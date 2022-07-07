package com.second.algorithm.programmers;

public class Solution_12913_땅따먹기 {
    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};

        int rst = solution(land);
        int rst2 = test(land);
        System.out.println(rst);
        System.out.println(rst2);
    }

    private static int test(int[][] land) {
        int[][] dp = new int[land.length][4];

        // dp 초기화
        System.arraycopy(land[0], 0, dp[0], 0, 4);

        recursive(1, dp, land);

        // 마지막 스텝에서의 최대값을 확인한다.
        int maxScore = 0;
        for (int score : dp[land.length - 1]) {
            maxScore = Math.max(maxScore, score);
        }

        return maxScore;
    }

    // 재귀
    private static void recursive(int step, int[][] dp, int[][] land) {
        if(step == land.length) return;

        dp[step][0] = land[step][0] + Math.max(dp[step - 1][1], Math.max(dp[step - 1][2], dp[step - 1][3]));
        dp[step][1] = land[step][1] + Math.max(dp[step - 1][2], Math.max(dp[step - 1][3], dp[step - 1][0]));
        dp[step][2] = land[step][2] + Math.max(dp[step - 1][3], Math.max(dp[step - 1][0], dp[step - 1][1]));
        dp[step][3] = land[step][3] + Math.max(dp[step - 1][0], Math.max(dp[step - 1][1], dp[step - 1][2]));

        recursive(step+1, dp, land);
    }

    private static int solution(int[][] land) {
        int[][] dp = new int[land.length][4];

        // dp 초기화
        System.arraycopy(land[0], 0, dp[0], 0, 4);

        // 현재 라인의 최대값은 (자신이 밟은 라인 + 자신이 밟은 라인을 제외한 이전의 스텝의 최대값)
        for (int step = 1; step < land.length; step++) {
            dp[step][0] = land[step][0] + Math.max(dp[step - 1][1], Math.max(dp[step - 1][2], dp[step - 1][3]));
            dp[step][1] = land[step][1] + Math.max(dp[step - 1][2], Math.max(dp[step - 1][3], dp[step - 1][0]));
            dp[step][2] = land[step][2] + Math.max(dp[step - 1][3], Math.max(dp[step - 1][0], dp[step - 1][1]));
            dp[step][3] = land[step][3] + Math.max(dp[step - 1][0], Math.max(dp[step - 1][1], dp[step - 1][2]));
        }

        // 마지막 스텝에서의 최대값을 확인한다.
        int maxScore = 0;
        for (int score : dp[land.length - 1]) {
            maxScore = Math.max(maxScore, score);
        }

        return maxScore;
    }
}
