package com.algorithm.Programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class Solution_1845 {
    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,4,5,8};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int count = nums.length/2;
        for(int x : nums)
            set.add(x);

        return Math.min(set.size(), count);
    }
}
