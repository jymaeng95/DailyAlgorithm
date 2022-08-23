package com.second.algorithm.programmers;
import java.util.*;
public class Solution_86971_전력망을_둘로_나누기 {
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int rst = solution(n, wires);
    }

    private static Map<Integer, List<Integer>> lines;

    private static int solution(int n, int[][] wires) {
        // 송전탑 초기화
        lines = new HashMap<>();
        for(int tower = 1; tower <= n; tower++) {
            lines.put(tower, new ArrayList<>());
        }

        // 송전선 연결
        for(int[] wire : wires) {
            int towerA = wire[0];
            int towerB = wire[1];

            lines.get(towerA).add(towerB);
            lines.get(towerB).add(towerA);
        }

        // 송전선 자르기
        int count = n;
        for(int[] wire : wires) {
            int towerA = wire[0];
            int towerB = wire[1];
            int seperate = cutLines(n, towerA, towerB);
            count = Math.min(count, Math.abs(n - seperate - seperate));
        }

        return count;
    }

    private static int cutLines(int n, int towerA, int towerB) {
        int count = 0;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(towerA);

        while(!queue.isEmpty()) {
            int tower = queue.poll();

            if(visited[tower]) continue;
            visited[tower] = true;
            count++;

            for(Integer nextTower : lines.get(tower)) {
                if(tower == towerA && towerB == nextTower)  continue;
                if(!visited[nextTower]) {
                    queue.add(nextTower);
                }
            }
        }

        return count;
    }
}
