package com.algorithm.Programmers.devmatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {
    public static void main(String[] args) {
        int[][] dist = {{0, 5, 2, 4, 1}, {5, 0, 3, 9, 6}, {2, 3, 0, 6, 3}, {4, 9, 6, 0, 3}, {1, 6, 3, 3, 0}};
        int[][] rst = solution(dist);
//        for (int[] x : rst) {
//            for (int y : x) {
//                System.out.println("y = " + y);
//            }
//            System.out.println();
//        }
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

    public static int[][] solution(int[][] dist) {
        List<List<Integer>> answer = new ArrayList<>();
        int answerIndex = 0;
        for (int row = 0; row < dist.length; row++) {
            int[] length = new int[100000001];
            Point[] index = new Point[dist.length];
            Arrays.fill(length, -1);
            length[row] = 0;
            for (int col = 0; col < dist.length; col++) {
                // 0-1 =5 , 0-2 = 2, 0-3 = 4, 0-4 = 1
                int distance = dist[row][col];

                //현재 위치 row로부터 떨어진 거리에 점 넣기
                length[distance] = col;

                //현재 위치 row로 부터 떨어진 col 점의 위치
                index[col] = new Point(col, distance);
            }

            boolean rst = true;
            for (int next = 0; next < dist.length; next++) {
                if (Math.abs((index[row].getDistance() - index[next].getDistance())) != dist[row][next]) {
                    rst = false;
                    break;
                }
            }

            if (rst) {
                answer.add(new ArrayList<>());
                Arrays.sort(index);
                for (Point point : index) {
                    System.out.println("point = " + point.getDot());
                    answer.get(answerIndex).add(point.getDot());
                }
                answerIndex++;
            }
        }

        int[][] rst = new int[answer.size()][dist.length];
        for (int size = 0; size < answer.size(); size++) {
            int index = 0;
            for (int dot : answer.get(size)) {
                rst[size][index] = dot;
                index++;
            }
        }
        return rst;
    }
}
