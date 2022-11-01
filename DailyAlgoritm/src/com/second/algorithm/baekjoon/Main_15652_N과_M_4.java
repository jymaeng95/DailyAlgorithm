package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15652_N과_M_4 {
    private static int N, M;
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] nums = new int[M];

        makeSubsequence(0, nums);
        System.out.println(builder.toString());

        br.close();
    }

    private static void makeSubsequence(int count, int[] nums) {
        if (count == M) {
            for (int num : nums) {
                builder.append(num).append(" ");
            }
            builder.append("\n");

            return;
        }

        for (int num = 1; num <= N; num++) {
            if(count == 0 || nums[count - 1] <= num) {
                nums[count] = num;
                makeSubsequence(count + 1, nums);
            }
        }
    }
}
