package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15651_N과M_3 {
    private static int N,M;
    private static boolean[] visited;
    private static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        getSeq(new StringBuilder(),0);
        bw.flush();
        br.close();
        bw.close();
    }

    private static void getSeq(StringBuilder start, int length) throws IOException {
        if(length == M){
            printSeq(start);
            return;
        }
        for(int i =1;i<=N;i++){
            start.append(i);
            getSeq(start,length+1);
            start.deleteCharAt(length);
        }
    }

    private static void printSeq(StringBuilder start) throws IOException {
        for(char c : start.toString().toCharArray()){
            bw.write(c+" ");
        }
        bw.newLine();
    }
}
