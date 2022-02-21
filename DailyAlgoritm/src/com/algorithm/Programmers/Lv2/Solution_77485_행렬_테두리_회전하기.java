package com.algorithm.Programmers.Lv2;

public class Solution_77485_행렬_테두리_회전하기 {
    public static void main(String[] args) {
        int rows = 6;
        int cols = 6;
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        int[] solution = solution(rows, cols, queries);
        for (int x : solution) System.out.println("x = " + x);
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                arr[row][col] = row * columns + (col + 1);
            }
        }

        int[] answer = new int[queries.length];
        for (int loop = 0; loop < queries.length; loop++) {
            answer[loop] = rotate(queries[loop][0]-1, queries[loop][1]-1, queries[loop][2]-1, queries[loop][3]-1, arr);
        }
        return answer;
    }
    private static int rotate(int x1, int y1, int x2, int y2, int[][] arr) {
        // [x1][y1 ~ y2-1] -> [x1][y1+1 ~ y2] 컬럼 + 1
        // [x1 ~ x2-1][y2] -> [x1 ~ x2][y2] 로우 + 1
        // [x2][y1+1~ y2] -> [x2][y1 ~ y2-1] 컬럼 -1
        // [x1+1 ~ x2][y1] -> [x1 ~ x2-][y1] 로우 -1
        int min = Integer.MAX_VALUE;
        int[][] buffer = new int[x2 - x1 + 1][y2 - y1 + 1];
        for (int row = x1, buf_row=0; row <= x2; row++, buf_row++) {
            for (int col = y1, buf_col=0; col <= y2; col++, buf_col++) {
                // 오른쪽 이동
                if (row == x1 && col != y2) {
                    buffer[buf_row][buf_col+1] = arr[row][col];
                    min = Math.min(min, arr[row][col]);
                }
                // 아래로 이동
                else if (row != x2 && col == y2) {
                    buffer[buf_row+1][buf_col] = arr[row][col];
                    min = Math.min(min, arr[row][col]);
                }
                // 왼쪽 이동
                else if (row == x2 && col != y1) {
                    buffer[buf_row][buf_col-1] = arr[row][col];
                    min = Math.min(min, arr[row][col]);
                }
                // 위로 이동
                else if (row != x1 && col == y1) {
                    buffer[buf_row-1][buf_col] = arr[row][col];
                    min = Math.min(min, arr[row][col]);
                }
                else {
                    buffer[buf_row][buf_col] = arr[row][col];
                }
            }
        }

        // buffer -> 바꾸기
        for (int row = x1, buf_row=0; row <= x2; row++, buf_row++) {
            for (int col = y1, buf_col=0; col <= y2; col++, buf_col++) {
                arr[row][col] = buffer[buf_row][buf_col];
            }
        }
        return min;
    }
}
