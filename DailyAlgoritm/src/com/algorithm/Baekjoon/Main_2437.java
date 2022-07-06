package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;

public class Main_2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] weight = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.parallelSort(weight);

        int target = 1;
        for (int i = 0; i < N; i++) {
            if (target < weight[i])
                break;
            target += weight[i];
        }
        bw.write(String.valueOf(target));

        br.close();
        bw.close();
    }
}
