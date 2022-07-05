package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15486_퇴사_2 {
    static class Company {
        int time;
        int pay;

        public Company(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<Company> company = new ArrayList<>();
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            company.add(new Company(time, pay));

        }

        int rst = getMaxPay(dp, n, company);
        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getMaxPay(int[] dp, int n, List<Company> company) {
        int max = dp[0];
        for (int i = 1; i <= dp.length; i++) {
            int time = company.get(i-1).time;
            int pay = company.get(i-1).pay;
            int suc = i + time;
            max = dp[i];
            if (suc <= n) {
                dp[suc] = Math.max(dp[suc], max + pay);

            }
        }

        return dp[n];
    }
}
