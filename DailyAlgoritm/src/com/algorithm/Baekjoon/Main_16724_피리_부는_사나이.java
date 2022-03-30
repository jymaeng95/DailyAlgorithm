package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16724_피리_부는_사나이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[][] zone = new String[N][M];

        for (int row = 0; row < N; row++) {
            String[] split = br.readLine().split("");
            for (int col = 0; col < M; col++) {
                zone[row][col] = split[col];
            }
        }

        int rst = getSafeZone(N,M, zone);
        System.out.println(rst);
        br.close();
    }
    private static boolean[][] visited;
    private static boolean[][] finished;
    private static int count;
    private static int getSafeZone(int n, int m, String[][] zone) {
        visited = new boolean[n][m];
        finished = new boolean[n][m];
        count = 0;
        // zone을 모두 돌면서 cycle 판별
        for(int row=0; row < n; row ++) {
            for (int col = 0; col < m; col++) {
                if(!visited[row][col]) {
                    peopleMove(row, col, zone);
                }
            }
        }
        return count;
    }
    static class Move {
        private int row;
        private int col;

        public Move(int row, int col) {
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
    private static void peopleMove(int row, int col, String[][] zone) {
        // 방문 처리
        visited[row][col] = true;

        // zone 방향 체크
        Move next = checkDirection(row, col, zone);
        if(!visited[next.getRow()][next.getCol()]) {
            peopleMove(next.getRow(), next.getCol(), zone);
        }
        // back Edge 사이클 생김
        else if(!finished[next.getRow()][next.getCol()]) {
            count++;
        }


        finished[row][col] = true;
    }

    private static Move checkDirection(int row, int col, String[][] zone) {
        int nextRow = row;
        int nextCol = col;

        if(zone[row][col].equals("U")) nextRow -= 1;
        else if(zone[row][col].equals("D")) nextRow += 1;
        else if(zone[row][col].equals("R")) nextCol += 1;
        else nextCol -= 1;

        return new Move(nextRow, nextCol);
    }
}
