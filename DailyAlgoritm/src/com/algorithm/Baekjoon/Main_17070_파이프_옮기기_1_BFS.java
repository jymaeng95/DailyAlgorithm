package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070_파이프_옮기기_1_BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                house[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = moveCount(N, house);
        System.out.println(rst);

        br.close();
    }

    private static final int[] DX = {0, 1, 1};
    private static final int[] DY = {1, 0, 1};
    private static int moveCount(int n, int[][] house) {
        /**
         * 1. BFS로 3가지 타입에 대해 모두 탐색 (타입 별로 경로 판단 필요하므로 3차원 visited배열)
         * 2. 다음 엔드포인트는 dp[현재 엔드 포인트] + dp[다음 엔드 포인트]
         */

        int[][][] dp = new int[n][n][3];
        dp[0][0][0] = dp[0][1][0] = 1;
        boolean[][][] visited = new boolean[n][n][3];
        Queue<Pipe> queue = new LinkedList<>();
        queue.offer(new Pipe(0, 0, 1));

        while (!queue.isEmpty()) {
            Pipe curPipe = queue.poll();


            movePipe(curPipe, visited, dp, house, queue);
        }

        return dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2];
    }

    private static void movePipe(Pipe curPipe, boolean[][][] visited, int[][][] dp, int[][] house, Queue<Pipe> queue) {
        int curType = curPipe.getType();
        int curRow = curPipe.getEndRow();
        int curCol = curPipe.getEndCol();

        // 가로 혹은 대각선인 경우 다음 파이프는 가로 모양
        if (curType == 0 || curType == 2) {
            int nextCol = curCol + 1;
            if (checkBound(0, curRow, curCol, house)) {
                visited[curRow][nextCol][0] = true;
                dp[curRow][nextCol][0] = dp[curRow][curCol][0] + dp[curRow][curCol][2];
                queue.add(new Pipe(0, curRow, nextCol));
            }
        }
        // 세로 혹은 대각선인 경우 다음 파이프는 세로 모양
        if (curType == 1 || curType == 2) {
            int nextRow = curRow + 1;
            if (checkBound(1, curRow, curCol, house) ) {
                visited[nextRow][curCol][1] = true;
                dp[nextRow][curCol][1] = dp[curRow][curCol][1] + dp[curRow][curCol][2];
                queue.add(new Pipe(1, nextRow, curCol));
            }
        }

        // 가로 세로 대각선은 모두 대각선 방향으로 놓는 것이 가능
        int nextRow = curRow + 1;
        int nextCol = curCol + 1;
        if (checkBound(2, curRow, curCol, house)) {
            visited[nextRow][nextCol][2] = true;
            dp[nextRow][nextCol][2] = dp[curRow][curCol][0] + dp[curRow][curCol][1] + dp[curRow][curCol][2];
            queue.add(new Pipe(2, nextRow, nextCol));
        }

    }

    private static boolean checkBound(int type, int curRow, int curCol, int[][] house) {
        if (type == 0 || type == 1) {
            int nextRow = curRow + DX[type];
            int nextCol = curCol + DY[type];
            return nextRow >= 0 && nextRow < house.length && nextCol >= 0 && nextCol < house.length && house[nextRow][nextCol] != 1;
        }

        // 대각선 이동 경우 범위 판단
        for (int direction = 0; direction < 3; direction++) {
            int nextRow = curRow + DX[direction];
            int nextCol = curCol + DY[direction];

            if (nextRow < 0 || nextRow >= house.length || nextCol < 0 || nextCol >= house.length || house[nextRow][nextCol] == 1)
                return false;
        }
        return true;
    }

    static class Pipe {
        private int type;
        private int endRow;
        private int endCol;

        public Pipe(int type, int endRow, int endCol) {
            this.type = type;
            this.endRow = endRow;
            this.endCol = endCol;
        }

        public int getType() {
            return type;
        }

        public int getEndRow() {
            return endRow;
        }

        public int getEndCol() {
            return endCol;
        }
    }
}