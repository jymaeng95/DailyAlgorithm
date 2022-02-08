package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1719_택배 {
    private static int N,M;
    private static final int INF = 1001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        char[][] point = new char[N+1][N+1];
        for(int i=1;i<=N;i++) {
            Arrays.fill(point[i], '-');
        }
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            dist[fr][to] = time;
            dist[to][fr] = time;
            point[fr][to] = Character.forDigit(to,10);
            point[to][fr] = Character.forDigit(fr,10);
        }
        floydWarshall(dist,point);
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                System.out.print(point[i][j]+ " ");
            }
            System.out.println();
        }
        br.close();
    }

    private static void floydWarshall(int[][] dist, char[][] point) {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                for(int k=1;k<=N;k++) {
                    if(j==k) continue;
                    if(dist[j][k] > dist[j][i] + dist[i][k]) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                        if(point[j][k] == '-')
                            point[j][k] = Character.forDigit(i,10);
                    }
                }
            }
        }
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                System.out.print(dist[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
