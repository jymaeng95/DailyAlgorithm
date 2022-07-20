package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_말이_되고픈_원숭이 {
    private static int K, W, H;
    private static int[][] world;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());    // 원숭이 가 말 흉내 가능 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        world = new int[H][W];
        for (int height = 0; height < H; height++) {
            st = new StringTokenizer(br.readLine());
            for (int width = 0; width < W; width++) {
                world[height][width] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = escapeZoo();
        System.out.println(rst);

        br.close();
    }

    private static int[] DX = {-2, -2, -1, -1, -1, 0, 0, 1, 1, 1, 2, 2};
    private static int[] DY = {-1, 1, -2, 0, 2, -1, 1, -2, 0, 2, -1, 1};

    private static int escapeZoo() {
        boolean[][][] visited = new boolean[H][W][K + 1]; // 횟수로 3차원 배열 생성
        int count = Integer.MAX_VALUE;

        // 12 방향 모두 체크
        for (int direction = 0; direction <= K; direction++) {
            Arrays.fill(visited[0][0], true);
        }

        Queue<Monkey> queue = new LinkedList<>();
        queue.add(new Monkey(0, 0, 0, K));

        while (!queue.isEmpty()) {
            Monkey monkey = queue.poll();

            int height = monkey.getHeight();
            int width = monkey.getWidth();
            int moveCnt = monkey.getMoveCnt();
            int likeHorse = monkey.getLikeHorse();

            if (height == H - 1 && width == W - 1)
                count = Math.min(count, moveCnt);

            for (int direction = 0; direction < 12; direction++) {
                int nextHeight = height + DX[direction];
                int nextWidth = width + DY[direction];
                boolean moveHorse = Math.abs(DX[direction]) + Math.abs(DY[direction]) == 3;

                // 12방향을 모두 체크하고 남은 말 흉내횟수로 현재 위치에 도착하지 않았고ㅡ 장애물이 아닌 경우
                if (checkBoundary(nextHeight, nextWidth) && world[nextHeight][nextWidth] != 1) {

                    // 말 흉내낼 수 있고 말 횟수를 줄였을 때 최초로 해당 위치에 도착하는 경우
                    if (moveHorse) {
                        if(likeHorse > 0 && !visited[nextHeight][nextWidth][likeHorse-1]) {
                            queue.add(new Monkey(nextHeight, nextWidth, moveCnt + 1, likeHorse - 1));
                            visited[nextHeight][nextWidth][likeHorse - 1] = true; // 현재까지 남은 말 흉내횟수로 최초로 현재위치에 도착하는 경우이기 때문에 반드시 최소로 움직임을 보장
                        }
                    }
                    // 그냥 이동하는 경우
                    else {
                        // 현재 말 흉내 횟수로 최초로 도착하는 경우
                        if (!visited[nextHeight][nextWidth][likeHorse]) {
                            queue.add(new Monkey(nextHeight, nextWidth, moveCnt + 1, likeHorse));
                            visited[nextHeight][nextWidth][likeHorse] = true;
                        }
                    }
                }
            }
        }
        return count != Integer.MAX_VALUE ? count : -1;
    }

    private static boolean checkBoundary(int nextHeight, int nextWidth) {
        return nextHeight >= 0 && nextHeight < H && nextWidth >= 0 && nextWidth < W;
    }

    static class Monkey {
        private int height;
        private int width;
        private int moveCnt;
        private int likeHorse;

        public Monkey(int height, int width, int moveCnt, int likeHorse) {
            this.height = height;
            this.width = width;
            this.moveCnt = moveCnt;
            this.likeHorse = likeHorse;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public int getMoveCnt() {
            return moveCnt;
        }

        public int getLikeHorse() {
            return likeHorse;
        }
    }
}
