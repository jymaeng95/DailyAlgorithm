package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1374_강의실 {
    static class Room implements Comparable<Room> {
        private int room;
        private int start;
        private int end;

        public Room(int room, int start, int end) {
            this.room = room;
            this.start = start;
            this.end = end;
        }

        public int getRoom() {
            return room;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public int compareTo(Room o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Room> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int room = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Room(room, start, end));
        }

        int rst = getRoomCount(N, list);
        System.out.println(rst);

        br.close();
    }

    private static int getRoomCount(int n, List<Room> list) {
        Collections.sort(list);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Room room = list.get(i);
            if (!pq.isEmpty() && pq.peek() <= room.getStart()) pq.poll();
            pq.offer(room.getEnd());
        }
        return pq.size();
    }
}
