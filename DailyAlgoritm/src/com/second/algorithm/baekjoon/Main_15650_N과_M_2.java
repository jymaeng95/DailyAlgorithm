package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_N과_M_2 {
    private static int N, M;
    private static final StringBuilder builder = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] check = new boolean[N];
        int[] nums = new int[M];
        makeSubsequence(1, 0, nums, check);

        System.out.println(builder.toString());
        br.close();
    }

    private static void makeSubsequence(int start, int count, int[] nums, boolean[] check) {
        if (count == M) {
            for (int num : nums) {
                builder.append(num).append(" ");
            }
            builder.append("\n");
            return;
        }

        for (int num = start; num <= N; num++) {
            if (!check[num - 1]) {
                check[num - 1] = true;
                nums[count] = num;

                makeSubsequence(num + 1, count + 1, nums, check);
                check[num - 1] = false;
            }
        }
    }
}
