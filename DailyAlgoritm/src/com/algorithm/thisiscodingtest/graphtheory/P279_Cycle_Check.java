package com.algorithm.thisiscodingtest.graphtheory;

import java.io.*;
import java.util.StringTokenizer;

public class P279_Cycle_Check {
    private static int V,E;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[] parent = new int[V+1];
        for(int i=1; i<=V ; i++){
            parent[i] = i;
        }

        boolean cycle = false;
        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(find(a,parent) == find(b,parent))
                cycle = true;
            else
                union(a,b,parent);
        }

        bw.write("cycle : "+cycle);
        bw.flush();
        br.close();
        bw.close();
    }

    private static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);

        if(a > b)
            parent[a] = b;
        else
            parent[b] = a;
    }

    private static int find(int x, int[] parent) {
        if(parent[x] != x)
            parent[x] = find(parent[x], parent);
        return parent[x];

    }
}
