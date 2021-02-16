package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_12978_배달 {
    public static void main(String[] args) {
        int N = 6;
        int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
        int K = 4;
        int solution = solution(N, road, K);
        System.out.println(solution);
    }
    static class Village implements Comparable<Village> {
        private int destination;
        private int distance;

        public Village(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        @Override
        public int compareTo(Village o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
    private static List<ArrayList<Village>> roadList;
    public static int solution(int N, int[][] road, int K) {
        roadList = new ArrayList<>();
        for(int i =0;i<=N;i++){
            roadList.add(new ArrayList<>());
        }
        for(int[] r : road){
            int start = r[0];
            int end = r[1];
            int dist = r[2];

            roadList.get(start).add(new Village(end,dist));
            roadList.get(end).add(new Village(start,dist));
        }

        int[] minDistance = getMinDistance(N);
        int count = 0;
        for(int i=1;i<minDistance.length;i++){
            if(minDistance[i] <= K)
                count++;
        }
        return count;
    }

    private static int[] getMinDistance(int N) {
        PriorityQueue<Village> pq = new PriorityQueue<>();
        int[] minDistance= new int [N+1];
        Arrays.fill(minDistance,Integer.MAX_VALUE);
        pq.offer(new Village(1,0));
        minDistance[1] = 0;
        while(!pq.isEmpty()){
            Village village = pq.poll();
            if(village.distance > minDistance[village.destination])
                continue;
            for(Village v : roadList.get(village.destination)){
                if(minDistance[v.destination] > v.distance + village.distance){
                    minDistance[v.destination] = v.distance + village.distance;
                    pq.offer(new Village(v.destination,minDistance[v.destination]));
                }
            }
        }
        return minDistance;
    }
}
