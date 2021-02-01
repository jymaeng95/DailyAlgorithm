package com.algorithm.Programmers.Lv3;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_12927_야근_지수 {
    public static void main(String[] args) {
        int[] works = {1,1,1};
        int n = 3;
        long solution = solution(n, works);
        System.out.println(solution);
    }
    public static long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works)
            pq.offer(work);

        int time = 0;
        while(!pq.isEmpty() && time < n){
            int work = pq.poll();
            work -= 1;
            time ++;
            if(work > 0)
                pq.offer(work);
        }
        if(pq.isEmpty())
            return 0;
        while(!pq.isEmpty()){
            int work = pq.poll();
            answer += (long) Math.pow(work,2);
        }
        return answer;
    }
}
