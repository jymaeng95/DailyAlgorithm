package com.algorithm.thisiscodingtest.DFS;

import java.io.*;
import java.util.StringTokenizer;

public class P351_Q19_연산자_끼워_넣기 {
    private static int min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        /*
         * N = 수의 개수
         * 2째줄 : 수
         * 3째줄 : + - * / 순서 갯수 (N-1개)
         */

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(num, op, num[0], 1, N);

        bw.write(String.valueOf(max));
        bw.newLine();
        bw.write(String.valueOf(min));
        bw.flush();
        br.close();
        bw.close();
    }


    private static void dfs(int[] num, int[] op, int rst, int i, int n) {
        // 숫자 개수 만큼 탐색한 경우
        if (i == n) {
            min = Math.min(min, rst);
            max = Math.max(max, rst);
            return;
        }

        // 연산자 1이상인 경우로
        if (op[0] > 0) {
            op[0]--;
            dfs(num, op, rst + num[i], i + 1, n);
            op[0]++;
        }

        if (op[1] > 0) {
            op[1]--;
            dfs(num, op, rst - num[i], i + 1, n);
            op[1]++;
        }

        if (op[2] > 0) {
            op[2]--;
            dfs(num, op, rst * num[i], i + 1, n);
            op[2]++;
        }

        if (op[3] > 0) {
            op[3]--;
            dfs(num, op, rst / num[i], i + 1, n);
            op[3]++;
        }
    }
}
