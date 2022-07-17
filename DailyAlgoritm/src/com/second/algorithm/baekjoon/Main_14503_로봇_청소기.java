package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14503_로봇_청소기 {

    private static int N, M;
    private static int[][] area;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        int startDirection = Integer.parseInt(st.nextToken());

        area = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                area[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = cleanHouse(startRow, startCol, startDirection);
        System.out.println(rst);

        br.close();
    }

    private static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, 1, 0, -1};

    private static int cleanHouse(int startRow, int startCol, int startDirection) {
        /**
         * 현재 위치를 청소한다.
         * 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
         * 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
         * 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
         * 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
         * 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
         */
        int count = 0;
        boolean[][] clean = new boolean[N][M];

        Queue<VacuumCleaner> queue = new LinkedList<>();
        queue.add(new VacuumCleaner(startRow, startCol, startDirection));

        while (!queue.isEmpty()) {
            VacuumCleaner cleaner = queue.poll();
            int curRow = cleaner.getRow();
            int curCol = cleaner.getCol();
            int curDirection = cleaner.getDirection();

            // 1. 현재 위치를 청소한다.
            if(!clean[curRow][curCol]) {
                clean[curRow][curCol] = true;
                count++;
            }

            // 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
            boolean available = false;
            for (int direction = 0; direction < 4; direction++) {
                int nextDirection = turnLeft(curDirection);
                int nextRow = curRow + DX[nextDirection];
                int nextCol = curCol + DY[nextDirection];

                // 2-1. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
                if (area[nextRow][nextCol] != 1 && !clean[nextRow][nextCol]) {
                    queue.add(new VacuumCleaner(nextRow, nextCol, nextDirection));
                    available = true;
                    break;
                }
                // 2-2. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
                else
                    curDirection = nextDirection;

            }

            // 3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
            if(!available) {
                int backRow = curRow - DX[curDirection];
                int backCol = curCol - DY[curDirection];

                // 3-1. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
                if(area[backRow][backCol] == 1)
                    return count;

                queue.add(new VacuumCleaner(backRow, backCol, curDirection));
            }
        }

        return count;
    }

    // 왼쪽으로 돌기
    private static int turnLeft(int direction) {
        if (direction == UP) return LEFT;
        if (direction == LEFT) return DOWN;
        if (direction == DOWN) return RIGHT;
        return UP;
    }

    static class VacuumCleaner {
        private int row;
        private int col;
        private int direction;

        public VacuumCleaner(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getDirection() {
            return direction;
        }
    }
}
