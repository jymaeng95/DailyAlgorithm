package com.algorithm.Programmers.Lv2;

public class Solution_43165 {
    public static int count;

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        count = 0;
        dfs(numbers, target, 0, 0);
        return count;
    }

    private static void dfs(int[] numbers, int target, int index, int sum) {
        if (sum == target && index == numbers.length) {
            count++;
            return;
        }
        if (index >= numbers.length)
            return;
        dfs(numbers, target, index + 1, sum+numbers[index]);
        dfs(numbers, target, index + 1, sum-numbers[index]);
    }
}
