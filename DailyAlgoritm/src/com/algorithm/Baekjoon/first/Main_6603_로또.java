package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_6603_로또 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            if(length == 0)
                break;

            int[] set = new int[length];
            boolean[] visited = new boolean[length];
            for(int i =0;i<length;i++){
                set[i] = Integer.parseInt(st.nextToken());
            }

            comb(set,visited,0,0);
            System.out.println();
        }

        br.close();
    }

    private static void comb(int[] set, boolean[] visited, int start, int length) {
        if(length == 6){
            getLotto(set,visited);
            return;
        }

        for(int i = start;i<set.length;i++){
            visited[i] = true;
            comb(set,visited,i+1,length+1);
            visited[i] = false;
        }
    }

    private static void getLotto(int[] set, boolean[] visited) {
        for(int i =0;i<visited.length;i++){
            if(visited[i])
                System.out.print(set[i]+" ");
        }
        System.out.println();
    }
}
