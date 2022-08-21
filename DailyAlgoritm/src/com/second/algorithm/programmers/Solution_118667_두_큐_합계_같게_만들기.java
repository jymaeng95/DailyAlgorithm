package com.second.algorithm.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_118667_두_큐_합계_같게_만들기 {
    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};

        int rst = solution(queue1, queue2);
        Queue<Object> q1 = new LinkedList<>(Arrays.asList(queue1));
        Arrays.stream(queue1).forEach(q1::add);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] queue1, int[] queue2) {
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();

        // 두 큐의 합계가 홀수인 경우 만들 수 없음
        if((sum1 + sum2) % 2 != 0) return -1;

        // 합계 동일한 경우
        if(sum1 == sum2) return 0;

        long target = (sum1 + sum2) / 2;
        Queue<Integer> q1 = new LinkedList<>();
        Arrays.stream(queue1).forEach(q1::add);

        Queue<Integer> q2 = new LinkedList<>();
        Arrays.stream(queue2).forEach(q2::add);
        int move = 0;
        while(!q1.isEmpty() && !q2.isEmpty()) {
            if(sum1 < target) {
                // 큐1의 합이 타겟 보다 작은 경우 큐2에서 빼서 넣어주기
                int poll = q2.poll();
                q1.add(poll);
                sum1 += poll;
                move++;
            } else if(sum1 > target) {
                // 큐1에서 빼서 큐2에 넣어주기
                int poll = q1.poll();
                q2.add(poll);
                sum1 -= poll;
                move++;
            }
            else return move;
        }

        return -1;
    }
}
