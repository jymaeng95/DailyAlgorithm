package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_19621_회의실_배정_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Room> roomList = new ArrayList<>();
        for(int loop = 0; loop < N ; loop++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int headcount = Integer.parseInt(st.nextToken());
            roomList.add(new Room(start, end, headcount));
        }

        int rst = getMaxHeadcount(N, roomList);
        System.out.println(rst);

        br.close();
    }

    private static int headcount;
    private static int getMaxHeadcount(int n, List<Room> roomList) {
        headcount = 0;

        dfs(0, roomList, 0, n);

        return headcount;
    }

    private static void dfs(int depth, List<Room> roomList, int count, int n) {
        if(depth > n-1) {
            headcount = Math.max(headcount, count);
            return;
        }

        // 현재 회의 참여하는 경우 다음 회의 참여 못함
        dfs(depth + 2, roomList, count + roomList.get(depth).getHeadcount(), n);

        // 현재 회의 참여하지 않는 경우 다음 회의 참여
        dfs(depth + 1, roomList, count, n);
    }

    static class Room {
        private int start;
        private int end;
        private int headcount;

        public Room(int start, int end, int headcount) {
            this.start = start;
            this.end = end;
            this.headcount = headcount;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getHeadcount() {
            return headcount;
        }
    }
}
