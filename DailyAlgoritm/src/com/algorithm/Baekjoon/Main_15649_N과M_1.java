package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15649_N과M_1 {
    private static int N,M;
    private static boolean[] visited;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        arr = new int[M];

        findSequence(0);
        br.close();
    }

    private static void findSequence(int length) {
        if(length == M){
            printSequence();
            return;
        }
        for(int i = 1;i<=N;i++){
            if(!visited[i]) {
                arr[length] = i;
                visited[i] = true;
                findSequence(length + 1);
                visited[i] = false;
            }
        }
    }

    private static void printSequence() {
        for(int x :  arr)
            System.out.print(x+" ");
        System.out.println();
    }
}
