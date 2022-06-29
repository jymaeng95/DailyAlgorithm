package com.algorithm.Programmers.Lv2;

public class SkillCheck_2 {
    public static void main(String[] args) {
//        int[][] arr1 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
//        int[][] arr2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};

        int[][] arr1 = {{1, 4},{3, 2}, {4, 1}};
        int[][] arr2 = {{3, 3}, {3, 3}};
        int[][] rst = solution(arr1, arr2);
        for (int[] ints : rst) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        for (int oneRow = 0; oneRow < arr1.length; oneRow++) {
            for (int twoCol = 0; twoCol < arr2[0].length; twoCol++) {
                for (int twoRow = 0; twoRow < arr2.length; twoRow++) {
                    answer[oneRow][twoCol] += arr1[oneRow][twoRow] * arr2[twoRow][twoCol];
                }
            }
        }
        return answer;
    }
}
