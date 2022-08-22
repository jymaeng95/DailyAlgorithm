package com.second.algorithm.programmers;

public class Solution_118668_코딩_테스트_공부 {
    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
        int rst = solution(alp, cop, problems);

        System.out.println("rst = " + rst);
    }

    private static int[][] dp;
    private static int minTime;
    public static int solution(int alp, int cop, int[][] problems) {
        minTime = 10001;

        // 시간 별 최대 문제 풀이를 구하기 위해 
        dp = new int[2][10001];
        boolean[] visited = new boolean[problems.length];
        for(int hour = 1; hour <= 10000; hour++) {
            dp[0][hour] = dp[1][hour] = hour;
        }

        studyCodingTest(new Test(alp, cop, 0, 0), visited, problems);

        return minTime;
    }

    private static void studyCodingTest(Test test, boolean[] visited, int[][] problems) {
        int solve = test.getSolve();
        int time = test.getTime();
        System.out.println("time = " + time);
        // 모든 문제 다 풀 수 있는 경우
        if(solve == problems.length) {
            minTime = Math.min(minTime, time);
            return;
        }

        // 최소 시간보다 많이 경과한 경우
        if(time >= minTime)
            return;

        // 현재 시간 기준 최대 알고력, 코딩력 아닌 경우
        int alp = test.getAlp();
        int cop = test.getCop();
        if(dp[0][time] >= alp && dp[1][time] >= cop) return;

        dp[0][time] = Math.max(alp, dp[0][time]);
        dp[1][time] = Math.max(cop, dp[1][time]);

        // 문제 풀이
        for(int order = 0; order < problems.length; order++) {
            // 현재 알고력과 코딩력이 현재 문제를 풀 수 있는 경우
            if(alp >= problems[order][0] && cop >= problems[order][1]) {
                // 안푼 문제인 경우 (문제 푼 개수 한개 증가)
                if(!visited[order]) {
                    visited[order] = true;
                    studyCodingTest(new Test(alp + problems[order][2], cop + problems[order][3], time + problems[order][4], solve + 1), visited, problems);
                    visited[order] = false;
                }
                // 푼 문제의 경우 푼 개수 증가 X
                else {
                    studyCodingTest(new Test(alp + problems[order][2], cop + problems[order][3], time + problems[order][4], solve), visited, problems);
                }
            }
        }
/*        // 알고력만 공부 (문제 풀이 X)
        studyCodingTest(new Test(alp + 1, cop, time + 1, solve), visited, problems);

        // 코딩력만 공부 (문제 풀이 X)
        studyCodingTest(new Test(alp, cop + 1, time + 1, solve), visited, problems);*/

    }

    static class Test {
        private int alp;
        private int cop;
        private int time;
        private int solve;

        public Test(int alp, int cop, int time, int solve) {
            this.alp = alp;
            this.cop = cop;
            this.time = time;
            this.solve = solve;
        }

        public int getAlp() {
            return alp;
        }

        public int getCop() {
            return cop;
        }

        public int getTime() {
            return time;
        }

        public int getSolve() {
            return solve;
        }
    }
}
