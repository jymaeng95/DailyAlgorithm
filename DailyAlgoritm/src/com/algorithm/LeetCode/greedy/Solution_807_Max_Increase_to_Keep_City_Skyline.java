package com.algorithm.LeetCode.greedy;

public class Solution_807_Max_Increase_to_Keep_City_Skyline {
    public static void main(String[] args) {
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        int rst = maxIncreaseKeepingSkyline(grid);
        System.out.println("rst = " + rst);
    }

    private static int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] rowMax = new int[grid.length];
        int[] colMax = new int[grid.length];

        for (int i = 0; i < grid.length; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < grid.length; j++) {
                row = Integer.max(row, grid[i][j]);
                col = Integer.max(col, grid[j][i]);
            }
            rowMax[i] = row;
            colMax[i] = col;
        }

        int totalSum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int min = Integer.min(rowMax[i],colMax[j]);
                totalSum += min - grid[i][j];
            }
        }

        return totalSum;
    }
}
