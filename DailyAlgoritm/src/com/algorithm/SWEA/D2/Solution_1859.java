package com.algorithm.SWEA.D2;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_1859 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] price = new int[N];
            for (int j = 0; j < N; j++)
                price[j] = Integer.parseInt(st.nextToken());

            long sum = 0;
            int max = price[N - 1];
            for (int j = N - 1; j >= 0; j--) {
                if (price[j] <= max) {
                    sum += max - price[j];
                    continue;
                }
                max = price[j];
            }
            bw.write("#" + i + " " + sum);
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}
