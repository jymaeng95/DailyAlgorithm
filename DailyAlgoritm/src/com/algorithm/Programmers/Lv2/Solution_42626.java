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

        //우선 순위 큐에 데이터 넣기
        for(int scv : scoville)
            pq.offer(scv);

        //큐 size가 1인 경우 값 비교만 필요
        //큐의 가장 최소 값이 K 이상인 경우 음식 섞어줄 필요 x
        while(pq.size()>1 && pq.peek() < K){
            int first = pq.poll();
            int afterScov = 0;

            //최소 값이 K 보다 작으면 음식 섞기
            if(pq.peek() < K){
                afterScov = first + (pq.poll() * 2);
                pq.offer(afterScov);
                answer++;
                continue;
            }

            //최소 값이 K보다 큰 경우 마지막으로 음식 섞어주기
            afterScov = first + (pq.peek() * 2);
            pq.offer(afterScov);
            answer++;
        }

        //최소 값이 기대 스코빌 지수보다 작으면 만들 수 없는 경우
        if(pq.peek() < K )
            return -1;

        return answer;
    }
}
