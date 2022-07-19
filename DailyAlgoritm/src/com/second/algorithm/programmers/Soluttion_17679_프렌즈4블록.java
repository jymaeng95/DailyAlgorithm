package com.second.algorithm.programmers;

public class Soluttion_17679_프렌즈4블록 {
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

        int rst = solution(m, n, board);
        System.out.println("rst = " + rst);
    }

    private static int solution(int m, int n, String[] board) {
        /**
         * 1. for문을 돌면서 삭제 되지 않은 모든 부분에 대해서 체크 bfs 이용
         * 2. 삭제되는 부분은 재귀로 체크해주기
         * 3. 삭제되는 부분을 - 로 표시
         * 4. 한번 다돈 뒤에 컬럼을 돌면서 - 개수만큼 체크해서 각 컬럼에 더해줌
         * 5. 삭제할 때 마다 개수 카운트
         */

        // 문자 배열로 변환
        char[][] newBoard = new char[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                newBoard[row][col] = board[row].charAt(col);
            }
        }

        boolean delete = true;
        count = 0;

        while (delete) {
            delete = false;
            boolean[][] check = new boolean[m][n];
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {

                    // 현재 루프에서 체크 하지 않았거나 이미 삭제된 블럭인 경우 체크안함
                    char character = newBoard[row][col];
                    if (newBoard[row][col] != '-') {
                        boolean result = checkAndDelete(row, col, character, newBoard, check);

                        // delete가 false 인 경우 체크 = 한번이라도 블록이 터진 적 있는지 체크
                        if (!delete) delete = result;
                    }
                }
            }

            // 현재 턴이 모두 지나간 경우 컬럼을 돌면서 삭제된 블럭인 경우 -로 바꿔주고 블럭 옮기기
            for (int col = 0; col < n; col++) {
                for (int row = 0; row < m; row++) {
                    if (check[row][col]) {
                        newBoard[row][col] = '-';
                        downBlocks(row, col, newBoard);
                    }
                }
            }
        }

        return count;
    }

    // 블럭 밑으로 내리기
    private static void downBlocks(int row, int col, char[][] newBoard) {
        // 폭파된 블럭이 가장 위로 오거나, 위에 있는 폭파된 블럭을 만나는 경우까지 블럭을 스와핑
        while (row != 0 || newBoard[row][col] != '-') {
            // 바로 위의 블럭이 멀쩡한 블럭인 경우 스와핑
            if (newBoard[row - 1][col] != '-') {
                newBoard[row][col] = newBoard[row - 1][col];
                newBoard[row - 1][col] = '-';
            }
            row--;
        }
    }
    
    // 자기자신, 오른쪽, 아래, 대각오른쪽 아래 체크
    private static final int[] DX = {0,0, 1, 1};
    private static final int[] DY = {0,1, 0, 1};
    private static int count;
    private static boolean checkAndDelete(int row, int col, char character, char[][] newBoard, boolean[][] check) {
        for (int direction = 0; direction < 4; direction++) {
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];

            // 게임판을 벗어나거나 삭제할 캐릭터 블록이 다른 경우
            if (!checkBoundary(nextRow, nextCol, newBoard.length, newBoard[0].length) || character != newBoard[nextRow][nextCol])
                return false;
        }

        // 삭제 가능한 경우
        for (int direction = 0; direction < 4; direction++) {
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];

            // 아직 지워지지 않은 경우에 대해서만 카운팅
            if (!check[nextRow][nextCol]) {
                check[nextRow][nextCol] = true;
                count++;
            }
        }
        return true;
    }

    // 게임판 경계 체크
    private static boolean checkBoundary(int nextRow, int nextCol, int m, int n) {
        return nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n;
    }
}
