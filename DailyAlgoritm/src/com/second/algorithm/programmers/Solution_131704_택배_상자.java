package com.second.algorithm.programmers;

import java.util.Stack;

public class Solution_131704_택배_상자 {
    public static void main(String[] args) {
//        int[] orders = {4, 3, 1, 2, 5};
         int[] orders = {5, 4, 3, 2, 1};

        int rst = solution(orders);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] orders) {
        Stack<Integer> sub = new Stack<>();

        int count = 0;
        int index = 0;
        for (int box = 1; box <= orders.length; box++) {
            // 순서가 맞는 경우 개수 증가
            if(box == orders[index]) {
                index++;
                count++;
            }
            else {
                // 보조 벨트에 존재하는지 확인
                while (!sub.isEmpty() && sub.peek() == orders[index]) {
                    count++;
                    sub.pop();
                    index++;
                }

                sub.push(box);
            }
        }

        // 마지막까지 존재하지 않는 경우 보조벨트에서 빼면서 체크
        while (!sub.isEmpty() && sub.peek() == orders[index]) {
            count++;
            sub.pop();
            index++;
        }

        return count;
    }
}
