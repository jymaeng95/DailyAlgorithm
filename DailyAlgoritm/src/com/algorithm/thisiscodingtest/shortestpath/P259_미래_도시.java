package com.algorithm.thisiscodingtest.shortestpath;

import java.io.*;
import java.util.StringTokenizer;

public class P259_미래_도시 {
    private static int[][] city;
    private static int N, M;
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       //vertex
        M = Integer.parseInt(st.nextToken());       //edge
        city = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j)
                    city[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            city[from][to] = 1;
            city[to][from] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int rst = getMinPath(X, K);

        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getMinPath(int x, int k) {
        // 1 -> k -> x
        for (int c = 1; c <= N; c++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    city[a][b] = Math.min(city[a][b], city[a][c] + city[c][b]);
                }
            }
        }
        int rst = city[1][k] + city[k][x];
        return rst >= INF ? -1 : rst;
    }
}
