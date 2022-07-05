package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1026_보물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N  =Integer.parseInt(br.readLine());
        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int rst = getTreasure(N,A,B);
        System.out.println(rst);
        br.close();
    }

    private static int getTreasure(int n, Integer[] a, Integer[] b) {
        Arrays.parallelSort(a);
        Arrays.parallelSort(b, Collections.reverseOrder());
        int sum = 0;
        for(int i =0;i<n;i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}
