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
        //인덱스가 마지막에 도달하였을 때 타겟넘버와 합계가 동일하면 타겟넘버 일치
        if (sum == target && index == numbers.length) {
            count++;
            return;
        }
        //무한루프 막기각
        if (index >= numbers.length)
            return;

        //부호가 +,- 인 경우 2가지 이므로 나눠서 생
        dfs(numbers, target, index + 1, sum+numbers[index]);
        dfs(numbers, target, index + 1, sum-numbers[index]);
    }
}
