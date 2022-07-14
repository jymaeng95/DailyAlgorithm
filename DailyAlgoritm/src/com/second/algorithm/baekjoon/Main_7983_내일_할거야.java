package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_7983_내일_할거야 {
    static class Homework implements Comparable<Homework> {

        private int day;    // 걸리는 일자
        private int deadline;   // 마감일자'

        public Homework(int day, int deadline) {
            this.day = day;
            this.deadline = deadline;
        }

        public int getDay() {
            return day;
        }

        public int getDeadline() {
            return deadline;
        }

        @Override
        public int compareTo(Homework o) {
            return Integer.compare(this.deadline - this.day, o.deadline - o.day);
        }
    }

    private static int N;
    private static List<Homework> homeworks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int homework = 0; homework < N; homework++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int deadline = Integer.parseInt(st.nextToken());

            homeworks.add(new Homework(day, deadline));
        }

        int rst = boredDay();
        System.out.println(rst);
        br.close();
    }

    private static int boredDay() {
        Collections.sort(homeworks);
        Homework homework = homeworks.get(0);

        return homework.getDeadline() - homework.getDay() - 1;
    }
}
