package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_8980_택배 {
    private static int N, C, M;

    static class Village implements Comparable<Village> {
        private int start;
        private int end;
        private int cap;

        public Village(int start, int end, int cap) {
            this.start = start;
            this.end = end;
            this.cap = cap;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getCap() {
            return cap;
        }

        @Override
        public int compareTo(Village o) {
            return Integer.compare(this.end, o.end);
        }
    }

    private static List<Village> villageList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cap = Integer.parseInt(st.nextToken());
            villageList.add(new Village(start, end, cap));
        }
        Collections.sort(villageList);

        int rst = delivery();
        System.out.println(rst);
        br.close();
    }

    private static int delivery() {
        int[] capacity = new int[N];
        Arrays.fill(capacity, C);
        int rst = 0;
        for (Village village : villageList) {
            int start = village.getStart();
            int end = village.getEnd();
            int cap = village.getCap();
            int available = C;

            for (int i = start; i < end; i++) {
                available = Math.min(Math.min(available, capacity[i]),cap);
            }
            if(available > 0) {
                for (int i = start; i < end; i++) {
                    capacity[i] -= available;
                }
            }

            rst += available;
        }
        return rst;
    }
}
