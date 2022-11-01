package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15663_N과_M_9 {
    private static int N, M;
    private static final LinkedHashSet<String> set = new LinkedHashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int index = 0; index < N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int[] values = new int[M];
        boolean[] check = new boolean[N];

        makeSubsequence(0, nums, values, check);
        set.forEach(System.out::println);
        br.close();
    }

    private static void makeSubsequence(int count, int[] nums, int[] values, boolean[] check) {
        if (count == M) {
            final StringBuilder builder = new StringBuilder();
            for (int value : values) {
                builder.append(value).append(" ");
            }
            set.add(builder.toString());

            return;
        }

        for (int index = 0; index < N; index++) {
            if (!check[index]) {
                check[index] = true;
                values[count] = nums[index];
                makeSubsequence(count + 1, nums, values, check);
                check[index] = false;
            }
        }
    }
}
