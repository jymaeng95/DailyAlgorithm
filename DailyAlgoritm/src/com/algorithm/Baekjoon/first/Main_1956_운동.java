package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1956_운동 {
    private static final int INF = (int) 1e9;
    private static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[][] village = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(village[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            village[fr][to] = dist;
        }

        int rst = getRoadCycle(village);
        System.out.println(rst);

        br.close();
    }

    private static int getRoadCycle(int[][] village) {
        int count = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    if (j == k) {
                        village[j][k] = 0;
                        continue;
                    }
                    village[j][k] = Math.min(village[j][k], village[j][i] + village[i][k]);
                }
            }
        }

        // 싸이클 체크
        for (int i = 1; i <= V; i++) {
            for (int j = i+1; j <= V; j++) {
                if(village[i][j] > 0 && village[j][i] > 0) {
                    count = Math.min(count, village[i][j] + village[j][i]);
                }
            }
        }

        return count >= INF ? -1 : count;
    }
}
