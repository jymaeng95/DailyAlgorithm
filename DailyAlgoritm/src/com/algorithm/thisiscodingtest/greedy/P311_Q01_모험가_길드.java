package com.algorithm.thisiscodingtest.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P311_Q01_모험가_길드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] scared = new int[N];
        for(int i=0;i<N;i++) {
            scared[i] = Integer.parseInt(st.nextToken());
        }
        int count = getMaxGroup(N, scared);
        bw.write(String.valueOf(count));
        bw.flush();

        br.close();
        bw.close();

    }

    private static int getMaxGroup(int n, int[] scared) {
        int rst = 0;
        int minPerson = 0;
        Arrays.parallelSort(scared);

        for(int i=0;i<n; i++) {
            minPerson++ ;
            if(minPerson >= scared[i]) {
                rst++;
                minPerson = 0;
            }
        }
        return rst;
    }
}
