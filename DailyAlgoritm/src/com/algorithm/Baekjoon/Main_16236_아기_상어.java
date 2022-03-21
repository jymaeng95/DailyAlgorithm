package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16236_아기_상어 {
    static class Shark implements Comparable<Shark> {
        private int row;
        private int col;
        private int weight;
        private int count;

        public Shark() {
        }

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Shark(int row, int col, int weight, int count) {
            this.row = row;
            this.col = col;
            this.weight = weight;
            this.count = count;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getWeight() {
            return weight;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public int compareTo(Shark o) {
            if(this.row == o.row) {
                return Integer.compare(this.col, o.col);
            }
            return Integer.compare(this.row, o.row);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] space = new int[N][N];
        babyShark = new Shark();
        for(int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N ; col++) {
                int fish = Integer.parseInt(st.nextToken());
                if(fish == 9) {
                    babyShark.setRow(row);
                    babyShark.setCol(col);
                    babyShark.setWeight(2);
                    babyShark.setCount(0);
                    fish = 0;
                }
                space[row][col] = fish;
            }
        }
        int rst = babyShark(N, space);
        System.out.println(rst);

        br.close();
    }

    private static boolean[][] visited;
    private static final int[] DX = {-1,0,1,0}; //상 좌 하 우
    private static final int[] DY = {0,-1,0,1}; //상 좌 하 우
    private static int second;
    private static Shark babyShark;
    private static int babyShark(int n, int[][] space) {
        boolean eat = true;    // 물고기를 잡아먹었는지 여부
        second = 0;
        while(eat) {
            visited = new boolean[n][n];
            if(!findFish(n,space, babyShark)) eat = false;
        }

        return second;
    }
    /** flow
     * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
     * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
     * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
     * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
     * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
     * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
     * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
     * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
     */

    private static boolean findFish(int n, int[][] space, Shark babyShark) {
        Queue<Shark> queue = new LinkedList<>();
        List<Shark> eatList = new LinkedList<>();
        int sharkRow = babyShark.getRow();
        int sharkCol = babyShark.getCol();

        queue.add(babyShark);
        visited[sharkRow][sharkCol] = true;
        int curSecond = 0;  // 현재 탐색에서 물고기 찾는데 사용한 시간
        boolean eat = false;    // 먹을 수 있는지 판단

        // 가장 가까운 물고기 찾기 (상 좌 순서) 탐색 순서 상 좌
        while(!queue.isEmpty()) {
            int size = queue.size();
            // 현재 사이클(초)에서 경우의 수 판단하기 위해 큐에 넣은 사이즈만큼만 루프 돌려주기
            for(int move = 0; move < size; move++) {
                Shark shark = queue.poll();
                int curRow = shark.getRow();
                int curCol = shark.getCol();
                int curWeight = shark.getWeight();
                int curCount = shark.getCount();

                for(int direction = 0; direction < 4; direction++) {
                    int nextRow = curRow + DX[direction];
                    int nextCol = curCol + DY[direction];
                    // 다음 방향이 범위 벗어나지 않고 상어가 물고기보다 크기가 큰 경우
                    if(checkBound(n, nextRow, nextCol) && !visited[nextRow][nextCol] && space[nextRow][nextCol] <= curWeight) {
                        // 잡아 먹을 수 있는 경우
                        if(space[nextRow][nextCol] != 0 && space[nextRow][nextCol] < curWeight) {
                            // 우선순위 리스트에 넣기
                            eatList.add(new Shark(nextRow, nextCol));

                            // 물고기를 잡아먹음
                            eat = true;
                        }
                        visited[nextRow][nextCol] = true;
                        queue.add(new Shark(nextRow, nextCol, curWeight, curCount));
                    }
                }
            }

            // 현재 초에서 아기 상어가 갈 수 있는 모든 경우의 수를 구한 경우
            if(eat) {
                // 우선순위 정렬
                Collections.sort(eatList);
                Shark fishPosition = eatList.get(0);
                int fishRow = fishPosition.getRow();
                int fishCol = fishPosition.getCol();

                // 현재 물고기 찾은 시간 증가
                curSecond++;

                // 먹을 수 있는 물고기를 찾았으므로 현재 탐색 시간만큼 초 증가
                second += curSecond;

                // 물고기 먹고 난 후 빈칸으로 갱신
                space[fishRow][fishCol] = 0;

                // 아기상어 위치 갱신
                babyShark.setRow(fishRow);
                babyShark.setCol(fishCol);

                // 지금까지 먹은 물고기 개수와 방금 먹은 물고기 == 아기 상어 사이즈 같으면 크기 증가
                if(babyShark.getCount()+1 == babyShark.getWeight()) {
                    babyShark.setWeight(babyShark.getWeight() + 1);   // 물고기 먹은 후 크기증가
                    babyShark.setCount(0);                            // 먹은 개수 0ㅇ으로 초기화
                }
                else babyShark.setCount(babyShark.getCount()+1);       //아닌 경우 물고기 먹은 개수 증가
                return true;
            }
            curSecond++;
        }
        return false;
    }

    private static boolean checkBound(int n, int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n;
    }
}
