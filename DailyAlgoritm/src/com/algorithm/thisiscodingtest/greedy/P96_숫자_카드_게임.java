package com.algorithm.thisiscodingtest.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P96_숫자_카드_게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] card = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                card[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = getCardNumber(card);
        bw.write(String.valueOf(answer));
        bw.flush();

        bw.close();
        br.close();
    }

    private static int getCardNumber(int[][] card) {
        int rst = 0;

        for(int[] c : card) {
            Arrays.parallelSort(c);
            if(rst < c[0])
                rst = c[0];
        }

        return rst;
    }
}
