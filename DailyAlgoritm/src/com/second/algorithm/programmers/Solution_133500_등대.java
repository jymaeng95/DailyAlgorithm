package com.second.algorithm.programmers;

import java.util.*;

public class Solution_133500_등대 {
    public static void main(String[] args) {
//        int n = 10;
//        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
//        int n = 8;
//        int[][] lighthouse = {{1, 2}, {2, 5}, {1, 4}, {4, 6}, {1, 3}, {3, 7}, {7, 8}};
        int n = 2;
        int[][] lighthouse = {{1, 2}};
        int rst = solution(n, lighthouse);
        System.out.println("rst = " + rst);
    }

    private static Map<Integer, List<Integer>> lightHouses;

    private static int count;
    private static int solution(int n, int[][] lighthouse) {
        lightHouses = init(n, lighthouse);

        boolean[] check = new boolean[n + 1];
        boolean[] turnOn = new boolean[n + 1];
        count = 0;

        dfs(1, n, check, turnOn);
        for (boolean on : turnOn) {
            if(on) count++;
        }
        return count;
    }

    private static boolean dfs(int house, int n, boolean[] check, boolean[] turnOn) {
        // 리프 노드의 부모는 불을 켜야함
        check[house] = true;
        for (Integer nextHouse : lightHouses.get(house)) {
            if(!check[nextHouse]) {
                if(dfs(nextHouse, n, check, turnOn) || lightHouses.get(nextHouse).size() == 1) turnOn[house] = true;
            }
        }
        return !turnOn[house];
    }

    private static Map<Integer, List<Integer>> init(int n, int[][] lighthouse) {
        lightHouses = new HashMap<>();
        for (int house = 1; house <= n; house++) {
            lightHouses.put(house, new ArrayList<>());
        }

        for (int[] house : lighthouse) {
            int from = house[0];
            int to = house[1];

            lightHouses.get(from).add(to);
            lightHouses.get(to).add(from);
        }

        return lightHouses;
    }
}
