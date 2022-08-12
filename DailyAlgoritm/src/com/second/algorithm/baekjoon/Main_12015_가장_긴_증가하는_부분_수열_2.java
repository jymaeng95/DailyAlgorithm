package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12015_가장_긴_증가하는_부분_수열_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            arr[index] = Integer.parseInt(st.nextToken());
        }

        int[] rst = LIS(N, arr);
        System.out.println(rst.length);

        br.close();
    }

    private static int[] LIS(int n, int[] arr) {
        int[] lis = new int[n];
        lis[0] = arr[0];

        int next = 0;
        for (int index = 1; index < n; index++) {
            if(arr[index] > lis[next]) {
                lis[++next] = arr[index];

            } else {
                int insertIndex = binarySearch(lis, 0, next, arr[index]);
                lis[insertIndex] = arr[index];
            }
        }

        return Arrays.stream(lis).filter(x -> x > 0).toArray();
    }

    private static int binarySearch(int[] lis, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if(lis[mid] >= target)
                end = mid;
            else start = mid + 1;
        }
        return end;
    }
}
