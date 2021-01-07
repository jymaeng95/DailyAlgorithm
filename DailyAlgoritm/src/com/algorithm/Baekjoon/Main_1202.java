package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;

public class Main_1202 {
    static class Gem implements Comparable<Gem> {
        private int weight;
        private int price;

        public Gem(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Gem o) {
            if (o.price > this.price) return 1;
            else return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Gem> gemList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int wg = Integer.parseInt(st.nextToken());
            int pc = Integer.parseInt(st.nextToken());
            gemList.add(new Gem(wg, pc));
        }


        List<Integer> bag = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bag.add(Integer.parseInt(br.readLine()));
        }

        long sum = 0;
        int idx = 0;

        //무게 오름차순 정렬
        Collections.sort(bag);
        Collections.sort(gemList, Comparator.comparingInt(o->o.weight));

        //최대힙
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int bg : bag) {
            while(idx < gemList.size()){
                if (bg >= gemList.get(idx).weight) {
                    pq.offer(gemList.get(idx++).price);
                }else
                    break;
            }
            if(!pq.isEmpty())
                sum += pq.poll();
        }
        bw.write(String.valueOf(sum));
        br.close();
        bw.close();
    }
}
