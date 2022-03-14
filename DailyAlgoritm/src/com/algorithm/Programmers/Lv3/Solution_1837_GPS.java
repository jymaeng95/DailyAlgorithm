package com.algorithm.Programmers.Lv3;

import java.util.*;

public class Solution_1837_GPS {
    public static void main(String[] args) {
        int n = 7;
        int m = 10;
        int[][] edge_list = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}};
        int k = 6;
        int[] gps_log = {1, 2, 3, 3, 6, 7};
//        int n = 7;
//        int m = 5;
//        int[][] edge_list = {{1,5}, {5,2},{5,3},{5,4},{5,6}};
//        int k = 8;
//        int[] gps_log = {1,5,2,3,4,6,6,7};
        int rst = solution(n, m, edge_list, k, gps_log);
        System.out.println("rst = " + rst);
    }

    private static List<List<Integer>> graph;
    private static int count;
    private static final int INF = (int) 1e9;

    //    private static Stack<Integer> path;
    private static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new ArrayList<>();
//        path = new Stack<>();
        count = k + 1;
        for (int vertex = 0; vertex <= n; vertex++) {
            graph.add(new ArrayList<>());
            graph.get(vertex).add(vertex);
        }

        for (int[] edge : edge_list) {
            int fr = edge[0];
            int to = edge[1];

            graph.get(fr).add(to);
            graph.get(to).add(fr);
        }
        count = moveTaxi(n, m, gps_log, k);
//        count = moveTaxi(gps_log[0],k,gps_log);
//        moveTaxi(gps_log[0],0,0,k,gps_log);
//        path.add(gps_log[0]);
//        moveTaxi(gps_log[0],0,k,gps_log);
        return count > k ? -1 : count;
    }

    private static int moveTaxi(int n, int m, int[] gps_log, int k) {
        int[][] error = new int[k][n + 1];
        for (int loop = 0; loop < k; loop++) {
            Arrays.fill(error[loop], INF);
        }
        error[0][gps_log[0]] = 0;
        for (int time = 1; time < k; time++) {  // 시간 지날 수록
            for (int node = 1; node <= n; node++) {  // 모든 노드에 대해서 확인
                for (int cur : graph.get(node)) {  // 연결된 노드
                    if (gps_log[time] != cur) {
                        error[time][cur] = Math.min(error[time][cur], error[time - 1][node] + 1);     // 현재 노드 에러개수와 이전 시간의 연결된 노드의 에러개수+1 중 작은 것
                    } else {
                        error[time][cur] = Math.min(error[time][cur], error[time - 1][node]);         // 현재 노드 에러개수와 이전 시간의 연결된 노드의 에러개수 중 작은 것
                    }
                }
            }
        }

        return error[k - 1][gps_log[k - 1]];
    }

    /*private static int moveTaxi(int start, int k, int[] gps_log) {
        int error = k+1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        for(int time = 0; time < k; time ++) {
            int size = queue.size();
            for(int loop = 0; loop < size; loop++) {
                Node cur = queue.poll();
                int curNode = cur.getNode();
                int curError = cur.getError();
                if(time == k-1) {
                    if(curNode != gps_log[time]) continue;
                    error = Math.min(error, curError);
                }
                if(gps_log[time] != curNode) curError += 1;
                    for(int next : graph.get(curNode)) {

                        queue.add(new Node(next, curError));
                    }
            }
        }
        return error;
    }*/

   /* private static void moveTaxi(int node, int time, int error, int k, int[] gps_log) {
        // 시간 다 지난 경우
        if(time == k-1) {
            if(gps_log[time] != node) return;       // 하차 위치 맞지 않은 경우
            count = Math.min(count,error);          // 최소 에러 개수 갱신
            return;
        }

        for(int next: graph.get(node)) {
            if(gps_log[time+1] != next)  moveTaxi(next,time+1, error+1, k, gps_log);        // 다음 노드가 로그와 틀리면 에러 개수 증가
            else moveTaxi(next, time+1, error, k, gps_log);                                       // 다음 노드가 로그와 동일하면 에러아님
        }
    }*/

// 경로 탐색 방식
    /*private static void moveTaxi(int node, int time, int k, int[] gps_log) {
        // 시간 다 지난 경우
        if(time == k-1) {
            // 하차 위치가 맞지 않은 경우
            if(gps_log[time] != node) return;

            int error = 0;
            // 경로 탐색 틀린 부분은 에러, 마지막에 도착은 하는 경우
            for(int log_time = 0; log_time < k-1; log_time++) {
                if(gps_log[log_time] != path.get(log_time)) error++;
            }

            count = Math.min(count, error);
            return;
        }
        for(int next :  graph.get(node)) {
            path.add(next);
            moveTaxi(next, time +1, k, gps_log);
            path.pop();
        }
    }*/
}
