package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890_경사로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = countRoad(N, L, map);
        System.out.println(rst);
        br.close();
    }

    private static int countRoad(int n, int l, int[][] map) {
        /**
         * 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
         * 낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
         * 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
         *
         * 아래와 같은 경우에는 경사로를 놓을 수 없다.
         *
         * 경사로를 놓은 곳에 또 경사로를 놓는 경우
         * 낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
         * 낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
         */

        int count = 0;
        // 행에서 길 찾기
        for(int row = 0; row < n; row++) {
            boolean possible = isPossibleRow(row, n, l, map);
            if(possible) count++;
        }

        // 열에서 길 찾기
        for (int col = 0; col < n; col++) {
            boolean possible = isPossibleCol(col, n, l, map);
            if (possible) count++;
        }
        return count;
    }

    private static boolean isPossibleCol(int col, int n, int l, int[][] map) {
        int next = map[0][col];
        boolean[] road = new boolean[n];
        for (int row = 1; row < n; row++) {
            // 경사로 설치 필요한 경우 (다음칸이 낮은 경우)
            if (!road[row] && map[row][col] != next && map[row][col] == next - 1) {
                // 현재 높이
                int height = map[row][col];
                // 길이 가 범위 넘어가지 않도록
                if (row + l <= n) {
                    for (int length = row; length < row + l; length++) {
                        // 현재 위치에 경사로 설치 안했고 경사로 설치 시작 높이와 경사로 길이 만큼 지도 높이가 동일한 경우 설치 가능
                        if (!road[length] && height == map[length][col]) {
                            road[length] = true;
                        }
                        // 한 칸이라도 설치 못하는 경우 경사로 설치 자체를 못하므로 false
                        else return false;
                    }
                    // 설치 가능하므로 다음 값 현재 경사로 설치한 높이로 갱신
                    next = map[row][col];
                }
                // 범위 넘어간 경우 경사로 설치 자체를 못하므로 false
                else return false;
            }
            // 경사로 설치 필요한 경우 (다음칸이 높은 경우)
            else if (!road[row] && map[row][col] != next && map[row][col] == next + 1) {
                //현재 높이
                int height = map[row - 1][col];
                // 길이가 범위 넘어가지 않도록
                if (row - l >= 0) {
                    for (int length = row - 1; length >= row - l; length--) {
                        // 현재 위치에서 경사로 설치 안했고, 경사로 설치 시작
                        if (!road[length] && height == map[length][col]) {
                            road[length] = true;
                        }
                        // 한 칸이라도 설치 못하는 경우 경사로 설치 자체를 못하므로 false
                        else return false;
                    }
                    // 설치 가능하므로 다음 값 현재 경사로 설치한 높이로 갱신
                    next = map[row][col];
                }
                // 범위 넘어간 경우 경사로 설치 자체를 못하므로 false
                else return false;
            }
            // 높이가 두 칸 이상 차이나는 경우
            else if (map[row][col] > next +1 || map[row][col] < next -1) return false;
        }
        return true;
    }

    private static boolean isPossibleRow(int row, int n, int l, int[][] map) {
        int next = map[row][0];
        boolean[] road = new boolean[n];
        for (int col = 1; col < n; col++) {
            // 경사로 설치 필요한 경우 (다음칸이 낮은 경우)
            if (!road[col] && map[row][col] != next && map[row][col] == next - 1) {
                // 현재 높이
                int height = map[row][col];
                // 길이 가 범위 넘어가지 않도록
                if (col + l <= n) {
                    for (int length = col; length < col + l; length++) {
                        // 현재 위치에 경사로 설치 안했고 경사로 설치 시작 높이와 경사로 길이 만큼 지도 높이가 동일한 경우 설치 가능
                        if (!road[length] && height == map[row][length]) {
                            road[length] = true;
                        }
                        // 한 칸이라도 설치 못하는 경우 경사로 설치 자체를 못하므로 false
                        else return false;
                    }
                    // 설치 가능하므로 다음 값 현재 경사로 설치한 높이로 갱신
                    next = map[row][col];
                }
                // 범위 넘어간 경우 경사로 설치 자체를 못하므로 false
                else return false;
            }
            // 경사로 설치 필요한 경우 (다음칸이 높은 경우)
            else if (!road[col] && map[row][col] != next && map[row][col] == next + 1) {
                //현재 높이
                int height = map[row][col - 1];
                // 길이가 범위 넘어가지 않도록
                if (col - l >= 0) {
                    for (int length = col - 1; length >= col - l; length--) {
                        // 현재 위치에서 경사로 설치 안했고, 경사로 설치 시작
                        if (!road[length] && height == map[row][length]) {
                            road[length] = true;
                        }
                        // 한 칸이라도 설치 못하는 경우 경사로 설치 자체를 못하므로 false
                        else return false;
                    }
                    // 설치 가능하므로 다음 값 현재 경사로 설치한 높이로 갱신
                    next = map[row][col];
                }
                // 범위 넘어간 경우 경사로 설치 자체를 못하므로 false
                else return false;
            }
            // 높이가 두칸 이상 차이 나는 경우
            else if (map[row][col] > next +1 || map[row][col] < next -1) return false;
        }
        return true;
    }
}
