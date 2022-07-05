package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1987_알파벳 {
    private static int R, C;
    private static boolean[][] visited;
    private static String[][] board;
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new String[R][C];
        visited = new boolean[R][C];
        count = 0;
        for (int i = 0; i < R; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < arr.length; j++)
                board[i][j] = arr[j];
        }
        StringBuilder sb = new StringBuilder();
        dfs(0, 0, sb);
        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }

    private static void dfs(int x, int y, StringBuilder sb) {
        //이미 나온 문자가 나오는 경우
        if (sb.toString().contains(board[x][y])) {
            //현재 문자열의 길이와 이전 길이의 최댓값
            count = Math.max(count,sb.length());
            return;
        }
        if (visited[x][y])
            return;
        //모든 문자열이 겹치지 않는 경우
        if(sb.length()+1 == R*C) {
            count = R * C;
            return;
        }

        visited[x][y] = true;
        sb.append(board[x][y]);
        for (int i = 0; i < DX.length; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];
            if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny]) {
                dfs(nx, ny, sb);
            }
        }
        //현재 인덱스 문자열 삭제
        sb.delete(sb.length() - 1, sb.length());
        visited[x][y] = false;
    }
}
