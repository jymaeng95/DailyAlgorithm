package com.algorithm.Programmers.Lv3;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_42628 {
    public static void main(String[] args) {
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        solution(operations);
    }

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op);
            String judge = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            //빈 큐에 데이터를 삭제 요청 경우 연산 무시
            if (pq.size() < 1 && judge.equals("D"))
                continue;

            if (judge.equals("I")) {
                pq.offer(value);
                maxPq.offer(value);
                continue;
            }
            if(value == -1) {
                int min = pq.poll();
                maxPq.remove(min);
                continue;
            }
            int max = maxPq.poll();
            pq.remove(max);
        }
        if(pq.size() > 0 ) {
            answer[0] = maxPq.poll();
            answer[1] = pq.poll();
        }
        return answer;
    }
}
