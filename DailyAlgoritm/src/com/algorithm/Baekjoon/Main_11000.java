package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;

public class Main_11000 {
    static class Classroom {
        private int start;
        private int end;

        public Classroom(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Classroom> classrooms = new ArrayList<>();

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            classrooms.add(new Classroom(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        Collections.sort(classrooms, (Comparator.comparingInt(o -> o.start)));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Classroom classroom : classrooms){
            if(!pq.isEmpty() && pq.peek() <= classroom.start){
                pq.poll();
            }
            pq.offer(classroom.end);
        }
        bw.write(String.valueOf(pq.size()));
        br.close();
        bw.close();
    }

}
