package com.algorithm.Programmers.Lv2;

public class Solution_12913 {

    public static void main(String[] args) {
        int[][] land = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
        System.out.println(solution(land));
    }
    static int solution(int[][] land) {

        int[][] dp = new int[land.length][4];
        int idx = dp.length-1;

        for(int i=0;i<4;i++)
            dp[0][i] = land[0][i];

        for(int i=1;i<land.length;i++){
            for(int j=0;j<4;j++){
                int max = 0;
                //점화식 max(memo한 부분과 현재 값을 더해 최대값)
                for(int k=0;k<4;k++){
                    if(j!=k)
                        max = Math.max(max, land[i][j] + dp[i-1][k]);
                }
                dp[i][j] = max;
            }
        }

        return Math.max(Math.max(dp[idx][0],dp[idx][1]),Math.max(dp[idx][2],dp[idx][3]));
    }
}
