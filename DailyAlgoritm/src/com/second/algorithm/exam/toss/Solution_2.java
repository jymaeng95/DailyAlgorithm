package com.second.algorithm.exam.toss;

import java.util.Arrays;

public class Solution_2 {
    public static void main(String[] args) {
        int[] levels = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int rst = solution(levels);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] levels) {
        if(levels.length < 4) return -1;
        Arrays.sort(levels);

        double size = levels.length * 0.25;

        return levels[levels.length - (int) size];
    }
}

