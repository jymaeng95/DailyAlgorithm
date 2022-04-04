package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2623_음악프로그램 {
    private static List<List<Integer>> order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegree = new int[N + 1];
        order = new ArrayList<>();
        for (int singer = 0; singer <= N; singer++) {
            order.add(new ArrayList<>());
        }

        for (int pd = 0; pd < M; pd++) {
            st = new StringTokenizer(br.readLine());
            int managementSinger = Integer.parseInt(st.nextToken());
            int prevSinger = Integer.parseInt(st.nextToken());
            for (int singer = 1; singer < managementSinger; singer++) {
                int postSinger = Integer.parseInt(st.nextToken());
                order.get(prevSinger).add(postSinger);
                indegree[postSinger]++;
                prevSinger = postSinger;
            }
        }

        makeOrder(indegree, N, M);
        if(singerOrder.size() < N) System.out.println(0);
        else {
            for(int singer :  singerOrder) System.out.println(singer);
        }
        br.close();
    }

    private static List<Integer> singerOrder;
    private static void makeOrder(int[] indegree, int n, int m) {
        singerOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int singer = 1; singer <= n; singer++) {
            if (indegree[singer] == 0)
                queue.add(singer);
        }

        while (!queue.isEmpty()) {
            int curSinger = queue.poll();
            singerOrder.add(curSinger);
            for(int singer : order.get(curSinger)) {
                indegree[singer]--;
                if(indegree[singer] == 0) {
                    queue.add(singer);
                }
            }
        }
    }
}
