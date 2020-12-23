package com.algorithm.Programmers.Lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_17680 {
    public static void main(String[] args) {
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution(cacheSize, cities));
    }

    private static final int HIT = 1;
    private static final int MISS = 5;
    public static int solution(int cacheSize, String[] cities) {
        //캐시가 없는 경우
        if (cacheSize == 0)
            return cities.length * MISS;

        int time = 0;
        Queue<String> cache = new LinkedList<>();

        for (String city : cities) {
            //캐시에 있는 경우 큐에서 삭제 후 삽입해야 최신 상태 반
            if (!cache.isEmpty() && cache.contains(city.toLowerCase())) {
                cache.remove(city.toLowerCase());
                cache.offer(city.toLowerCase());
                time += HIT;
                continue;
            }
            //캐시 사이즈와 같다면 가장 오래된 캐시내역 삭제 후 삽입 필
            if (cache.size() == cacheSize)
                cache.poll();
            cache.offer(city.toLowerCase());
            time += MISS;
        }
        return time;
    }
}
