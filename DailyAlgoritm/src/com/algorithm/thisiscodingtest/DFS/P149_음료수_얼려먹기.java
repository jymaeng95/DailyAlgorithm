package com.algorithm.thisiscodingtest.DFS;

import java.io.*;
import java.util.StringTokenizer;

public class P149_음료수_얼려먹기 {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[][] ice = new int[y][x];
        boolean[][] visited = new boolean[y][x];

        for (int i = 0; i < ice.length; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < ice[i].length; j++) {
                ice[i][j] = Integer.parseInt(split[j]);
            }
        }

        int count = makeIceCream(ice, visited);
        System.out.println("count = " + count);
    }

    private static int makeIceCream(int[][] ice, boolean[][] visited) {
        int count = 0;
        for (int i = 0; i < ice.length; i++) {
            for (int j = 0; j < ice[i].length; j++) {
                if (!visited[i][j] && ice[i][j] == 0) {
                    dfs(i, j, visited, ice);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int i, int j, boolean[][] visited, int[][] ice) {

        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nx = dx[k] + i;
            int ny = dy[k] + j;
            if (nx >= 0 && nx < ice.length && ny >= 0 && ny < ice[0].length && !visited[nx][ny] && ice[nx][ny] == 0)
                dfs(nx, ny, visited, ice);
        }
    }
}
