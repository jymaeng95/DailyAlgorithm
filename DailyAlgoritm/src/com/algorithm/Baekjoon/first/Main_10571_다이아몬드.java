package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_10571_다이아몬드 {
    static class Diamond {
        double weight;
        double clear;

        public Diamond(double weight, double clear) {
            this.weight = weight;
            this.clear = clear;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            List<Diamond> diamonds = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                diamonds.add(new Diamond(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
            }

            int rst = getBestDiamonds(N, diamonds);
            bw.write(String.valueOf(rst));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static int getBestDiamonds(int n, List<Diamond> diamonds) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = dp[0];
        for (int i = 1; i < diamonds.size(); i++) {
            double targetWeight = diamonds.get(i).weight;
            double targetClear = diamonds.get(i).clear;
            for (int j = 0; j < i; j++) {
                double prevWeight = diamonds.get(j).weight;
                double prevClear = diamonds.get(j).clear;
                if (prevWeight < targetWeight && prevClear > targetClear)
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
