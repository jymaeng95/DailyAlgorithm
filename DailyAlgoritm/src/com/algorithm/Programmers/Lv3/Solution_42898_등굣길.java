package com.algorithm.Programmers.Lv3;

public class Solution_42898_등굣길 {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};

        int rst = solution(m, n, puddles);
        System.out.println("rst = " + rst);
    }

    private static final int MOD = 1000000007;
    private static int solution(int m, int n, int[][] puddles) {
        int[][] road = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        for (int[] puddle : puddles) {
            road[puddle[1]][puddle[0]] = -1;     // 연못이 있는 경우
        }

        dp[1][1] = 1;
        for(int row  = 1; row<=n; row++) {
            for(int col = 1; col <= m ; col++) {
                if(road[row][col] != -1) {
                    dp[row][col] += dp[row-1][col] % MOD + dp[row][col-1] % MOD;
                }
            }
        }

        return dp[n][m] % MOD;
    }
}
