package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15652_N과M_4 {
    private static int N,M;
    private static BufferedWriter bw;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];

        comb(new StringBuilder(),1,0);

        bw.flush();
        br.close();
        bw.close();
    }

    private static void comb(StringBuilder sb, int start, int length) throws IOException {
        if(length == M){
            for(char c : sb.toString().toCharArray()){
                bw.write(c+" ");
            }
            bw.newLine();
            return;
        }

        for(int i =start;i<=N;i++){
            sb.append(i);
            comb(sb,i,length+1);
            sb.deleteCharAt(length);
        }
    }
}
