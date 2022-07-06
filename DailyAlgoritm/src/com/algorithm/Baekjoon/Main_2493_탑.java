package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탑 {
    static class Tower {
        private int index;
        private int height;

        public Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }

        public int getIndex() {
            return index;
        }

        public int getHeight() {
            return height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Tower> tower = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= N; index++) {
            tower.push(new Tower(index, Integer.parseInt(st.nextToken())));
        }

        int[] rst = getTowerIndex(N, tower);
        for (int index = 1; index <= N; index++) {
            System.out.print(rst[index] + " ");
        }
        br.close();
    }

    private static int[] getTowerIndex(int n, Stack<Tower> tower) {
        int[] towerIndex = new int[n + 1];
        Stack<Tower> buffer = new Stack<>();

        while (!tower.isEmpty()) {
            buffer.push(tower.pop());

            // buffer에  넣은 높이보다 tower의 높이가 더 크면 부딪히는 경우이므로 인덱스 저장
            while (!buffer.isEmpty() && !tower.isEmpty() && buffer.peek().getHeight() < tower.peek().getHeight()) {
                towerIndex[buffer.pop().getIndex()] = tower.peek().getIndex();
            }

        }

        return towerIndex;
    }
}
