package com.algorithm.Programmers.devmatching;

import java.util.Arrays;

public class Solution_2 {
    public static void main(String[] args) {
        int[][] dist = {{0, 5, 2, 4, 1},
                        {5, 0, 3, 9, 6},
                        {2, 3, 0, 6, 3},
                        {4, 9, 6, 0, 3},
                        {1, 6, 3, 3, 0}};
        int[][] rst = solution(dist);
        for (int[] x : rst) {
            for (int y : x) {
                System.out.println("y = " + y);
            }
            System.out.println();
        }
    }

    private static int[][] solution(int[][] dist) {
        int max = 0;
        int maxRow = 0;
        for (int row = 0; row < dist.length; row++) {
            for (int col = row+1; col < dist.length; col++) {
                if(max < dist[row][col]) {
                    maxRow = row;
                    max = dist[row][col];
                }
            }
        }

        Point[] answer = new Point[dist.length];
        for(int col =0; col < dist.length; col++) {
            answer[col] = new Point(col, dist[maxRow][col]);
        }
        Arrays.parallelSort(answer);
        int[][] rst = new int[2][dist.length];
        for(int col = 0; col < dist.length; col++) {
            rst[0][col] = answer[col].getDot();
            rst[1][col] = answer[dist.length-col-1].getDot();
        }
        return rst;
    }

    static class Point implements Comparable<Point> {
        private int dot;
        private int distance;

        public Point(int dot, int distance) {
            this.dot = dot;
            this.distance = distance;
        }

        public int getDot() {
            return dot;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
