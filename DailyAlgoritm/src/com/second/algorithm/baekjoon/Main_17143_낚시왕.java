package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17143_낚시왕 {
    private static int R, C, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Shark[][] map = new Shark[R + 1][C + 1];

        for (int shark = 0; shark < M; shark++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            map[row][col] = new Shark(row, col, speed, direction, size);
        }

        int rst = startFishing(map);
        System.out.println(rst);

        br.close();
    }

    private static final int[] DX = {0, -1, 1, 0, 0};
    private static final int[] DY = {0, 0, 0, 1, -1};
    private static int startFishing(Shark[][] map) {
        /**
         * 낚시왕이 오른쪽으로 한 칸 이동한다.
         * 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
         * 상어가 이동한다.
         * 상어가 1초에 1칸, 방향으로 속도
         * 상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다. 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
         */
        int catchSize = 0;
        List<Shark> sharks = new ArrayList<>();
        for (int king = 1; king <= C; king++) {
            // 가장 가까운 상어 잡기
            for (int row = 1; row <= R; row++) {
                if (map[row][king] != null) {
                    catchSize += map[row][king].getSize();
                    map[row][king] = null;
                    break;
                }
            }

            // 상어를 리스트에 넣어준다.
            for (int row = 1; row <= R; row++) {
                for (int col = 1; col <= C; col++) {
                    if(map[row][col] != null) sharks.add(map[row][col]);
                }
            }

            // 상어 이동
            for (Shark shark : sharks) {
                move(shark);
            }

            // 상어 배열에 넣어주기 (큰 값이 들어가도록)
            map = makeMap(sharks);
            sharks.clear();
        }

        return catchSize;
    }

    private static Shark[][] makeMap(List<Shark> sharks) {
        Shark[][] map = new Shark[R + 1][C + 1];
        for (Shark shark : sharks) {
            Shark containShark = map[shark.getRow()][shark.getCol()];
            if(containShark != null) {
                if(containShark.getSize() < shark.getSize()) map[shark.getRow()][shark.getCol()] = shark;
            }
            else {
                map[shark.getRow()][shark.getCol()] = shark;
            }
        }

        return map;
    }

    private static void move(Shark shark) {
        int speed = shark.getSpeed();

        for (int time = 1; time <= speed; time++) {
            int direction = shark.getDirection();
            int row = shark.getRow();
            int col = shark.getCol();
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];

            // 범위안이라면
            if (checkBoundary(nextRow, nextCol)) {
                shark.setRow(nextRow);
                shark.setCol(nextCol);
            } else {
                if(direction % 2 != 0) shark.setDirection(direction + 1);
                else shark.setDirection(direction - 1);
                time--;
            }
        }
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return 1 <= nextRow && nextRow <= R && 1 <= nextCol && nextCol <= C;
    }

    static class Shark {
        private int row;
        private int col;
        private int speed;
        private int direction;
        private int size;

        public Shark(int row, int col, int speed, int direction, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "row=" + row +
                    ", col=" + col +
                    ", speed=" + speed +
                    ", direction=" + direction +
                    ", size=" + size +
                    '}';
        }
    }
}
