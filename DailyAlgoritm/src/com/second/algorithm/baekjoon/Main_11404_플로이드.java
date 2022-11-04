package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11404_플로이드 {
    private static int N;
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] lines = new int[N + 1][N + 1];
        for (int city = 1; city <= N; city++) {
            Arrays.fill(lines[city], INF);
        }
        for (int bus = 0; bus < M; bus++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (lines[from][to] == INF) lines[from][to] = cost;
            else lines[from][to] = Math.min(lines[from][to], cost);
        }

        getArriveInformation(lines);
        for (int departure = 1; departure <= N; departure++) {
            for (int arrive = 1; arrive <= N; arrive++) {
                if (lines[departure][arrive] == INF) lines[departure][arrive] = 0;
                System.out.print(lines[departure][arrive] + " ");
            }
            System.out.println();
        }
        br.close();
    }

    private static void getArriveInformation(int[][] lines) {
        for (int mid = 1; mid <= N; mid++) {
            for (int departure = 1; departure <= N; departure++) {
                for (int arrive = 1; arrive <= N; arrive++) {
                    if (departure == arrive) lines[departure][arrive] = 0;
                    else
                        lines[departure][arrive] = Math.min(lines[departure][arrive], lines[departure][mid] + lines[mid][arrive]);
                }
            }
        }
    }
}
