package com.algorithm.Programmers.Lv2;

import java.util.PriorityQueue;

public class Solution_42626 {
    public static void main(String[] args) {
        int[] scoville = {1,2,3};
        int k = 14;
        System.out.println(solution(scoville,k));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int scv : scoville)
            pq.offer(scv);

        while(pq.size()>1 && pq.peek() < K){
            int first = pq.poll();
            int afterScov = 0;
            if(pq.peek() < K){
                afterScov = first + (pq.poll() * 2);
                pq.offer(afterScov);
                answer++;
                continue;
            }
            afterScov = first + (pq.peek() * 2);
            pq.offer(afterScov);
            answer++;
            break;
        }
        if(pq.peek() < K )
            return -1;

        return answer;
    }
}
