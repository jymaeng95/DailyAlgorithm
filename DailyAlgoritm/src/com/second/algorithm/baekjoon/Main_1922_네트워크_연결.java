package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1922_네트워크_연결 {
    private static int N, M;
    private static List<List<Computer>> networks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        networks = new ArrayList<>();
        for (int computer = 0; computer <= N; computer++) {
            networks.add(new ArrayList<>());
        }

        for (int line = 0; line < M; line++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            networks.get(from).add(new Computer(to, cost));
            networks.get(to).add(new Computer(from, cost));
        }

        int rst = getMinCost(1);
        System.out.println(rst);

        br.close();
    }

    private static int getMinCost(int startComputer) {
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.add(new Computer(startComputer, 0));

        boolean[] check = new boolean[N + 1];

        int length = 0;
        while (!pq.isEmpty()) {
            Computer computer = pq.poll();
            int number = computer.getNumber();

            if(check[number]) continue;
            check[number] = true;
            length += computer.getCost();

            for (Computer nextComputer : networks.get(number)) {
                int nextNumber = nextComputer.getNumber();
                if(!check[nextNumber]) {
                    pq.add(nextComputer);
                }
            }
        }

        return length;
    }

    static class Computer implements Comparable<Computer> {
        private final int number;
        private final int cost;

        public Computer(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        public int getNumber() {
            return number;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
