package com.algorithm.Programmers.Lv3;

public class Solution_42895_N으로_표현_풀이중 {
    public static void main(String[] args) {
        int N = 2;
        int number = 11;
        int rst = solution(N, number);
        System.out.println("rst = " + rst);
    }

    private static int count;

    private static int solution(int n, int number) {
        long[] dp = new long[10];
        count = 9;
        dp[1] = n;

        dfs(1, n, number, dp);
        return count > 8 ? -1 : count;
    }

    private static void dfs(int depth, int n, int number, long[] dp) {
        if (depth > 9) {
            return;
        }

        if (dp[depth] == number) {
            count = Math.min(count, depth);
            return;
        }

        for(int loop = 1; loop <= 8 - depth; loop++) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(n).repeat(loop));

            dp[depth] = dp[depth - 1] + Integer.parseInt(sb.toString());
            dfs(depth + loop, n, number, dp);

            dp[depth] = dp[depth - 1] - Integer.parseInt(sb.toString());
            dfs(depth + loop, n, number, dp);

            dp[depth] = dp[depth - 1] * Integer.parseInt(sb.toString());
            dfs(depth + loop, n, number, dp);

            if (dp[depth - 1] > 0) {
                dp[depth] = dp[depth - 1] / Integer.parseInt(sb.toString());
                dfs(depth + loop, n, number, dp);
            }
        }
    }
}
