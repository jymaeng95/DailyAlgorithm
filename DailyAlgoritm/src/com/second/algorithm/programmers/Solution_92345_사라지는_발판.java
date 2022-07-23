package com.second.algorithm.programmers;

public class Solution_92345_사라지는_발판 {
    public static void main(String[] args) {
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
//        int[][] board = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};

//        int[][] board = {{1, 1, 1, 1, 1}};
//        int[] aloc = {0, 0};
//        int[] bloc = {0, 4};
        int rst = solution(board, aloc, bloc);
        System.out.println(rst);

    }

    private static int solution(int[][] board, int[] aloc, int[] bloc) {

        // A, B 현재 위치 체크
        Position posA = new Position(aloc[0], aloc[1]);
        Position posB = new Position(bloc[0], bloc[1]);

        // A 먼저 시작
        Turn turnA = movePlayerA(0, posA, posB, board);

        return turnA.getCount();
    }
    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};

    private static Turn movePlayerA(int count, Position posA, Position posB, int[][] board) {
        // A 위치
        int rowA = posA.getRow();
        int colA = posA.getCol();

        // A가 지는 플레이어가 되는 경우 (발판이 없어지거나, 이동을 할 수 없는 경우)
        if (board[rowA][colA] == 0 || checkMovable(rowA, colA, board)) {
            return new Turn(count, false);
        }

        int win = Integer.MAX_VALUE;
        int lose = 0;
        for (int direction = 0; direction < 4; direction++) {
            int nextRow = rowA + DX[direction];
            int nextCol = colA + DY[direction];
            if (checkBoardBoundary(nextRow, nextCol, board) && board[nextRow][nextCol] == 1) {
                // A는 이동할 수 있기 때문에 이동하고 발판을 없앤다.
                board[rowA][colA] = 0;

                // 현재 플레이어가 현재 턴에서 이기는 경우를 체크 (이기는 경우는 항상 이기는 선택) -> 이겨서 움직일 행 열 구해짐
                Turn turnA = movePlayerB(count + 1, new Position(nextRow, nextCol), posB, board);

                if(turnA.isWin())
                    lose = Math.max(lose, turnA.getCount());
                else
                    win = Math.min(win, turnA.getCount());

                board[rowA][colA] = 1;
            }
        }
        if(win != Integer.MAX_VALUE) return new Turn(win, true);
        if(lose != 0) return new Turn(lose, false);
        return null;    }

    private static Turn movePlayerB(int count, Position posA, Position posB, int[][] board) {
        // B 위치
        int rowB = posB.getRow();
        int colB = posB.getCol();

        // B가 지는 플레이어가 되는 경우 (발판이 없어지거나 이동을 할 수 없는 경우)
        if (board[rowB][colB] == 0 || checkMovable(rowB, colB, board)) {
            return new Turn(count, false);
        }

        int win = Integer.MAX_VALUE;
        int lose = 0;

        for (int direction = 0; direction < 4; direction++) {
            int nextRow = rowB + DX[direction];
            int nextCol = colB + DY[direction];

            if (checkBoardBoundary(nextRow, nextCol, board) && board[nextRow][nextCol] == 1) {
                // B가 이동할 수 있는 경우이므로 현재 발판 0
                board[rowB][colB] = 0;

                Turn turnB = movePlayerA(count + 1, posA, new Position(nextRow, nextCol), board);

                if(turnB.isWin())
                    win = Math.min(win, turnB.getCount());
                else
                    lose = Math.max(lose, turnB.getCount());

                board[rowB][colB] = 1;
            }
        }

        if(win != Integer.MAX_VALUE) return new Turn(win, true);
        if(lose != 0) return new Turn(lose, false);
        return null;
    }

    // 움직일 수 있는지 확인
    private static boolean checkMovable(int row, int col, int[][] board) {
        for (int direction = 0; direction < 4; direction++) {
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];

            // 게임판을 벗어나지 않고 옆의 칸이 아직 있는 경우
            if (checkBoardBoundary(nextRow, nextCol, board) && board[nextRow][nextCol] == 1)
                return false;
        }
        return true;
    }

    // 게임판 경계 확인
    private static boolean checkBoardBoundary(int nextRow, int nextCol, int[][] board) {
        return nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length;
    }

    static class Position {
        private int row;
        private int col;

        public Position(int row, int col) {
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

    static class Turn {
        private int count;
        private boolean win;

        public Turn(int count, boolean win) {
            this.count = count;
            this.win = win;
        }

        public int getCount() {
            return count;
        }

        public boolean isWin() {
            return win;
        }
    }
}
