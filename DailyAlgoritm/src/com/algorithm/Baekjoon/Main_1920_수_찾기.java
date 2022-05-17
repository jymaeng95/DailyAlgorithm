package com.algorithm.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1920_수_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] base = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) base[index] = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        int[] find = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int index = 0; index < M; index++) find[index] = Integer.parseInt(st.nextToken());

        Arrays.parallelSort(base);
        for (int num : find) {
            int rst = binarySearch(num, base, N);
            bw.write(String.valueOf(rst));
            bw.newLine();
        }

        bw.flush();
        br.close();
    }

    private static int binarySearch(int num, int[] base, int N) {
        int low = 0;
        int high = N - 1;

        if (base[low] > num || base[high] < num) return 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (base[mid] == num) return 1;
            else if (base[mid] < num) low = mid + 1;
            else if (base[mid] > num) high = mid - 1;
        }
        return 0;
    }
}
