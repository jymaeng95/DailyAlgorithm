package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2285_우체국 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Village> villages = new ArrayList<>();
        for (int index = 0; index < N; index++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            villages.add(new Village(number, people));
        }

        System.out.println(villages.stream().sorted().findFirst().get().number);

        br.close();
    }
    static class Village implements Comparable<Village> {
        private long number;
        private long peeple;

        public Village(long number, long peeple) {
            this.number = number;
            this.peeple = peeple;
        }

        @Override
        public int compareTo(Village o) {
            return Long.compare(o.peeple, this.peeple);
        }
    }
}
