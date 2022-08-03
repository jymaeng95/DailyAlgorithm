package com.second.algorithm.programmers;

public class Solution_43165_타겟_넘버 {
    public static void main(String[] args) {
        int[] nubmers = {1, 1, 1, 1,1};
        int target = 3;

        int rst = solution(nubmers, target);
        System.out.println("rst = " + rst);
    }

    private static int count;
    private static int solution(int[] numbers, int target) {
        count = 0;

        makeExpress(0, 0, numbers, target);

        return count;
    }

    private static void makeExpress(int depth, int sum, int[] numbers, int target) {
        if(depth == numbers.length) {
            if(sum == target) count++;
            return;
        }

        makeExpress(depth + 1, sum + numbers[depth], numbers, target);
        makeExpress(depth + 1, sum - numbers[depth], numbers, target);
    }

}
