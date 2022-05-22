package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070_파이프_옮기기_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N][N];

        for(int row = 0; row < N; row ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col ++) {
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

        int[][] dp = new int[n][n];
        dp[0][0] = dp[0][1] = 1;
        boolean[][][] visited = new boolean[3][n][n];
        Queue<Pipe> queue = new LinkedList<>();
        queue.offer(new Pipe(0, 0, 1, 1));

        while(!queue.isEmpty()) {
            Pipe curPipe = queue.poll();


            movePipe(curPipe, visited, dp, house, queue);
        }

        return dp[n - 1][n - 1];
    }

    private static void movePipe(Pipe curPipe, boolean[][][] visited, int[][] dp, int[][] house, Queue<Pipe> queue) {
        int curType = curPipe.getType();
        int curRow = curPipe.getEndRow();
        int curCol = curPipe.getEndCol();
        int curCount = curPipe.getCount();

        if(curType == 0 || curType == 2) {
            int nextCol = curCol + 1;
            if(checkBound(0, curRow, curCol, house) && !visited[0][curRow][nextCol]) {
                visited[0][curRow][nextCol] = true;
                dp[curRow][nextCol] = Math.max(dp[curRow][nextCol], curCount);
                queue.add(new Pipe(0,curRow, nextCol, curCount));
            }
        }
        if(curType == 1 || curType == 2) {
            int nextRow = curRow + 1;
            if(checkBound(1, curRow, curCol, house) && !visited[1][nextRow][curCol]) {
                visited[1][nextRow][curCol] = true;
                dp[nextRow][curCol] = Math.max(dp[nextRow][curCol], curCount);
                queue.add(new Pipe(1,nextRow, curCol, curCount));
            }
        }

        int nextRow = curRow + 1;
        int nextCol = curCol + 1;
        if(checkBound(2, curRow, curCol, house) && !visited[2][nextRow][nextCol]) {
            visited[2][nextRow][nextCol] = true;
            dp[nextRow][nextCol] = Math.max(dp[nextRow][nextCol], curCount);
            queue.add(new Pipe(2, nextRow, nextCol, curCount));
        }

    }

    private static boolean checkBound(int type, int curRow, int curCol, int[][] house) {
        if(type == 0 || type == 1) {
            int nextRow = curRow + DX[type];
            int nextCol = curCol + DY[type];
            return nextRow >= 0 && nextRow < house.length && nextCol >= 0 && nextCol < house.length && house[nextRow][nextCol] != 1;
        }

        for(int direction = 0; direction < 3; direction++) {
            int nextRow = curRow + DX[direction];
            int nextCol = curCol + DY[direction];

            if(nextRow < 0 || nextRow >= house.length || nextCol < 0 || nextCol >= house.length || house[nextRow][nextCol] == 1) return false;
        }
        return true;
    }

    static class Pipe {
        private int type;
        private int endRow;
        private int endCol;
        private int count;

        public Pipe(int type, int endRow, int endCol, int count) {
            this.type = type;
            this.endRow = endRow;
            this.endCol = endCol;
            this.count = count;
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

        public int getCount() {
            return count;
        }
    }
}
