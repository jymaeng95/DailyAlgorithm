package com.algorithm.Programmers.test;

public class  Solution_DevMatching_2 {
    public static void main(String[] args) {
        int n = 5;
        boolean horizontal = false;

        int[][] rst = solution(n, horizontal);
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print(rst[row][col]+" ");
            }
            System.out.println();
        }
    }


    private static int[][] solution(int n, boolean horizontal) {
        int[][] room = new int[n][n];

        int index = 1;
        int startRow = 0;
        int startCol = 0;
        // 이동하고 horizontal = !horizontal
        for (int coverage = 1; coverage <= n; coverage++) {
            if (horizontal) {
                for (int row = startRow; row >= 0; row--) {
                    for (int col = 0; col < coverage; col++) {
                        if (room[row][col] == 0) {
                            room[row][col] = index;
                            index++;
                        }
                    }
                }
                startRow = 0;
                startCol = coverage;
            } else {
                for (int col = startCol; col >= 0; col--) {
                    for (int row = 0; row < coverage; row++) {
                        if (room[row][col] == 0) {
                            room[row][col] = index;
                            index++;
                        }
                    }
                }
                startRow = coverage;
                startCol = 0;
            }
            horizontal = !horizontal;
        }

        return room;
    }
}
