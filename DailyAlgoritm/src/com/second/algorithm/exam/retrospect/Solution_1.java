package com.second.algorithm.exam.retrospect;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {
    // 문제 복기로 풀어달라고 요청
    public static void main(String[] args) {
//        String[] color = {"RG", "WR", "BW", "GG"};
//        int[] prices = {5000, 6000};

//        String[] color = {"RG", "WR", "BW", "GG"};
//        int[] prices = {2000, 6000};

//        String[] color = {"BW", "RY", "BY"};
//        int[] prices = {9000, 10000};

        String[] color = {"YW", "RY", "WG", "BW"};
        int[] prices = {7561, 8945};

        long rst = solution(color, prices);
        System.out.println("rst = " + rst);
    }

    private static final char[] COLORS = {'B', 'W', 'G', 'Y', 'R'};
    private static long solution(String[] color, int[] prices) {
        /**
         * 1. 상의, 하의를 모두 동일 조합으로만 구매한다. (상의, 하의 중 최대 개수로 구매)
         * 2. 상의, 하의를 최소한의 동일 조합으로 산다 + 남는 상,하의는 다른 조합으로 구매한다. (상,하의 동일 조합 최소 금애 구매, 나머지 최대 금애 구매)
         */
        Map<Character, Integer> top = initMap(color, 0);
        Map<Character, Integer> bottom = initMap(color, 1);

        long samePrice = 0; // 모두 동일 조합 구매가격
        long minSamePrice = 0; // 최소 동일 조합 구매가격
        int remain = 0;
        for (char c : COLORS) {
            // 1. 모두 동일 조합
            int topCount = top.get(c);
            int bottomCount = bottom.get(c);
            int maxSameBuy = Math.max(topCount, bottomCount);
            samePrice += (long) prices[0] * maxSameBuy;

            // 2. 최소로 동일조합 하고 나머지는 다른 조합 구매ERD
            int minSameBuy = Math.min(topCount, bottomCount);
            minSamePrice += (long) prices[0] * minSameBuy;

            if(topCount >= bottomCount) remain += topCount - bottomCount;
            else remain += bottomCount - topCount;
        }

        // 남는 옷들로 다른 조합으로 계산
        minSamePrice += (long) prices[1] * (remain / 2);

        // 3. 최소값 비교
        return Math.min(minSamePrice, samePrice);
    }

    private static Map<Character, Integer> initMap(String[] color, int pos) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : COLORS) {
            map.put(c, 0);
        }

        for (String clothes : color) {
            map.put(clothes.charAt(pos), map.get(clothes.charAt(pos)) + 1);
        }

        return map;
    }
}
