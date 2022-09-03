package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19238_스타트_택시 {
    private static int N, M;
    private static final int WALL = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                int type = Integer.parseInt(st.nextToken());

                // 벽인 경우 -1 치환
                if (type == 1) map[row][col] = WALL;
                else map[row][col] = type;
            }
        }

        // 최초 택시 정보 저장
        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        Taxi taxi = new Taxi(0, row, col, K);

        for (int passenger = 1; passenger <= M; passenger++) {
            st = new StringTokenizer(br.readLine());
            int standRow = Integer.parseInt(st.nextToken());
            int standCol = Integer.parseInt(st.nextToken());
            int departRow = Integer.parseInt(st.nextToken());
            int departCol = Integer.parseInt(st.nextToken());

            map[standRow][standCol] = passenger;        // 서있는 위치
            map[departRow][departCol] = passenger + M; // 도착지 정보
        }

        int rst = transportTaxi(taxi, map);
        System.out.println(rst);

        br.close();
    }

    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {1, -1, 0, 0};

    private static int transportTaxi(Taxi taxi, int[][] map) {
        // 현재 택시 위치 부터 승객 다 태울때 까지 반복
        for (int passenger = 1; passenger <= M; passenger++) {
            Taxi startTaxi = goClosePassenger(taxi, map);

            // 현재 택시에서 가장 가까운 손님에 도달할 수 없는 경우
            if (startTaxi.getFuel() <= 0) return -1;

            // 목적지 이동
            int prevFuel = startTaxi.getFuel();
            Taxi arriveTaxi = goDeparture(startTaxi, map);

            // 목적지까지 도착 못하는 경우
            if (arriveTaxi.getFuel() < 0) return -1;

            // 도달하는 경우
            int chargeFuel = prevFuel - arriveTaxi.getFuel();
            taxi = new Taxi(0, arriveTaxi.getRow(), arriveTaxi.getCol(), arriveTaxi.getFuel() + chargeFuel * 2);
        }
        return taxi.getFuel();
    }

    private static Taxi goDeparture(Taxi taxi, int[][] map) {
        boolean[][][] visited = new boolean[N + 1][N + 1][4];
        Queue<Taxi> queue = new LinkedList<>();
        queue.add(taxi);

        for (int direction = 0; direction < 4; direction++) {
            visited[taxi.getRow()][taxi.getCol()][direction] = true;
        }

        int arriveRow = 0;
        int arriveCol = 0;
        int remainFuel = 0;
        while (!queue.isEmpty()) {
            Taxi curTaxi = queue.poll();
            int passenger = curTaxi.getPassenger();
            int curRow = curTaxi.getRow();
            int curCol = curTaxi.getCol();
            int curFuel = curTaxi.getFuel();

            if (map[curRow][curCol] == passenger + M) {
                arriveRow = curRow;
                arriveCol = curCol;
                remainFuel = curFuel;
                break;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];

                if (checkBoundary(nextRow, nextCol) && !visited[nextRow][nextCol][direction] && map[nextRow][nextCol] != WALL) {
                    visited[nextRow][nextCol][direction] = true;

                    queue.add(new Taxi(passenger, nextRow, nextCol, curFuel - 1));
                }
            }
        }

        // 도달 못하는 경우 추가 필요

        return new Taxi(0, arriveRow, arriveCol, remainFuel);
    }

    private static Taxi goClosePassenger(Taxi taxi, int[][] map) {
        boolean[][][] visited = new boolean[N + 1][N + 1][4];
        Queue<Taxi> queue = new LinkedList<>();
        queue.add(taxi);

        for (int direction = 0; direction < 4; direction++) {
            visited[taxi.getRow()][taxi.getCol()][direction] = true;
        }

        int passenger = M + 1; // 여기부터 도착점 위치
        int standRow = 0;
        int standCol = 0;
        int remainFuel = 0;
        while (!queue.isEmpty() && passenger == M + 1) {
            int size = queue.size();

            // 같은 길이에 있는 경우
            for (int loop = 0; loop < size; loop++) {
                Taxi curTaxi = queue.poll();
                int curRow = curTaxi.getRow();
                int curCol = curTaxi.getCol();
                int curFuel = curTaxi.getFuel();

                if (map[curRow][curCol] > 0 && map[curRow][curCol] <= M) {
                    if (passenger > map[curRow][curCol]) {
                        passenger = Math.min(passenger, map[curRow][curCol]);
                        standRow = curRow;
                        standCol = curCol;
                        remainFuel = curFuel;
                    }
                }

                for (int direction = 0; direction < 4; direction++) {
                    int nextRow = curRow + DX[direction];
                    int nextCol = curCol + DY[direction];

                    if (checkBoundary(nextRow, nextCol) && !visited[nextRow][nextCol][direction] && map[nextRow][nextCol] != WALL) {
                        visited[nextRow][nextCol][direction] = true;

                        queue.add(new Taxi(0, nextRow, nextCol, curFuel - 1));
                    }
                }
            }
        }

        // 현재 위치 태운 것 표시
        map[standRow][standCol] = 0;

        return new Taxi(passenger, standRow, standCol, remainFuel);
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 1 && nextRow <= N && nextCol >= 1 & nextCol <= N;
    }

    static class Taxi {
        private int passenger;
        private int row;
        private int col;
        private int fuel;

        public Taxi(int passenger, int row, int col, int fuel) {
            this.passenger = passenger;
            this.row = row;
            this.col = col;
            this.fuel = fuel;
        }

        public int getPassenger() {
            return passenger;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getFuel() {
            return fuel;
        }
    }
}
