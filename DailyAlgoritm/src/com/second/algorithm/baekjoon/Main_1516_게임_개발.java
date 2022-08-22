package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1516_게임_개발 {
    private static List<List<Integer>> relations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Building[] buildings = new Building[N + 1];
        relations = new ArrayList<>();
        for (int number = 0; number <= N; number++) {
            buildings[number] = new Building(number);
            relations.add(new ArrayList<>());
        }

        // 건물 정보 입력
        for (int number = 1; number <= N; number++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            buildings[number].addTime(time);

            while(st.hasMoreTokens()) {
                int prevBuilt = Integer.parseInt(st.nextToken());
                if(prevBuilt == -1) break;
                buildings[number].addIndegree();
                relations.get(prevBuilt).add(number);
            }
        }

        int[] rst = buildBuildings(N, buildings);
        for (int index = 1; index <= N; index++) {
            System.out.println(rst[index]);
        }

        br.close();
    }

    private static int[] buildBuildings(int N, Building[] buildings) {
        int[] dp = new int[N + 1];
        // 위상정렬
        Queue<Building> queue = new LinkedList<>();
        for (Building building : buildings) {
            if(building.getIndegree() == 0)
                queue.add(building);
        }

        while(!queue.isEmpty()) {
            Building building = queue.poll();
            int time = building.getTime();
            int number = building.getNumber();

            // 다음에 지을 건물 확인
            for (Integer nextNumber : relations.get(number)) {
                buildings[nextNumber].minusIndegree();
                dp[nextNumber] = Math.max(dp[nextNumber], time);
                // 건물 짓기전에 지어져야할 건물이 없는 경우
                if(buildings[nextNumber].getIndegree() == 0) {
                    buildings[nextNumber].addTime(dp[nextNumber]);
                    queue.add(buildings[nextNumber]);
                }
            }
        }

        return Arrays.stream(buildings).mapToInt(Building::getTime).toArray();
    }

    static class Building {
        private int number;
        private int time;
        private int indegree;

        public Building(int number) {
            this.number = number;
            this.time = 0;
            this.indegree = 0;
        }

        public int getTime() {
            return time;
        }

        public void addTime(int time) {
            this.time += time;
        }

        public int getIndegree() {
            return indegree;
        }

        public void addIndegree() {
            this.indegree++;
        }

        public void minusIndegree() {
            this.indegree--;
        }

        public int getNumber() {
            return number;
        }
    }
}
