package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865_평범한_배낭 {
    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        TravelSupply[] suplies = new TravelSupply[N];
        for (int index = 0; index < N; index++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            suplies[index] = new TravelSupply(weight, value);
        }

        int maxValue = fillGoods(suplies);
        System.out.println(maxValue);

        br.close();
    }

    private static int fillGoods(TravelSupply[] supplies) {
        int[][] dp = new int[N + 1][K + 1];

        for (int index = 1; index <= N; index++) {
            TravelSupply supply = supplies[index - 1];
            int supplyWeight = supply.getWeight();

            for (int weight = 0; weight <= K; weight++) {
                dp[index][weight] = Math.max(dp[index - 1][weight], dp[index][weight]);
                if(weight + supplyWeight <= K)
                    dp[index][weight + supplyWeight] = Math.max(dp[index - 1][weight + supplyWeight], dp[index - 1][weight] + supply.getValue());
            }
        }

        return dp[N][K];
    }

    static class TravelSupply {
        private final int weight;
        private final int value;

        public TravelSupply(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }
    }
}
