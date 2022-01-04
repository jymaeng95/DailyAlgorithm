package com.algorithm.thisiscodingtest.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P385_Q37_플로이드 {
    private static int[][] city;
    private static final int INF = (int) 1e9;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());        //vertex
        M = Integer.parseInt(br.readLine());        //edge

        city = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(city[i], INF);
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            //출발 도착이 동일한 경우가 있음
            if(city[fr][to] == INF) city[fr][to] = dist;
            else city[fr][to] = Math.min(city[fr][to], dist);
        }
        floyd();

        for(int i =1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(city[i][j] == INF) System.out.print(0+ " ");
                else System.out.print(city[i][j]+" ");
            }
            System.out.println();
        }
        br.close();
    }

    private static void floyd() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if(j==k){
                        city[j][k] = 0;
                        continue;
                    }
                    city[j][k] = Math.min(city[j][k], city[j][i] + city[i][k]);
                }
            }
        }
    }
}
