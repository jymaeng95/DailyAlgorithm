package com.algorithm.Programmers.Lv3;

import java.util.PriorityQueue;

public class Solution_42884_단속카메라 {
    public static void main(String[] args) {
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        int solution = solution(routes);
        System.out.println("solution = " + solution);
    }

    private static int solution(int[][] routes) {
        int count = 1;
        PriorityQueue<Camera> pq = new PriorityQueue<>();
        for(int[] route : routes) {
            pq.offer(new Camera(route[0], route[1]));
        }

        int rangeIn = pq.peek().getIn();
        int rangeOut = pq.peek().getOut();
        while(!pq.isEmpty()) {
            Camera camera = pq.poll();
            int curIn = camera.getIn();
            int curOut = camera.getOut();

            if(rangeIn < curIn && curIn <= rangeOut) {
                rangeIn = curIn;
            }
            else if(rangeOut < curIn){
                rangeIn = curIn;
                rangeOut = curOut;
                count++;
            }
        }
        return count;
    }

    static class Camera implements Comparable<Camera> {
        private int in;
        private int out;

        public Camera(int in, int out) {
            this.in = in;
            this.out = out;
        }

        public int getIn() {
            return in;
        }

        public int getOut() {
            return out;
        }


        @Override
        public int compareTo(Camera o) {
            return Integer.compare(this.out, o.out);
        }
    }
}
