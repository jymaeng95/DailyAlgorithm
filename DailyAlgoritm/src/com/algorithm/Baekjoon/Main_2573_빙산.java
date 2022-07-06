package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] iceberg = new int[N][M];
        for(int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < M; col++) {
                iceberg[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getMinYear(N, M, iceberg);
        System.out.println(rst);

        br.close();
    }

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};
    private static int getMinYear(int n, int m, int[][] iceberg) {
        /**
         * 1. 기존 빙산 배열을 새로운 빙산 배열로 하나 복사하기
         * 2. 기존 빙산 배열을 이용해 남은 남아있는 빙산의 상하좌우에 바다가 인접해있는지 체크
         * 3. 새로 복사한 빙산 배열에 인접한 바다 개수 만큼 녹이기
         * 3-1. 현재 해에 한번도 빙산을 녹이지 않은 경우 다 녹았지만 두 덩어리로 나눠지지 않은 경우므로 return 0;
         * 4. 현재 해에 녹일 수 있는 빙산을 녹였으므로, 현재 해를 1 증가
         * 5. 빙산이 두 덩어리 이상으로 나뉘어졌는지 판단하기
         * 6. 나뉘어 지지 않은 경우 새로운 배열을 다음 해의 기존 배열로 사용하기 위해 복사
         */
        int year = 0;
        while(true) {

            // 1. 기존 배열 새로운 배열로 복사
            int[][] copyIceberg = copyArray(n,m, iceberg);

            boolean remainIceberg = false;          // 현재 해에 남은 빙산이 있는지 판단하기 위해
            for(int row =0; row < n; row++) {
                for(int col = 0; col < m; col++) {
                    // 2. 남아있는 빙산이 있고 상하좌우 바다 판단
                    if(iceberg[row][col] != 0) {
                        int meltCount = 0;
                        for(int direction = 0; direction < 4; direction++) {
                            int checkRow = DX[direction] + row;
                            int checkCol = DY[direction] + col;
                            if(checkBound(checkRow, checkCol, n,m) && iceberg[checkRow][checkCol] == 0) {
                                meltCount++;        // 상하좌우의 바다 개수 구하기
                            }
                        }
                        // 3. 새로 복사한 빙산 배열에 인접한 바다 개수 만큼 녹이기
                        copyIceberg[row][col] = Math.max(copyIceberg[row][col] - meltCount, 0);     // 0 보다 크면 빙산 녹인 크기 아니면 다 녹은 경우
                        remainIceberg = true;       // 한번이라도 빙산을 녹였으면 남은 빙산이 있었다는 뜻
                    }
                }  
            }

            // 2-1. 빙산이 다 녹았지만 두 덩어리로 안나눠진 경우
            if(!remainIceberg) {
                year = 0;
                break;
            }

            // 3. 빙산 녹이고 일년 보내기
            year++;
            
            // 4. 빙산이 두갈래 이상으로 나눠졌는지 판단
            if(isDivide(copyIceberg, n,m)) break;
            
            // 5. 다 녹지 않은 경우 기존 배열을 남은 빙산 배열로 복사
            iceberg = copyArray(n, m, copyIceberg);
        }
        return year;
    }

    private static boolean isDivide(int[][] copyIceberg, int n, int m) {
        boolean[][] visited = new boolean[n][m];
        boolean isDivide = false;

        for(int row =0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(!visited[row][col] && copyIceberg[row][col] != 0) {
                    if(isDivide) return true;       // 이어진 빙산 한번 탐색한 경우 다음 방문하지 않은 빙산은 나눠진 경우이기 때문에 true

                    bfs(copyIceberg, row, col, n, m, visited);
                    isDivide = true;        // 하나의 이어진 빙산을 구함
                }
            }
        }
        return false;
    }

    private static void bfs(int[][] copyIceberg, int row, int col, int n, int m, boolean[][] visited) {
        // 이어진 빙산 체크하기
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));

        while(!queue.isEmpty()) {
            Point curPoint = queue.poll();
            int curRow = curPoint.getRow();
            int curCol = curPoint.getCol();

            if(visited[curRow][curCol]) continue;
            visited[curRow][curCol] = true;
            for(int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];
                if(checkBound(nextRow,nextCol, n,m) && !visited[nextRow][nextCol] && copyIceberg[nextRow][nextCol] != 0) {
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean checkBound(int checkRow, int checkCol, int n, int m) {
        return checkRow >= 0 && checkRow < n && checkCol >= 0 && checkCol < m;
    }

    private static int[][] copyArray(int n, int m, int[][] iceberg) {
        int[][] copyIceberg = new int[n][m];
        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                copyIceberg[row][col] = iceberg[row][col];
            }
        }
        return copyIceberg;
    }

    static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
