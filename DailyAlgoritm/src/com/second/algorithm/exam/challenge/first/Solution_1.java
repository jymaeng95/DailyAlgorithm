package com.second.algorithm.exam.challenge.first;

public class Solution_1 {
    public static void main(String[] args) {
        String X = "5525";
        String Y = "1255";

        String rst = solution(X, Y);
        System.out.println(rst);
    }

    private static String solution(String X, String Y) {
        int[] countA = new int[10];
        int[] countB = new int[10];

        for (int index = 0; index < X.length(); index++) {
            int number = Integer.parseInt(String.valueOf(X.charAt(index)));
            countA[number]++;
        }

        for (int index = 0; index < Y.length(); index++) {
            int number = Integer.parseInt(String.valueOf(Y.charAt(index)));
            countB[number]++;
        }

        StringBuilder maxNumber = new StringBuilder();
        boolean check = true;
        for (int index = 9; index >= 0; index--) {
            if (countA[index] > 0 && countB[index] > 0) {
                if(index != 0) check = false;
                int appendCount = Math.min(countA[index], countB[index]);
                for (int loop = 0; loop < appendCount; loop++) {
                    maxNumber.append(index);
                }
            }
        }

        if(maxNumber.length() < 1) return "-1";
        if(check) return "0";
        return maxNumber.toString();
    }
}
