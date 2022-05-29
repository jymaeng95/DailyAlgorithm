package com.algorithm.Programmers.test;

public class Solution_4_NHN {
    public static void main(String[] args) {
        long[] players = {10, 11, 15, 14, 16, 18, 19, 20};
        int power = 10;
        int k = 2;

        long rst = solution(players, power, k);
        System.out.println(rst);
    }

    //    private static long solution(long[] players, int power, int k) {
//        final int WIN = 0;
//        final int LOSE = 1;
//        long[][] dp = new long[2][players.length + 1];
//
//        dp[WIN][0] = dp[LOSE][0] = power;
//        int continuousWin = 0;
//        for (int player = 1; player <= players.length; player++) {
//            // 승리의 경우 -> 이전에 이긴 경우, 진 경우에서 최대 값이 내가 가질 공격력
//            if (players[player - 1] <= Math.max(dp[WIN][player - 1], dp[LOSE][player - 1])) {
//                continuousWin++;        // 연승
//                dp[WIN][player] = Math.max(dp[WIN][player - 1] + continuousWin, dp[LOSE][player - 1] + 1);
//            } else {
//                // 연승 깨지는 경우
//                continuousWin = 0;
//                dp[WIN][player] = Math.max(dp[WIN][player - 1], dp[LOSE][player - 1]) + k;
//            }
//
//            // 고의 패배의 경우
//                dp[LOSE][player] = Math.max(dp[WIN][player - 1], dp[LOSE][player - 1]) + k;
//        }
    private static long solution(long[] players, int power, int k) {
        final int WIN = 0;
        final int FORCE_LOSE = 1;
        final int LOSE = 2;
        long[][] dp = new long[3][players.length + 1];

        dp[WIN][0] = dp[FORCE_LOSE][0] = dp[LOSE][0] = power;
        int continuousWin = 0;

        for (int player = 1; player <= players.length; player++) {
            // 승리의 경우 (이전에 이긴 경우, 진 경우 , 강제로 진 경우)
            if (players[player - 1] <= Math.max(dp[WIN][player - 1], Math.max(dp[FORCE_LOSE][player - 1], dp[LOSE][player - 1]))) {
                // 승리
                continuousWin++;
                dp[WIN][player] = Math.max(dp[WIN][player - 1] + continuousWin, Math.max(dp[FORCE_LOSE][player - 1], dp[LOSE][player - 1]) + 1);
                dp[FORCE_LOSE][player] = Math.max(dp[WIN][player - 1], Math.max(dp[FORCE_LOSE][player - 1], dp[LOSE][player - 1])) + k;

                if(dp[FORCE_LOSE][player] > dp[WIN][player]) {
                    continuousWin = 0;
                    dp[WIN][player] = dp[FORCE_LOSE][player];
                }
            } else {
                continuousWin = 0;
                dp[LOSE][player] = Math.max(dp[WIN][player - 1], Math.max(dp[FORCE_LOSE][player - 1], dp[LOSE][player - 1])) + k;
            }
            dp[FORCE_LOSE][player] = Math.max(dp[WIN][player - 1], Math.max(dp[FORCE_LOSE][player - 1], dp[LOSE][player - 1])) + k;
        }

        return Math.max(dp[WIN][players.length], Math.max(dp[LOSE][players.length], dp[FORCE_LOSE][players.length]));
    }
}
