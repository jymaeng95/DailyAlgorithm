package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3190_뱀 {
    private static int N, K, L;
    private static final int APPLE = 1, BLANK = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for (int count = 0; count < K; count++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            board[row][col] = APPLE;
        }

        L = Integer.parseInt(br.readLine());
        Queue<Command> commands = new LinkedList<>();
        for (int count = 0; count < L; count++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            char command = st.nextToken().charAt(0);

            commands.add(new Command(second, command));
        }

        int rst = playDummy(board, commands);
        System.out.println(rst);

        br.close();
    }


    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int playDummy(int[][] board, Queue<Command> commands) {
        Deque<Snake> deque = new LinkedList<>();
        boolean[][] check = new boolean[N][N];

        check[0][0] = true;
        deque.addFirst(new Snake(0, 0, RIGHT));

        int second = 0;
        while (!deque.isEmpty()) {
            /**
             * 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
             * 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
             * 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
             */

            // 초 확인 후 방향 전환
            Snake head = deque.peekFirst();
            int direction = head.getDirection();
            if (!commands.isEmpty() && commands.peek().getSecond() == second) {
                Command command = commands.poll();
                direction = rotate(direction, command.getCommand());
            }


            // 뱀의 머리 이동
            int nextRow = head.getRow() + DX[direction];
            int nextCol = head.getCol() + DY[direction];

            // 게임오버 체크 (벽에 닿거나, 꼬리에 닿는 경우)
            if (gameOver(nextRow, nextCol, check)) {
                second++;
                break;
            }

            // 게임오버 안되는 경우 사과 확인
            check[nextRow][nextCol] = true;
            deque.addFirst(new Snake(nextRow, nextCol, direction));

            // 사과 아닌 경우 (꼬리 없애고 체크풀기)
            if(board[nextRow][nextCol] != APPLE) {
                Snake tail = deque.pollLast();
                int tailRow = tail.getRow();
                int tailCol = tail.getCol();

                check[tailRow][tailCol] = false;
            } else board[nextRow][nextCol] = BLANK;

            second++;
        }

        return second;
    }

    private static int rotate(int direction, char rotate) {
        // 'L
        if (rotate == 'L') {
            if (direction == RIGHT) return UP;
            else if (direction == UP) return LEFT;
            else if (direction == LEFT) return DOWN;
            return RIGHT;
        }

        // 'D'
        if (direction == RIGHT) return DOWN;
        else if (direction == DOWN) return LEFT;
        else if (direction == LEFT) return UP;
        else return RIGHT;
    }

    private static boolean gameOver(int nextRow, int nextCol, boolean[][] check) {
        // 벽에 박지 않는 경우
        if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
            return check[nextRow][nextCol];
        }

        return true;
    }

    static class Snake {
        private final int row;
        private final int col;
        private final int direction;

        public Snake(int row, int col, int direction) {
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

    static class Command {
        private final int second;
        private final char command;

        public Command(int second, char command) {
            this.second = second;
            this.command = command;
        }

        public int getSecond() {
            return second;
        }

        public char getCommand() {
            return command;
        }
    }
}
