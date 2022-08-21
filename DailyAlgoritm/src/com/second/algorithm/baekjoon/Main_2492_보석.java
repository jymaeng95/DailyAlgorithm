package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2492_보석 {
    private static int N, M, T, K;
    private static List<Gem> gems;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        gems = new ArrayList<>();

        for (int index = 0; index < T; index++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            gems.add(new Gem(x, y));
        }

        int[] rst = collectGems();
        System.out.println(rst[0] + " " + rst[1]);
        System.out.println(rst[2]);

        br.close();
    }

    private static int[] collectGems() {
        int[] gemInfo = new int[3];
        // 광물이 있는 곳을 꼭짓점으로 네방향에 대해 사각형을 만든다.
        for (Gem gem : gems) {
            int x = gem.getX();
            int y = gem.getY();
            for (Gem nextGem : gems) {
                int xx, yy;
                if(gem.getX() + K > N) xx = N - K;
                else xx = gem.getX();
                if(nextGem.getY()+K>M) yy = M - K;
                else yy = gem.getY();

                System.out.println("xx" + xx+" " + " yy"+ yy );
                // 현재 보석은 무조건 사각형 안에 들어감
                int count = 0;
                // 사각형 양끝점 범위 체크
//                if (checkBoundary(startX, startY, endX, endY)) {
                for (Gem findGem : gems) {

                        // 사각형 내 보석 체크
                        if (findGem.getX() >= xx && findGem.getX() <= xx + K && findGem.getY() >= yy && findGem.getY() <= yy + K) {

                            count++;
                        }

                    }
//                }

                if (gemInfo[2] <= count) {
                    gemInfo[0] = xx;
                    gemInfo[1] = yy+K;
                    gemInfo[2] = count;

                }
            }
        }
        return gemInfo;
    }

    private static boolean checkBoundary(int startX, int startY, int endX, int endY) {
        return startX >= 0 && startX <= N && endX <= N && endX >= 0 && startY >= 0 && startY <= M && endY >= 0 && endY <= M;
    }

    static class Gem {
        private final int x;
        private final int y;

        public Gem(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
