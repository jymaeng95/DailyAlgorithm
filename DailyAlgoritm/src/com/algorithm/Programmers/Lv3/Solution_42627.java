package com.algorithm.Programmers.Lv3;

import java.util.*;

public class Solution_42627 {
    static class Disk implements Comparable<Disk> {
        private int start;
        private int elapsed;

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

        Arrays.sort(jobs, (o1, o2) -> {
            if (o1[0] == o2[0])
                return Integer.compare(o1[1], o2[1]);
            else
                return Integer.compare(o1[0], o2[0]);
        });

        LinkedList<Disk> list = new LinkedList<>();
        for (int[] job : jobs)
            list.add(new Disk(job[0], job[1]));

        pq.offer(list.poll());
        int time = pq.peek().getStart();
        //time >= start 인 값 큐에 넣기
        //time += elapsed;
        //answer += time - disk.start;
        while (!pq.isEmpty() || list.size() > 0) {
            if(list.size() > 0 && pq.isEmpty()){
                time = list.peek().getStart();
                pq.offer(list.poll());
                continue;
            }
            Disk disk = pq.poll();
            time += disk.getElapsed();

            while (list.size() > 0 && time >= list.peek().getStart()) {
                pq.offer(list.poll());
            }
            answer += time - disk.getStart();
        }

        return answer / jobs.length;
    }
}
