package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15650_N과M_2 {
    private static int N, M;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];

        comb(0, 0);

        br.close();
        bw.close();
    }

    private static void comb(int start, int length) {
        if (length == M) {
            printSeq();
            return;
        }
        for (int i = start; i < visited.length; i++) {
            visited[i] = true;
            comb(i + 1, length + 1);
            visited[i] = false;
        }
    }

    private static void printSeq() {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i])
                System.out.print(i + 1 + " ");
        }
        System.out.println();
    }
}
