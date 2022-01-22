package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1239_차트 {
    private static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] pct = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++) {
            pct[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            count = 0;
            boolean[] visited = new boolean[N];
            int[] seq = new int[N];
            backtracking(N, pct, visited,seq,0);

        }

        System.out.println(count);

        br.close();
    }

    private static void backtracking(int n, int[] pct, boolean[] visited, int[] seq, int lev) {
        // 퍼센티지 다 한 경우
        if(lev == n) {
            for(int x: seq) {
                System.out.print(x+" ");
            }
            System.out.println();
            check(seq,n);
        }
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                visited[i] = true;
                seq[lev] = pct[i];
                backtracking(n, pct, visited, seq, lev+1);
                visited[i] = false;
            }
        }
    }

    private static void check(int[] seq, int n) {
        int cnt = 0;
        for(int i=0;i<n/2;i++) {
            int pct = seq[i];
            for(int j=i+1;j<n;j++) {
                pct += seq[j];
                if(pct > 50) break;
                else if(pct==50) {
                    cnt++;
                    break;
                }
            }
        }
        count = Math.max(count, cnt);
    }
}
