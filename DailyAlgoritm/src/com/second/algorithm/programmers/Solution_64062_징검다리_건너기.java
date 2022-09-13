package com.second.algorithm.programmers;

public class Solution_64062_징검다리_건너기 {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;

        int rst = solution(stones, k);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] stones, int k) {
        long minPerson = 1;
        long maxPerson = 200_000_000;

        while (minPerson <= maxPerson) {
            long mid = (minPerson + maxPerson) / 2;
            int zeroCount = 0;
            for (int stone : stones) {
                // stone - mid(지나갈 수 있는 사람) <= 0 인경우 count++
                if (stone - mid <= 0) zeroCount++;
                else zeroCount = 0;

                // k칸 이상 띄어진 경우
                if (zeroCount >= k) break;
            }

            // k칸 이상 띄어진 경우 인원수 줄이기
            if (zeroCount >= k) maxPerson = mid - 1;

            // K칸 이상 안띄어진 경우 한명 더 지나갈 수 있음
            else minPerson = mid + 1;
        }

        return (int) minPerson;
    }
}
