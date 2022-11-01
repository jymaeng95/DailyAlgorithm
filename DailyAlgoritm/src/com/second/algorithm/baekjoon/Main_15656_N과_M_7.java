package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15656_N과_M_7 {
    private static int N, M;
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        int[] values = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        makeSubsequence(0, nums, values);
        System.out.println(builder.toString());

        br.close();
    }

    private static void makeSubsequence(int count, int[] nums, int[] values) {
        if (count == M) {
            for (int value : values) {
                builder.append(value).append(" ");
            }
            builder.append("\n");

            return;
        }

        for (int idx = 0; idx < N; idx++) {
                values[count] = nums[idx];
                makeSubsequence(count + 1, nums, values);
        }
    }
}
