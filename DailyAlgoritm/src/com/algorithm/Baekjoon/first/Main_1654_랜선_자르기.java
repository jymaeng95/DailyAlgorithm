package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1654_랜선_자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] lengths = new int[N];
        for (int lan = 0; lan < N; lan++) {
            lengths[lan] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lengths);
        long rst = cutLan(N, K, lengths);
        System.out.println(rst);

        br.close();
    }

    private static long cutLan(int n, int k, int[] lengths) {
        long minLength = 1;
        long maxLength = lengths[n - 1];

        long cutLength = 1;
        while (minLength <= maxLength) {
            long mid = (minLength + maxLength) / 2;
            long count = 0;
            for (int length : lengths) {
                if(mid <= length) {
                    count += length / mid;
                }
            }

            if(count >= k) {
                cutLength = Math.max(cutLength, mid);
                minLength = mid + 1;
            } else {
                maxLength = mid - 1;
            }
        }

        return cutLength;
    }
}
