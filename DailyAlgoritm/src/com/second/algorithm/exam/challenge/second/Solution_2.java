package com.second.algorithm.exam.challenge.second;

import java.util.HashSet;
import java.util.Set;

public class Solution_2 {
    public static void main(String[] args) {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};

        int rst = solution(topping);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] topping) {

        // 마지막보다 한개 전까지 확인 (두부분으로 나눠야 하기 때문에)
        int[] left = new int[topping.length - 1];
        Set<Integer> leftSet = new HashSet<>();
        for (int index = 0; index < topping.length - 1; index++) {
            leftSet.add(topping[index]);

            left[index] = leftSet.size();
        }


        // 거꾸로 1까지  확인 (두부분으로 나눠야 하기 때문에)
        int[] right = new int[topping.length - 1];
        Set<Integer> rightSet = new HashSet<>();
        for (int index = topping.length - 1; index > 0; index--) {
            rightSet.add(topping[index]);
            right[index-1] = rightSet.size();
        }

        int count = 0;
        for (int index = 0; index < topping.length - 1; index++) {
            if(left[index] == right[index]) count++;
        }

        return count;
    }
}
