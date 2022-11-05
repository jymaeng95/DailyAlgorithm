package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위_굴리기 {
    private static int N, M, x, y, K;
    private static int[] moveRow, moveCol;
    private static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
    private static final int BOTTOM = 3, TOP = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 남북 이동하는 경우 + 동서 이동하는 경우
        moveRow = new int[]{2, 1, 5, 6};
        moveCol = new int[]{4, 1, 3, 6};

        // 최초 위치 복사
        int[] dice = new int[7];
        dice[6] = board[x][y];

        st = new StringTokenizer(br.readLine());
        for (int order = 0; order < K; order++) {
            int command = Integer.parseInt(st.nextToken());

            if (rollDice(command, board, dice)) {
                System.out.println(dice[moveRow[TOP]]);
            }
        }

        br.close();
    }

    private static final int[] DX = {0, 0, 0, -1, 1};
    private static final int[] DY = {0, 1, -1, 0, 0};

    private static boolean rollDice(int command, int[][] board, int[] dice) {
        /**
         * 1. 주사위를 굴린다.
         * 2. 이동한 칸 = 0, 주사위 바닥 복사해서 칸에 저장
         * 3. 이동한 칸 != 0, 주사위 바닥으로 값 복사, 이동한 칸 = 0;
         * 4. 게임판을 벗어나는 경우 false;
         */
        int nextX = x + DX[command];
        int nextY = y + DY[command];

        // 4. 게임판을 벗어나는 경우 false;
        if (!outOfBoard(nextX, nextY)) return false;

        // 1. 주사위를 굴린다.
        if (command == EAST || command == WEST)
            roll(moveCol, moveRow, command);
        else
            roll(moveRow, moveCol, command);

        // 2. 이동한 칸이 0인 경우
        if (board[nextX][nextY] == 0) {
            board[nextX][nextY] = dice[moveRow[BOTTOM]];
        }  else {
            dice[moveRow[BOTTOM]] = board[nextX][nextY];
            board[nextX][nextY] = 0;
        }

        // 주사위 위치 실제로 옮겨주기
        x = nextX;
        y = nextY;

        return true;
    }

    private static void roll(int[] move, int[] base, int command) {
        if (command == EAST || command == NORTH) {
            int temp = move[3];
            System.arraycopy(move, 0, move, 1, 3);
            move[0] = temp;

        } else {
            int temp = move[0];
            System.arraycopy(move, 1, move, 0, 3);
            move[3] = temp;
        }

        // 남북 바닥, 아래 바꿔주기
        base[BOTTOM] = move[BOTTOM];
        base[TOP] = move[TOP];
    }

    private static boolean outOfBoard(int nextX, int nextY) {
        return nextX >= 0 && nextX < N && nextY >= 0 && nextY < M;
    }
}
