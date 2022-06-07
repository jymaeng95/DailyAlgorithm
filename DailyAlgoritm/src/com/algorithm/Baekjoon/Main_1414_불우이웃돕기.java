package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1414_불우이웃돕기 {
    private static List<List<Computer>> computers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        computers = new ArrayList<>();

        for(int computer = 0; computer <= N; computer++) {
            computers.add(new ArrayList<>());
        }

        // 간선 정보 넣기
        int totalLength = 0;
        for(int computerA = 1; computerA <= N; computerA++) {
            String lengthInfo = br.readLine();
            for(int computerB = 1; computerB <= N; computerB++) {
                int length = convertAlpha(lengthInfo.charAt(computerB - 1));

                computers.get(computerA).add(new Computer(computerB, length));
                computers.get(computerB).add(new Computer(computerA, length));

                totalLength += length;
            }
        }

        // 랜선 구하기
        int rst = getMaxLanLength(N, totalLength);
        System.out.println(rst);

        br.close();
    }

    private static int convertAlpha(char alphabet) {
        if(Character.isLowerCase(alphabet)) return alphabet - 'a' + 1;
        else if(Character.isUpperCase(alphabet)) return alphabet - 'A' + 27;
        return 0;
    }

    private static int getMaxLanLength(int n, int totalLength) {
        /**
         * 1. 모든 컴퓨터가 연결되어야 하므로 1번 컴퓨터에 대해서 최소신장트리 구하기
         * 2. 모두 방문 안되는 경우 -1
         * 3. 모두 방문 처리 된 경우 totalLength - 길이값
         */
        int[] lan = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Computer> pq = new PriorityQueue<>();

        Arrays.fill(lan, Integer.MAX_VALUE);
        pq.add(new Computer(1, 0));
        lan[1] = 0;

        // 프림 알고리즘
        while(!pq.isEmpty()) {
            Computer computerA = pq.poll();

            if(visited[computerA.getComputer()]) continue;
            visited[computerA.getComputer()] = true;

            for (Computer computerB : computers.get(computerA.getComputer())) {
                if(!visited[computerB.getComputer()] && computerB.getLength() != 0) {
                    // 최소 길이 갱신
                    lan[computerB.getComputer()] = Math.min(lan[computerB.getComputer()], computerB.getLength());
                    pq.add(computerB);
                }
            }

        }

        // 사용한 랜선 길이 구하기
        int lanLength = 0;
        for(int computer = 1; computer <= n; computer++) {
            // 연결 안된 컴퓨터 있는 경우 -1
            if(!visited[computer]) return -1;
            lanLength += lan[computer];
        }

        // 전체 길이 - 사용 랜선 길이 = 기부할 랜선 최대 길이
        return totalLength - lanLength;
    }

    static class Computer implements Comparable<Computer> {
        private int computer;
        private int length;

        public Computer(int computer, int length) {
            this.computer = computer;
            this.length = length;
        }

        public int getComputer() {
            return computer;
        }

        public int getLength() {
            return length;
        }

        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.length, o.length);
        }
    }
}

