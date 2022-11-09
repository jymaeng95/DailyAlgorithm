package com.second.algorithm.programmers;

public class Solution_12907_거스름돈 {
    public static void main(String[] args) {
        int n = 5;
        int[] money = {1, 2, 5};

        int rst = solution(n, money);
        System.out.println("rst = " + rst);
    }

    private static int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int index = 0; index < money.length; index++) {
            for (int price = 1; price <= n; price++) {
                if (price - money[index] >= 0) {
                    dp[price] += (dp[price - money[index]]) % 1000000007;
                }
            }
        }

        return dp[n];
    }
}
