package com.algorithm.thisiscodingtest.graphtheory;

import java.io.*;
import java.util.StringTokenizer;

public class P273_Union_Find {
    private static int V,E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int[] parent = new int[V+1];

        // 루트 배열 자기자신으로 초기화
        for(int i =1;i<=V;i++) {
            parent[i] = i;
        }

        //union 연산 시작
        for(int i=0; i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(parent,a,b);
        }

        bw.write("각 원소가 속한 집합 : ");
        for(int i=1;i<=V;i++)
            bw.write(find(parent,i) + " ");
        bw.flush();
        br.close();
        bw.close();
    }

    // 두 원소가 속한 집합 합치기
    private static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        // 원소가 작은 것을 루트로
        if(a > b)
            parent[a] = b;
        else
            parent[b] =  a;
    }

    private static int find(int[] parent, int x) {
        /* 루트 노드 찾을 때까지 재귀 (경로 압축 x)
        if(parent[x] != x) return find(parent, parent[x]);
        return x;
        */
        if(parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }
}
