package com.second.algorithm.exam.challenge.third;

import java.util.HashMap;
import java.util.Map;

public class Solution_5 {
    public static void main(String[] args) {
        int[] task = {1, 1, 2, 2, 3, 3, 3, 3, 3};

        int rst = solution(task);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] task) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : task) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 2를 빼거나 3을 빼서 만들 수 있는지 확인
        int sum = 0;
        for (Integer number : map.keySet()) {
            minJob = map.get(number);
            countJobs(map.get(number), 0);

            // 만약 Job을 못끝내는 경우
            if(minJob == map.get(number)) return -1;
            sum += minJob;
        }
        return sum;
    }

    private static int minJob;

    private static void countJobs(Integer count, int job) {
        if (count <= 0) {
            if (count == 0) minJob = Math.min(minJob, job);
            return;
        }

        countJobs(count - 2, job + 1);
        countJobs(count - 3, job + 1);
    }
}
