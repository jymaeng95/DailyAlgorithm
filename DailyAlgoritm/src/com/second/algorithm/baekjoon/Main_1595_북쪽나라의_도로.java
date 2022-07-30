package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1595_북쪽나라의_도로 {
    private static Map<Integer, List<City>> roads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        roads = new HashMap<>();

        for (int city = 0; city <= 10000; city++) {
            roads.put(city, new ArrayList<>());
        }

        int maxCity = 0;
        String roadInfo = "";
        while ((roadInfo = br.readLine()) != null) {
            if (roadInfo.trim().equals("") || roadInfo.trim().length() == 0)
                break;
            StringTokenizer st = new StringTokenizer(roadInfo);
            int departure = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            maxCity = Math.max(maxCity, Math.max(destination, departure));
            roads.get(departure).add(new City(destination, distance));
            roads.get(destination).add(new City(departure, distance));
        }

        long rst = getLongestRoad(maxCity);
        System.out.println(rst);


        br.close();
    }

    private static int longestIndex, longestDistance;
    private static long getLongestRoad(int maxCity) {
        // 1번 도시부터 거리 구해서 넣어주기
        // 두 도시간 연결된 도로는 한개
        boolean[] visited = new boolean[10000 + 1];
        longestIndex = 1;
        longestDistance = 0;
        visited[longestIndex] = true;

        calculateDistance(longestIndex, 0, visited);

        visited = new boolean[10000 + 1];
        visited[longestIndex] = true;
        calculateDistance(longestIndex, 0, visited);

        return longestDistance;
    }

    private static void calculateDistance(int city, int distance, boolean[] visited) {
        for (City nextCity : roads.get(city)) {
            int nextDestination = nextCity.getDestination();
            int nextDistance = nextCity.getDistance();

            if (!visited[nextDestination]) {
                visited[nextDestination] = true;
                if(distance + nextDistance > longestDistance) {
                    longestDistance = distance + nextDistance;
                    longestIndex = nextDestination;
                }
                calculateDistance(nextDestination, distance + nextDistance, visited);
            }
        }
    }

    static class City {
        private int destination;
        private int distance;

        public City(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        public int getDestination() {
            return destination;
        }

        public int getDistance() {
            return distance;
        }
    }
}
