package com.second.algorithm.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15649_N과_M_1 {
    private static int N, M;
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] check = new boolean[N + 1];
        String builder = "";
        makeSubsequence(0, check, builder);

        bw.flush();
        br.close();
    }

    private static void makeSubsequence(int count, boolean[] check, String builder) throws IOException {
        if (count == M) {
            bw.write(builder);
            bw.newLine();

            return;
        }

        for (int number = 1; number <= N; number++) {
            if (!check[number]) {
                check[number] = true;
                makeSubsequence(count + 1, check, builder + number + " ");
                check[number] = false;
            }
        }
    }
}
