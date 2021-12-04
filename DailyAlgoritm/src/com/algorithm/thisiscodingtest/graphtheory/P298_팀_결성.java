package com.algorithm.thisiscodingtest.graphtheory;

import java.io.*;
import java.util.StringTokenizer;

public class P298_팀_결성 {
    private static int V, E;
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        for(int i=1;i<=V;i++) {
            parent[i] = i;
        }

        // 0 : 팀 합치기, 1 : 같은 팀 여부 확인
        for(int i=0; i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(mode == 0) union(a,b,parent);
            if(mode == 1) {
                if(find(a,parent) == find(b,parent))
                    bw.write("YES");
                else bw.write("NO");
                bw.newLine();
            }
        }
        br.close();
        bw.close();
    }

    private static void union(int a, int b, int[] parent) {
        a = find(a,parent);
        b = find(b,parent);
        if(a > b) parent[a] = b;
        else parent[b] =a;
    }

    private static int find(int x, int[] parent) {
        if(parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];

    }
}
