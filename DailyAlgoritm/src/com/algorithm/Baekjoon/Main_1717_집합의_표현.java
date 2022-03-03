package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_집합의_표현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N+1];
        for(int num = 1; num <= N; num++) {
            parent[num] = num;
        }

        for(int loop = 0; loop < M ;loop++) {
            st = new StringTokenizer(br.readLine());
            int opr = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //union
            if(opr == 0) {
                union(a,b,parent);
            }
            //find
            else {
                int aParent = find(a,parent);
                int bParent = find(b,parent);

                if(aParent != bParent) System.out.println("NO");
                else System.out.println("YES");
            }
        }

        br.close();
    }

    private static void union(int a, int b, int[] parent) {
        int aParent = find(a,parent);
        int bParent = find(b,parent);
        if(aParent > bParent) parent[aParent] = bParent;
        else parent[bParent] = aParent;
    }

    private static int find(int a, int[] parent) {
        if(parent[a] != a) parent[a] = find(parent[a], parent);
        return parent[a];
    }
}
