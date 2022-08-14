package com.second.algorithm.exam.challenge.third;

import java.util.*;

public class Solution_4 {
    public static void main(String[] args) {
//        int n = 10;
//        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
        int n = 4;
        int[][] lighthouse = {{1, 2}, {2, 3}, {3, 4}};
        int rst = solution(n, lighthouse);
        System.out.println("rst = " + rst);
    }

    private static Map<Integer, List<Integer>> seaRoads;
    private static int solution(int n, int[][] lighthouse) {
        if(lighthouse.length == 1) return 1;
        seaRoads = new HashMap<>();
        for (int house = 1; house <= n; house++) {
            seaRoads.put(house, new ArrayList<>());
        }

        // 뱃길 저장
        for (int[] house : lighthouse) {
            int start = house[0];
            int end = house[1];

            seaRoads.get(start).add(end);
            seaRoads.get(end).add(start);
        }

        // 리프 노드 찾은 각 리프노드의 부모를 Set에 넣기
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        int[] lightCount = findLastLightHouse(1, visited,1);
        return Math.min(lightCount[0], lightCount[1]);
    }

    private static int[] findLastLightHouse(int house, boolean[] visited, int visitCount) {
        // 리프 등대인 경우 (처음 시작이 리프노드인 경우는 제외하고)
        if(visitCount > 1 && seaRoads.get(house).size() == 1) {
            // 등대 키거나 끄거나
            return new int[] {1,0};
        }

        int pick = 1; int notPick = 0;
        for (Integer nextHouse : seaRoads.get(house)) {
            if(!visited[nextHouse]) {
                visited[nextHouse] = true;
                int[] result = findLastLightHouse(nextHouse, visited, visitCount + 1);

                // 이전 등대가 켜진 경우 (안 킨 경우과 킨 경우 중 고르기)
                pick += Math.min(result[0], result[1]);

                //등대가 꺼진 경우 현재 등대는 반드시 켜아함
                notPick += result[0];
            }
        }
        return new int[]{pick, notPick};
    }
}
