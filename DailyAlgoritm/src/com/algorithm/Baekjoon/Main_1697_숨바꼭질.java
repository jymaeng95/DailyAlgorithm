package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int rst = hideAndSeek(N, K);
        System.out.println(rst);

        br.close();
    }
    private static int hideAndSeek(int n, int k) {
        boolean[] visited = new boolean[100001];
        PriorityQueue<Position> pq = new PriorityQueue<>();

        pq.offer(new Position(n, 0));
        visited[n] = true;

        while (!pq.isEmpty()) {
            Position curPosition = pq.poll();
            int curPos = curPosition.getPos();
            int curCount = curPosition.getCount();

            if(curPos == k) return curCount;

            if(curPos + 1 <= 100000 && !visited[curPos+1]) {
                visited[curPos + 1] = true;
                pq.add(new Position(curPos + 1, curCount + 1));
            }

            if (curPos <= 100000 && curPos - 1 >= 0 && !visited[curPos - 1]) {
                visited[curPos - 1] = true;
                pq.add(new Position(curPos - 1, curCount + 1));
            }

            if (curPos * 2 <= 100000 && !visited[curPos * 2]) {
                visited[curPos * 2] = true;
                pq.add(new Position(curPos * 2, curCount + 1));
            }
        }
        return -1;
    }

    static class Position implements Comparable<Position> {
        private int pos;
        private int count;


        public Position(int pos, int count) {
            this.pos = pos;
            this.count = count;
        }

        public int getPos() {
            return pos;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(Position o) {
            return Integer.compare(this.count, o.count);
        }
    }
}
