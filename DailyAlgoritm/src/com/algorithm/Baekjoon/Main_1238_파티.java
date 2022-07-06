package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1238_파티 {

    private static int N, M, X;
    private static final int INF = 999999;
    private static int[][] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        route = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++)
                route[i][j] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            route[start][end] = dist;
        }

        floyd_warshall();

        int max = Integer.MIN_VALUE;
        for(int i=1;i<route.length;i++){
            if(i==X) continue;
            max = Math.max(max,route[i][X]+route[X][i]);
        }

        bw.write(String.valueOf(max));
        bw.close();
        br.close();
    }

    private static void floyd_warshall() {
        for (int i = 1; i < route.length; i++) {
            for (int j = 1; j < route.length; j++) {
                for (int k = 1; k < route.length; k++) {
                    if (route[j][k] > route[j][i] + route[i][k])
                        route[j][k] = route[j][i] + route[i][k];
                }
            }
        }
    }
}
