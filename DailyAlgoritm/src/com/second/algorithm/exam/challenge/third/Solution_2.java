package com.second.algorithm.exam.challenge.third;

import java.util.Stack;

public class Solution_2 {
    public static void main(String[] args) {
        int[] ingredient = {1,3,2,1,2,1,3,1,2};
        int rst = solution(ingredient);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] ingredient) {
        Stack<Integer> stack = new Stack<>();
        int[] orders = {3, 2, 1};
        int count = 0;
        for (int food : ingredient) {
            // 빵인 경우
            if (food == 1) {
                boolean hamburger = true;

                // 뒤에서 세개 확인
                for (int index = 1; index <= 3; index++) {
                    if (stack.size() - index < 0 || stack.get(stack.size() - index) != orders[index - 1]) {
                        hamburger = false;
                        break;
                    }
                }

                // 순서 동일한 경우 스택에서 3개 빼주기
                if (hamburger) {
                    for (int loop = 1; loop <= 3; loop++) stack.pop();
                    count++;
                } else {
                    stack.push(food);
                }
            } else {
                stack.push(food);
            }
        }
        return count;
    }
}
