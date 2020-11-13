package com.algorithm.Programmers.Lv3;

import java.util.*;

public class Solution_42627 {
    static class Disk implements Comparable<Disk> {
        private int start;      //시작 시간
        private int elapsed;    //경과 시간

        public Disk(int start, int elapsed) {
            this.start = start;
            this.elapsed = elapsed;
        }

        public int getStart() {
            return start;
        }

        public int getElapsed() {
            return elapsed;
        }

        // 경과 시간이 짧은 순서로 힙에 넣어줌
        @Override
        public int compareTo(Disk o) {
            if (this.elapsed < o.elapsed) return -1;
            else if (this.elapsed > o.elapsed) return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] jobs = {{1, 1}, {4, 9}, {5, 6}};
        System.out.println(solution(jobs));
    }

    public static int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<Disk> pq = new PriorityQueue<>();

        //Jobs 배열을 시작시간이 빠른 순서로 먼저 정렬
        //시작시간이 동일한 경우 경과 시간이 짧은 순으로 정렬
        Arrays.sort(jobs, (o1, o2) -> {
            if (o1[0] == o2[0])
                return Integer.compare(o1[1], o2[1]);
            else
                return Integer.compare(o1[0], o2[0]);
        });

        LinkedList<Disk> list = new LinkedList<>();
        for (int[] job : jobs)
            list.add(new Disk(job[0], job[1]));

        pq.offer(list.poll());      //가장 빠른 작업 제일 먼저 넣어주기
        int time = pq.peek().getStart();     //가장 빠른 작업 시작시간이 모든 스케줄링 시작 시간

        //time >= start 인 값 큐에 넣기
        //time += elapsed;
        //answer += time - disk.start;
        //큐가 empty가 아니거나 아직 작업이 큐에 다 들어가지 않은 경우
        while (!pq.isEmpty() || list.size() > 0) {
            /*큐가 비었지만 list size가 남은 경우는 (1,1) (4,1)의 경우
             *1초에 시작 2초에 완료, 이후 2초부터 다음 작업 시작시간인 4초까지 작업이 없기 때문에
             *먼저 요청하는 작업 큐에 넣어주기
             */
            if(list.size() > 0 && pq.isEmpty()){
                time = list.peek().getStart();
                pq.offer(list.poll());
                continue;
            }
            Disk disk = pq.poll();
            time += disk.getElapsed();      //경과 시간을 더해주면 작업이 끝난 현재 시간

            while (list.size() > 0 && time >= list.peek().getStart()) {
                pq.offer(list.poll());      //현재 시간보다 전에 요청 받은 작업 큐에 넣어주기
            }
            answer += time - disk.getStart();   //현재 시간부터 요청 시작 시간을 빼주면 요청부터 작업 완료까지의 시간
        }

        return answer / jobs.length;
    }
}
