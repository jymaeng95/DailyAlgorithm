package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_1414_불우이웃돕기 {
    private static int N;
    private static List<List<Computer>> length;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        length = new ArrayList<>();
        for (int index = 0; index < N; index++) {
            length.add(new ArrayList<>());
        }

        int sumLength = 0;
        for (int computer = 0; computer < N; computer++) {
            String lan = br.readLine();
            sumLength += convertToLength(computer, lan);
        }

        int rst = maxDonateLength(sumLength);
        System.out.println(rst);

        br.close();
    }

    private static int maxDonateLength(int sumLength) {
        boolean[] check = new boolean[N];

        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.add(new Computer(0, 0));
        int connection = 0;
        while (!pq.isEmpty()) {
            Computer poll = pq.poll();
            int computer = poll.getComputer();
            int len = poll.getLength();

            if(check[computer]) continue;
            check[computer] = true;
            connection ++;
            sumLength -= len;

            for (Computer nc : length.get(computer)) {
                int nextComputer = nc.getComputer();
                if(!check[nextComputer]) {
                    pq.add(nc);
                }
            }
        }

        return connection == N ? sumLength : -1;
    }

    private static int convertToLength(int computer, String lan) {
        int sum = 0;
        // 문자열 변환해서 넣기
        for (int col = 0; col < N; col++) {
            char lanChar = lan.charAt(col);
            int lanLength = 0;

            // 소문자인 경우 1, 대문자인 경우 27부터 시작
            if(Character.isLowerCase(lanChar)) lanLength = lanChar - 'a' + 1;
            else if (Character.isUpperCase(lanChar)) lanLength = lanChar - 'A' + 27;

            // 동일한 컴퓨터가 아니고 랜선 길이가 0이상인 경우에만 연결
            if(computer != col && lanLength > 0) {
                length.get(computer).add(new Computer(col, lanLength));
                length.get(col).add(new Computer(computer, lanLength));
            }

            sum += lanLength;
        }
        return sum;
    }

    static class Computer implements Comparable<Computer> {
        private int computer;
        private int length;

        public Computer(int computer, int length) {
            this.computer = computer;
            this.length = length;
        }

        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.length, o.length);
        }

        public int getComputer() {
            return computer;
        }

        public int getLength() {
            return length;
        }
    }
}
