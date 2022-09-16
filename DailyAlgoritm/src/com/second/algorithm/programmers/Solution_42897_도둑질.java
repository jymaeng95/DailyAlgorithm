package com.second.algorithm.programmers;

public class Solution_42897_도둑질 {
    public static void main(String[] args) {
        int[] money = {1, 2, 3, 1};
        int rst = solution(money);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] money) {
        //원형으로 되어있는 마을이므로 1번 집을 털거나 2번집을 터는 경우를 나눠서 DP
        int[][] firstDP = new int[2][money.length];
        int[][] secondDP = new int[2][money.length];

        // 1번 집 부터 마지막 보다 하나 앞의 집까지 터는 경우 (1번집을 털면 마지막 집은 절대 못텀 인접)
        firstDP[0][0] = money[0];
        firstDP[0][1] = money[1];
        firstDP[1][1] = Math.max(firstDP[0][0], firstDP[1][0]);

        for (int index = 2; index < money.length - 1; index++) {
            firstDP[0][index] = Math.max(firstDP[0][index - 2], firstDP[1][index - 2]) + money[index];// 터는 경우
            firstDP[1][index] = Math.max(firstDP[0][index - 1], firstDP[1][index - 1]);
        }

        // 2번 집부터 마지막 집까지 터는 경우
        secondDP[0][1] = money[1];
        secondDP[0][2] = money[2];
        secondDP[1][2] = Math.max(secondDP[0][1], secondDP[1][1]);
        for(int index = 3; index < money.length; index++) {
            secondDP[0][index] = Math.max(secondDP[0][index - 2], secondDP[1][index - 2]) + money[index];// 터는 경우
            secondDP[1][index] = Math.max(secondDP[0][index - 1], secondDP[1][index - 1]);
        }

        int maxFirst = Math.max(firstDP[0][money.length - 2], firstDP[1][money.length - 2]);
        int maxSecond = Math.max(secondDP[0][money.length - 1], secondDP[1][money.length - 1]);

        return Math.max(maxFirst, maxSecond);
    }
}
