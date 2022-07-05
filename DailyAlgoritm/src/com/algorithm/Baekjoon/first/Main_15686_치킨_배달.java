package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15686_치킨_배달 {
    static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] city = new int[N][N];
        List<Point> chickenStoreList = new ArrayList<>();
        List<Point> houseList = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 2) chickenStoreList.add(new Point(row, col));
                else if(type == 1) houseList.add(new Point(row, col));
                city[row][col] = type;
            }
        }

        int rst = getMinChickenDistance(N, M, city, chickenStoreList, houseList);
        System.out.println(rst);
        br.close();

    }

    private static int getMinChickenDistance(int n, int m, int[][] city, List<Point> chickenStoreList, List<Point> houseList) {
        // 치킨집에서 조합 구하기
        boolean[] visited = new boolean[chickenStoreList.size()];
        distance = Integer.MAX_VALUE;
        removeChickenStore(n, m, city, chickenStoreList,houseList, visited, 0);

        return distance;
    }
    private static int distance;
    private static void removeChickenStore(int n, int m, int[][] city, List<Point> chickenStoreList, List<Point> houseList, boolean[] visited, int start) {
        if (m == 0) {
            distance = Math.min(distance, recentChickenHouse(chickenStoreList,houseList, visited));
        }

        for (int index = start; index < chickenStoreList.size(); index++) {
            if(!visited[index]) {
                visited[index] = true;
                removeChickenStore(n,m-1, city, chickenStoreList, houseList, visited, index);
                visited[index] = false;
            }
        }
    }

    private static int recentChickenHouse(List<Point> chickenStoreList, List<Point> houseList, boolean[] visited) {
        int sum = 0;
        for(Point house : houseList) {
            int houseDistance = Integer.MAX_VALUE;
            for(int index = 0; index < visited.length; index++) {
                if(visited[index]) {
                    Point chickenStore = chickenStoreList.get(index);
                    houseDistance = Math.min(houseDistance, Math.abs(house.getRow() - chickenStore.getRow()) + Math.abs(house.getCol() - chickenStore.getCol()));
                }
            }
            sum += houseDistance;
        }
        return sum;
    }
}
