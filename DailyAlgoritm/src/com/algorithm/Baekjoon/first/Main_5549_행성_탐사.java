package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_5549_행성_탐사 {
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        int[][] jungle = new int[M + 1][N + 1];
        int[][] ocean = new int[M + 1][N + 1];
        int[][] ice = new int[M + 1][N + 1];

        for (int row = 1; row <= M; row++) {
            String[] split = br.readLine().split("");
            for (int col = 1; col <= N; col++) {
                if (split[col - 1].equals("J")) jungle[row][col] = 1;
                else if (split[col - 1].equals("O")) ocean[row][col] = 1;
                else ice[row][col] = 1;
            }
        }

        int[][] sumJungle = getPrefixSum(jungle);
        int[][] sumOcean = getPrefixSum(ocean);
        int[][] sumIce = getPrefixSum(ice);

        for (int loop = 1; loop <= K; loop++) {
            String rst = getPlanetInfo(sumJungle, sumOcean, sumIce);
            bw.write(rst);
            bw.newLine();

        }
        bw.flush();
        br.close();
    }

    private static String getPlanetInfo(int[][] sumJungle, int[][] sumOcean, int[][] sumIce) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        StringBuilder rst = new StringBuilder();
        return rst.append(getCount(x1, y1, x2, y2, sumJungle)).append(" ")
                .append(getCount(x1, y1, x2, y2, sumOcean)).append(" ")
                .append(getCount(x1, y1, x2, y2, sumIce)).toString();
    }

    private static int getCount(int x1, int y1, int x2, int y2, int[][] prefix) {
        return prefix[x2][y2] - (prefix[x1 - 1][y2] + prefix[x2][y1 - 1] - prefix[x1 - 1][y1 - 1]);
    }

    private static int[][] getPrefixSum(int[][] map) {
        int[][] prefix = new int[map.length][map[0].length];

        for (int row = 1; row < map.length; row++) {
            for (int col = 1; col < map[row].length; col++) {
                prefix[row][col] = prefix[row][col - 1] + map[row][col];
            }
        }

        for (int col = 1; col < map[0].length; col++) {
            for (int row = 1; row < map.length; row++) {
                prefix[row][col] += prefix[row - 1][col];
            }
        }

        return prefix;
    }
}
