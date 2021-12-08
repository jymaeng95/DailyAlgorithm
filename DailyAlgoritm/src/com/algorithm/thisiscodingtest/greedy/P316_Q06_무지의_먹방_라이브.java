package com.algorithm.thisiscodingtest.greedy;

import java.util.*;

public class P316_Q06_무지의_먹방_라이브 {
    //나중에 다시 풀기
    static class Food implements Comparable<Food> {
        private int index;
        private long time;

        public Food(int index, int time) {
            this.index = index;
            this.time = time;
        }

        public int getIndex() {
            return index;
        }

        public long getTime() {
            return time;
        }

        @Override
        public int compareTo(Food o) {
            return Long.compare(this.time, o.time);
        }
    }
    public static void main(String[] args) {
        int[] food_times = {1,5,6,7};
        long k = 8;
        int solution = solution(food_times, k);
        System.out.println(solution);
    }

    public static int solution(int[] food_times, long k) {
        int answer = 0;

        PriorityQueue<Food> queue = new PriorityQueue<>();
        for(int i=0;i< food_times.length;i++) {
            queue.offer(new Food(i+1,food_times[i]));
        }
        long prev = 0;
        long totalTime = 0;
        while(k >= queue.size()) {
            if (queue.isEmpty()) {
                answer = -1;
                break;
            }
            int size = queue.size();
            Food food = queue.peek();
            long next = food.getTime() - prev;
            if(k < next * size) {
                k %= size;
                break;
            }
            k -= next * size;
            prev = food.getTime();
            queue.poll();
        }
        List<Food> foodList = new ArrayList<>(queue);
        foodList.sort(Comparator.comparingInt(o -> o.getIndex()));

        if(!queue.isEmpty()) {
            answer = foodList.get((int)k).getIndex();
        }
        return answer;
    }
}
