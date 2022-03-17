package com.algorithm.Programmers.Lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_17679_프렌즈4블록 {
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
//
//        int m = 6;
//        int n = 6;
//        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        int rst = solution(m, n, board);
        System.out.println("rst = " + rst);
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static String[][] gameBoard;
    private static boolean[][] visited;
    private static int count;
    private static final int[] DX = {0, 1, 1};
    private static final int[] DY = {1, 0, 1};

    private static int solution(int m, int n, String[] board) {
        count = 0;
        gameBoard = new String[m][n];   // 새로운 게임 배열 초기화
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length(); col++) {
                String[] block = board[row].split("");
                gameBoard[row][col] = block[col];
            }
        }

        boolean removeBlock = true;     // 블럭이 터졌는지 확인 체크
        while (removeBlock) {           // 이전 사이클에서 블럭이 안터진 경우 -> 더이상 터뜨릴 것 이 없다.
            visited = new boolean[m][n];    //사이클마다 방문 배열 초기화
            removeBlock = false;
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    // 방문하지 않았고, 게임 보드가 빈칸 ("-") 아닌 경우 탐색
                    if (!visited[row][col] && !gameBoard[row][col].equals("-")) {
                        // 블럭 터진 경우 갱신
                        if (blockSearch(row, col, m, n))
                            removeBlock = true;
                    }
                }
            }

            // 현재 사이클에서 블럭 다 터뜨린 후 새로운 보드로 갱신
            String[] newBoard = new String[n];

            for (int col = 0; col < n; col++) {
                StringBuilder sb = new StringBuilder();
                for (int row = 0; row < m; row++) {
                    // 터뜨린 것 내려주기 위해 빈칸 인경우 제외하고 블럭 붙히기
                    if (!gameBoard[row][col].equals("-")) sb.append(gameBoard[row][col]);
                }
                int length = sb.length();   // 현재 열에 남은 블럭 수
                for (int loop = 0; loop < m - length; loop++) {
                    // 게임 보드 행 길이에서 앞에 빠진 빈칸 넣어줌
                    sb.insert(0, "-");
                }
                newBoard[col] = sb.toString();
            }

            // 새로 갱신한 보드 배열 초기화 다음 사이클 시작
            for(int col = 0; col < n; col++) {
                for(int row = 0; row < m ; row++) {
                    String[] blocks = newBoard[col].split("");
                    gameBoard[row][col] = blocks[row];
                }
            }
        }

        return count;
    }

    private static boolean blockSearch(int row, int col, int m, int n) {
        boolean remove = false;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int curX = point.getX();
            int curY = point.getY();

            // 배열 경계를 넘어가지 않고, 오른쪽, 아래, 오른쪽 대각 아래 쪽 모두 같은 블럭인지 체크
            if (checkBound(curX, curY, m, n) && checkSameBlock(curX, curY)) {
                remove = true;      // 세 곳 모두 같은 블럭이면 2*2 동일 블럭이므로 블럭 터뜨림 true

                // 체크한 세 곳의 블럭을 큐에 넣어 해당 블럭부터 다시 2*2 동일 블럭있는지 체크
                for (int direction = 0; direction < 3; direction++) {
                    int nextX = curX + DX[direction];
                    int nextY = curY + DY[direction];

                    // 범위 상 동일한 위치의 블럭이 중복 체크 될 수 있기 때문에 방문하지 않은 블럭만 큐에 넣어준 후 방문 체크
                    if (!visited[nextX][nextY]) {
                        queue.add(new Point(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }

            // 큐 사이클 돌면서 블럭이 터진 경우 하나씩 세어준 후 빈칸으로 바꾸기
            if (remove) {
                count++;
                gameBoard[curX][curY] = "-";
            }

        }
        return remove;
    }

    // 오른쪽 대각 아래, 오른쪽, 아래 3방향 동일 블럭 체크
    private static boolean checkSameBlock(int curX, int curY) {
        String curBlock = gameBoard[curX][curY];
        return curBlock.equals(gameBoard[curX][curY + 1]) && curBlock.equals(gameBoard[curX + 1][curY]) && curBlock.equals(gameBoard[curX + 1][curY + 1]);
    }

    // 배열 경계 체크
    private static boolean checkBound(int curX, int curY, int m, int n) {
        int underX = curX + 1;
        int rightY = curY + 1;

        return rightY >= 0 && rightY < n && underX >= 0 && underX < m;
    }
}
