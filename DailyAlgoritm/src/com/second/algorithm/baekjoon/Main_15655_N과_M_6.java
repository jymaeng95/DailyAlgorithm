package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15655_N과_M_6 {
    private static int N, M;
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        int[] values = new int[M];
        boolean[] check = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        makeSubsequence(0, 0, nums, values, check);
        System.out.println(builder.toString());

        br.close();
    }

    private static void makeSubsequence(int start, int count, int[] nums, int[] values, boolean[] check) {
        if (count == M) {
            for (int value : values) {
                builder.append(value).append(" ");
            }
            builder.append("\n");

            return;
        }

        for (int idx = start; idx < N; idx++) {
            if (!check[idx]) {
                check[idx] = true;
                values[count] = nums[idx];
                makeSubsequence(idx + 1, count + 1, nums, values, check);
                check[idx] = false;
            }
        }
    }
}
