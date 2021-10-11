package com.algorithm.LeetCode.greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution_11_Container_With_Most_Water {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] height = {1,8,6,2,5,4,8,3,7};
        int maxArea = maxArea(height);
        br.close();
        bw.close();
    }

    private static int maxArea(int[] height) {
        List<Point> pointList = new ArrayList<>();
        for(int i=1; i<=height.length; i++) {
            pointList.add(new Point(i,height[i]));
        }



        return 0;
    }
}
