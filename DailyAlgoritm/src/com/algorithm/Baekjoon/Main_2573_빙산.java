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
        // 1. 매 해 증가 시키면서 빙산인 부분 동서남북 체크 후 빙산 녹이기 (오리지널 배열로 체크 복사한 배열로 녹이기)
        // 2. 다 녹이면 복사한 배열로 새롭게 체크
        int year = 0;
        while(true) {

            // 1. 기존 배열 새로운 배열로 복사
            int[][] copyIceberg = copyArray(n,m, iceberg);

            // 2. 기존 배열로 상하좌우 체크
            boolean remainIceberg = false;
            for(int row =0; row < n; row++) {
                for(int col = 0; col < m; col++) {
                    if(iceberg[row][col] != 0) {
                        int meltCount = 0;
                        for(int direction = 0; direction < 4; direction++) {
                            int checkRow = DX[direction] + row;
                            int checkCol = DY[direction] + col;
                            if(checkBound(checkRow, checkCol, n,m) && iceberg[checkRow][checkCol] == 0) {
                                meltCount++;
                            }
                        }
                        copyIceberg[row][col] = Math.max(copyIceberg[row][col] - meltCount, 0);
                        remainIceberg = true;
                    }
                }  
            }

            // 빙산이 다 녹았지만 두덩어리로 안나눠진 경우
            if(!remainIceberg) {
                year = 0;
                break;
            }

            // 빙하 녹이고 일년 보내기
            year++;
            
            //2. 체크 후 다 녹았으면 break;
            if(isDivide(copyIceberg, n,m)) break;
            
            //3. 다 안녹은 경우 오리지널 배열로 다시 복사
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
                    isDivide = true;
                }
            }
        }
        return false;
    }

    private static void bfs(int[][] copyIceberg, int row, int col, int n, int m, boolean[][] visited) {
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
