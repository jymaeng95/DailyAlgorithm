package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13164_행복_유치원 {
    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int child = 0; child < N; child++) {
            heights[child] = Integer.parseInt(st.nextToken());
        }

        int rst = getTshirtCost(heights);
        System.out.println(rst);

        br.close();
    }

    private static int getTshirtCost(int[] heights) {
        if (N == K) return 0;
        int[] costs = new int[N - 1];
        for (int child = 1; child < N; child++) {
            costs[child - 1] = heights[child] - heights[child - 1];
        }

        Arrays.sort(costs);
        int sum = 0;
        for (int index = 0; index < N - K; index++) {
            sum += costs[index];;
        }

        return sum;
    }
}
