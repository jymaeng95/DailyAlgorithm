package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1911_흙길_보수하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        PriorityQueue<Pool> pq = new PriorityQueue<>();
        for (int loop = 0; loop < N; loop++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Pool(start, end));
        }

        int rst = repairRoad(pq, N, L);
        System.out.println(rst);
        br.close();
    }

    private static int repairRoad(PriorityQueue<Pool> pq, int n, int l) {
        int count = 0;
        int end = 0;

        while(!pq.isEmpty()) {
            Pool pool = pq.poll();
            // 웅덩이 시작 보다
            if(pool.getStart() > end) {
                end = pool.getStart();
            }
            if(pool.getEnd() >= end) {
                while(end < pool.getEnd()) {
                    end += l;
                    count++;
                }
            }
        }

        return count;
    }

    static class Pool implements Comparable<Pool> {
        private int start;
        private int end;

        public Pool(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public int compareTo(Pool o) {
            return Integer.compare(this.start, o.start);
        }
    }
}
