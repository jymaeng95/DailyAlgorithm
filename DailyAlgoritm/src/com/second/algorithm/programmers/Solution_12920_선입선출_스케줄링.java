package com.second.algorithm.programmers;

public class Solution_12920_선입선출_스케줄링 {
    public static void main(String[] args) {
        int n = 6;
        int[] cores = {1,2,3};

        int rst = solution(n, cores);
        System.out.println("rst = " + rst);
    }

    private static int solution(int n, int[] cores) {
        if(cores.length >= n) return n;

        n -= cores.length;
        long min = 1;
        long max = 10000 * 50000 * 10000;
        long remain = 0;
        while(min <= max) {
            long mid = (min + max) / 2;
            long count = 0;
            for(int core : cores) {
                count += mid / core;
            }

            // 작업 개수가 n보다 많거나 같은 경우
            if(count >= n) max = mid - 1;
            else {
                min = mid + 1;

                // n을 넘지 않는 경우까지의 최대 작업 구하기
                remain = Math.max(remain, count);
            }
        }

        // 남은 작업
        n -= remain;
        int answer = 0;

        // 현재 시간에 남은 작업 행할 수 있는 경우 처리
        for(int number = 1; number <= cores.length; number++) {
            if(min % cores[number - 1] == 0) n--;
            if(n <= 0) return number;
        }
        return answer;
    }
}
