package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21609_상어_중학교 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 한변의 길이
        M = Integer.parseInt(st.nextToken());   // 색상 개수

        int[][] board = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = playGame(board);
        System.out.println(rst);

        br.close();
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static final int BLANK = -2;

    private static int playGame(int[][] board) {
        int score = 0;
        // 1. 블록 그룹 찾기 (그룹에 속한 블록 2개 이상, 동일 개수 => 무지개 블록, 행 큰것, 열 큰것 기준으로 선택)
        while (findBlockGroup(board)) {
            // 2. 블록 그룹 제거 + 점수 추가
            removeBlockGroup(group, board);
            score += Math.pow(group.getCount(), 2);

            // 3. 빈칸으로 블록 내리기(내리는 경우 -> 바닥에 닿거나, 다른 블록에 닿는 경우까지 내리기
            gravityBlock(board);

            // 4. 90도 돌리기 (반시계)
            board = rotate(board);

            // 5. 빈칸으로 블록 내리기
            gravityBlock(board);
        }
        return score;
    }

    private static int[][] rotate(int[][] board) {
        int[][] arr = new int[N][N];

        for (int row = 0, newCol = 0; row < N; row++, newCol++) {
            for (int col = 0, newRow = N-1; col < N; col++, newRow--) {
                arr[newRow][newCol] = board[row][col];
            }
        }
        return arr;
    }

    private static void gravityBlock(int[][] board) {
        for (int col = 0; col < N; col++) {
            for (int row = N - 2; row >= 0; row--) {
                if (board[row][col] >= 0) {
                    int index = row;
                    while (index < N - 1 && board[index + 1][col] == BLANK) {
                        board[index + 1][col] = board[index][col];
                        board[index][col] = BLANK;
                        index++;
                    }
                }
            }
        }
    }

    // 블록 지우기
    private static void removeBlockGroup(Group group, int[][] board) {
        boolean[][] check = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        int basicRow = group.getRow();
        int basicCol = group.getCol();

        queue.add(new Point(basicRow, basicCol));
        check[basicRow][basicCol] = true;
        int color = board[basicRow][basicCol];
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int row = point.getRow();
            int col = point.getCol();
            board[row][col] = BLANK;
            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + DX[direction];
                int nextCol = col + DY[direction];

                if (checkBoundary(nextRow, nextCol) && !check[nextRow][nextCol]) {
                    int nextColor = board[nextRow][nextCol];
                    if (nextColor == color || nextColor == 0) {
                        check[nextRow][nextCol] = true;
                        queue.add(new Point(nextRow, nextCol));
                    }
                }
            }
        }

    }

    // 블록 그룹 찾기
    private static Group group;
    private static boolean find;

    private static boolean findBlockGroup(int[][] board) {
        find = false;
        boolean[][] check = new boolean[N][N];
        group = new Group(0, 0, 0, 0);

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                // 블록 체크 안했고, 블록이 1이상인 경우
                if (!check[row][col] && board[row][col] >= 1) {
                    check[row][col] = true;
                    find(row, col, board, check);
                }
            }
        }

        return find;
    }

    private static void find(int row, int col, int[][] board, boolean[][] check) {
        boolean[][] visited = new boolean[N][N];
        visited[row][col] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        int color = board[row][col];
        int rainbow = 0;
        int count = 1;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int curRow = point.getRow();
            int curCol = point.getCol();

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];

                if (checkBoundary(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    int nextColor = board[nextRow][nextCol];
                    if (nextColor == color || nextColor == 0) {
                        check[nextRow][nextCol] = true;
                        visited[nextRow][nextCol] = true;
                        if (nextColor == 0) {
                            check[nextRow][nextCol] = false;
                            rainbow++;
                        }
                        count++;
                        queue.add(new Point(nextRow, nextCol));
                    }
                }
            }
        }

        // 블록 그룹이 있는 경우
        if (!find && count >= 2) find = true;

        // 가장 큰 그룹 비교
        if (count >= 2) {
            Group newGroup = new Group(row, col, rainbow, count);
            if (group.compareTo(newGroup) > 0) {
                group = newGroup;
            }
        }
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N;
    }

    static class Group implements Comparable<Group> {
        private int row;
        private int col;
        private int rainbow;
        private int count;

        public Group(int row, int col, int rainbow, int count) {
            this.row = row;
            this.col = col;
            this.rainbow = rainbow;
            this.count = count;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getRainbow() {
            return rainbow;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(Group o) {
            if (this.count == o.count) {
                if (this.rainbow == o.rainbow) {
                    if (this.row == o.row) {
                        return Integer.compare(o.col, this.col);
                    }
                    return Integer.compare(o.row, this.row);
                }
                return Integer.compare(o.rainbow, this.rainbow);
            }
            return Integer.compare(o.count, this.count);
        }
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
