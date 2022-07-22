package com.second.algorithm.exam;

import java.util.Stack;

public class Solution_3 {
    public static void main(String[] args) {
        int[] order = {5, 4, 3, 2, 1};
        int rst = solution(order);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] order) {
        int count = 0;
        Stack<Integer> subBelt = new Stack<>();
        for (int box = 1; box <= order.length; box++) {
            // 현재 박스를 실을 수 있는지 판단
            if (order[count] == box) {
                count++; // 실을 수 있는 경우 다음 순서로
                // 스택에서 순서 한번 더 확인
                while (!subBelt.isEmpty() && subBelt.peek() == order[count]) {
                    subBelt.pop();
                    count++;
                }
            } else {
                // 벨트에서 실을 수 있는지 체크
                subBelt.push(box);
            }
        }

        while (!subBelt.isEmpty() && subBelt.peek() == order[count]) {
            subBelt.pop();
            count++;
        }
        return count;
    }
}
