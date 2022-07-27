package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1654_랜선_자르기 {
    private static int K, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[] length = new int[K];
        for (int lan = 0; lan < K; lan++) {
            length[lan] = Integer.parseInt(br.readLine());
        }

        long rst = getMaxLanLength(length);
        System.out.println(rst);

        br.close();
    }

    private static long getMaxLanLength(int[] length) {
        Arrays.sort(length);

        long min = 1;  // 최소 길이는 1
        long max = length[K - 1];

        long maxLength = min;
        while (min <= max) {
            long mid = (min + max) / 2;
            int count = 0;

            // 만들 수 있는 랜선 개수 확인
            for (int lanLength : length) {
                count += lanLength / mid;
            }

            // N개 보다 많이 만들 수 있는 경우 랜선 길이 늘려보기
            if(count >= N) {
                maxLength = Math.max(maxLength, mid);
                min = mid + 1;
            }
            // N개 보다 작게 만들 수 있는 경우 랜선 길이 줄이기
            else {
                max = mid - 1;
            }
        }
        return maxLength;
    }
}
