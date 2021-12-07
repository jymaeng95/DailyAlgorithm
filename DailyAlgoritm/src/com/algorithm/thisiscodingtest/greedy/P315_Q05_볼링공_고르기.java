package com.algorithm.thisiscodingtest.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class P315_Q05_볼링공_고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] weight = new int[N];

        st= new StringTokenizer(br.readLine());
        for(int i =0;i<N;i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        int rst = N * (N-1) / 2 - (N-M);
        bw.write(String.valueOf(rst));
        br.close();
        bw.close();
    }
}
