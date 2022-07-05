package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1826_연료_채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stationCount = Integer.parseInt(br.readLine());    // 주유소 개수
        gasStations = new PriorityQueue<>();
        for(int station = 0; station < stationCount; station++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int distance = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            gasStations.add(new GasStation(distance, fuel));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int remainDistance = Integer.parseInt(st.nextToken());
        int remainFuel = Integer.parseInt(st.nextToken());

        int rst = visitMinGasStation(remainDistance, remainFuel);
        System.out.println(rst);

        br.close();
    }

    private static int visitMinGasStation(int remainDistance, int remainFuel) {
        PriorityQueue<Integer> fuelQueue = new PriorityQueue<>(Collections.reverseOrder());
        int visit = 0;
        while(remainFuel < remainDistance) {
            // 주유소에 방문이 가능
            while(!gasStations.isEmpty() && gasStations.peek().getDistance() <= remainFuel) {
                fuelQueue.add(gasStations.poll().getFuel());
            }

            // 현재 남은 연료로 방문할 수 없는 주유소
            if(fuelQueue.isEmpty()) {
                return -1;
            }

            // 갈 수 있는 주유소 중 가장 연료 많이 담을 수 있는 주유소 방문
            remainFuel += fuelQueue.poll();
            visit++;
        }

        return visit;
    }

    private static PriorityQueue<GasStation> gasStations;
    static class GasStation implements Comparable<GasStation> {
        private int distance;
        private int fuel;

        public GasStation(int distance, int fuel) {
            this.distance = distance;
            this.fuel = fuel;
        }

        public int getDistance() {
            return distance;
        }

        public int getFuel() {
            return fuel;
        }

        @Override
        public int compareTo(GasStation o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
