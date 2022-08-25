package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16235_나무_재테크 {
    private static int N, M, K;

    private static PriorityQueue<Tree> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] lands = new int[N + 1][N + 1];
        int[][] manure = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(lands[row], 5);
            for (int col = 1; col <= N; col++) {
                manure[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        pq = new PriorityQueue<>();
        for (int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            pq.add(new Tree(age, row, col));
        }

        int rst = financeTree(manure, lands);
        System.out.println(rst);

        br.close();
    }

    private static final int[] DX = {-1, -1, -1,0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static int financeTree(int[][] manure, int[][] lands) {
        // K년까지 봄여름가을겨울
        for (int year = 0; year < K; year++) {
            // 사이즈만큼 빼서 1년 진행
            Queue<Tree> queue = new LinkedList<>();
            int[][] deadTreeManure = new int[N + 1][N + 1];
            while(!pq.isEmpty()) {
                Tree tree = pq.poll();
                int row = tree.getRow();
                int col = tree.getCol();
                int age = tree.getAge();

                // 봄 : 나무가 자신의 나이만큼 양분 먹고, 나이가 1증가한다. 하나칸 여러개 나무 -> 나이 어린 순 양분
                if(lands[row][col] >= age){
                    lands[row][col] -= age;
                    age += 1;
                    queue.add(new Tree(age, row, col));

                    // 가을 : 나무 나이 5의 배수인경우 8방향 나무 생김 (죽지 않은 나무만 가을을 탈 수 있음)
                    if(age % 5 == 0) {
                        for (int direction = 0; direction < 8; direction++) {
                            int nextRow = row + DX[direction];
                            int nextCol = col + DY[direction];
                            if (checkBoundary(nextRow, nextCol)) {
                                queue.add(new Tree(1, nextRow, nextCol));
                            }
                        }
                    }
                }
                // 여름 : 양분 못먹는 나무는 나이만큼 양분으로 변함 = 죽은 나무 나이 / 2가 양분 추가
                else {
                    deadTreeManure[row][col] += age / 2;
                }
            }
            // 겨울 :  양분 추가
            for (int curRow = 1; curRow <= N; curRow++) {
                for (int curCol = 1; curCol <= N; curCol++) {
                    lands[curRow][curCol] += manure[curRow][curCol] + deadTreeManure[curRow][curCol];
                }
            }
            pq.addAll(queue);
        }
        return pq.size();
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 1 && nextRow <= N && nextCol >= 1 && nextCol <= N;
    }

    static class Tree implements Comparable<Tree> {
        private int age;
        private int row;
        private int col;

        public Tree(int age, int row, int col) {
            this.age = age;
            this.row = row;
            this.col = col;
        }

        public int getAge() {
            return age;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }
}
