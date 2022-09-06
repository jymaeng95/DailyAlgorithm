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

        // 두 큐의 합이 홀수인 경우 동일하게 만드는 것 불가능
        if((sum1 + sum2) % 2 != 0) return -1;

        // 두 큐의 합이 동일한 경우
        if(sum1 == sum2) return 0;


        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();

        Arrays.stream(queue1).forEach(queueA::add);
        Arrays.stream(queue2).forEach(queueB::add);

        int count = 0, idxA = 0;
        int length = queue1.length + queue2.length;

        // 두 큐중 하나가 비는 경우 만들 수 없는 경우 두 큐중 하나가 큐 두개를 거쳐서 자신의 큐로 돌아온다면 만들 수 없는 경우
        while(!queueA.isEmpty() && !queueB.isEmpty() && idxA < length) {
            // 합계가 작은 큐에 큰 큐에서 poll해서 넣어줌
            if(sum1 > sum2) {
                // A -> B
                int number = queueA.poll();
                sum1 -= number;
                sum2 += number;

                queueB.add(number);
                idxA++;
            } else if(sum1 < sum2) {
                // B->A
                int number = queueB.poll();
                sum1 += number;
                sum2 -= number;

                queueA.add(number);
            }
            else return count;
            count ++;
        }

        return -1;
    }
}
