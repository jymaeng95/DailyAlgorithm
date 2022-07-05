package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1182_부분수열의_합 {
    private static int N, S;
    private static int count;
    private static int[] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        seq = new int[N];
        count = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }


        subSequence(0, 0);
        if(S==0)
            count--;
        bw.write(String.valueOf(count));

        br.close();
        bw.close();
    }

    private static void subSequence(int start, int sum) {
        if(start >= N) {
            if (sum == S) {
                count++;
            }
            return;
        }
        subSequence(start+1,sum);
        subSequence(start+1,sum+seq[start]);
    }
}
