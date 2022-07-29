package com.second.algorithm.exam.challenge.first;

public class Solution_4 {
    public static void main(String[] args) {
        int[][] beginning = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0}
        };
        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};

        int rst = solution(beginning, target);
        System.out.println(rst);
    }

    private static int count;
    private static boolean finish;
    private static boolean[][] availableRow,availableCol;
    private static int solution(int[][] beginning, int[][] target) {
        int row = beginning.length;
        int col = beginning[0].length;
        int count =0;
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            int[][] copy = copyOf(beginning);
            int cur = 0;
            for (int colIndex = 0; colIndex < col; colIndex++) {
                // 뒤집기
                if(copy[rowIndex][colIndex]!= target[rowIndex][colIndex]) {
                    for (int y = 0; y < row; y++) {
                        if(copy[y][colIndex] == 0) copy[y][colIndex] = 1;
                        else copy[y][colIndex] = 0;
                    }
                    cur++;
                }
            }


            for (int x = 0; x < row; x++) {
                if (x != rowIndex) {
                    if(copy[x][0] != target[x][0]) {
                        for (int z = 0; z < col; z++) {
                            if(copy[x][z] == 0) copy[x][z] =1;
                            else copy[x][z] = 0;
                            cur++;
                        }
                    }
                }
            }

            if(check(copy, target))
                count = Math.min(count, cur);
        }
        return count;
    }

    private static boolean check(int[][] copy, int[][] target) {
        for (int row = 0; row < copy.length; row++) {
            for (int col = 0; col < copy[0].length; col++) {
                if (target[row][col] != copy[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] copyOf(int[][] beginning) {
        int[][] copy = new int[beginning.length][beginning[0].length];
        for (int row = 0; row < beginning.length; row++) {
            for (int col = 0; col < beginning[0].length; col++) {
                copy[row][col] = beginning[row][col];
            }
        }
        return copy;
    }

}
