package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1943_동전_분배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int loop = 1; loop <= 3; loop++) {
            int coins = Integer.parseInt(br.readLine());
            int[][] info = new int[coins][2];

            int sum = 0;
            for (int coin = 0; coin < coins; coin++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int counts = Integer.parseInt(st.nextToken());

                info[coin][0] = value;
                info[coin][1] = counts;
                sum += value * counts;
            }

            int rst = 0;
            if (sum % 2 == 0)
                rst = divideHalf(sum / 2, info);
            System.out.println(rst);
        }

        br.close();
    }

    private static int divideHalf(int half, int[][] info) {
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;
        for (int[] coins : info) {
            int coin = coins[0];
            int counts = coins[1];

            for (int value = 0; value <= half; value++) {
                for (int count = 1; count <= counts; count++) {
                    if (dp[value] && value + coin * count <= half) {
                        dp[value + coin * count] = true;
                    }
                }
            }
        }
        return dp[half] ? 1 : 0;
    }
}
