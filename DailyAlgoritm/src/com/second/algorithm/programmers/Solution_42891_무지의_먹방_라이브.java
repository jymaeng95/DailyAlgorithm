package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_42891_무지의_먹방_라이브 {
    public static void main(String[] args) {
        int rst = solution(new int[]{3, 1, 2}, 5);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] food_times, long k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 전체 소요 시간 우선 구하기
        long totalSecond = 0;
        for (int index = 0; index < food_times.length; index++) {
            totalSecond += food_times[index];
            pq.add(food_times[index]);
        }

        // 전체 소요 시간 보다 K가 크거나 같은 경우 더이상 먹을 음식 X
        if (totalSecond <= k) return -1;

        long cycle = 0;
        while (!pq.isEmpty()) {
            int size = pq.size();
            int remain = pq.poll();
            long second = (remain - cycle) * size;

            // K초가 초과되거나 K초 지난 경우
            if (k - second <= 0) {
                break;
            }

            k -= second;
            cycle = remain;
        }

        // 이미 끝난 경우 제외하고 인덱스 확인
        List<Integer> indexes = new ArrayList<>();
        for (int index = 0; index < food_times.length; index++) {
            if (food_times[index] > cycle) {
                indexes.add(index);
            }
        }

        long answerIndex = -1;
        if (indexes.size() > 0) {
            answerIndex = k % indexes.size();
        }

        return answerIndex < 0 ? -1 : indexes.get((int) answerIndex) + 1;
    }
}