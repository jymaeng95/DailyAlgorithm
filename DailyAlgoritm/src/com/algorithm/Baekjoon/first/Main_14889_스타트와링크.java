package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {
    private static int[][] score;
    private static boolean[] visited;
    private static int N;
    private static int minAbility;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        score = new int[N][N];
        visited = new boolean[N];
        minAbility = Integer.MAX_VALUE;

        for(int i =0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0;j<N;j++){
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);

        bw.write(String.valueOf(minAbility));
        br.close();
        bw.close();
    }

    private static void comb(int start, int length) {
        if (length == N / 2) {
            getMinAbility();
            return;
        }
        for(int i = start;i<N;i++){
            visited[i] = true;
            comb(i+1,length+1);
            visited[i] = false;
        }
    }

    private static void getMinAbility() {
        List<Integer> start = new ArrayList<>();
        List<Integer> link = new ArrayList<>();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) start.add(i);
            else link.add(i);
        }

        int startSum = 0;
        int linkSum = 0;
        for (int i = 0; i < start.size(); i++) {
            for (int j = 0; j < start.size(); j++) {
                int x1 = start.get(i);
                int y1 = start.get(j);
                int x2 = link.get(i);
                int y2 = link.get(j);

                startSum += score[x1][y1];
                linkSum += score[x2][y2];
            }
        }
        minAbility = Math.min(minAbility, Math.abs(startSum - linkSum));
    }
}
