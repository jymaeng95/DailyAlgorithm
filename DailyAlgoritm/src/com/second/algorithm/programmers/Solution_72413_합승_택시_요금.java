package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_72413_합승_택시_요금 {
    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};


        int rst = solution(n, s, a, b, fares);
        System.out.println("rst = " + rst);

    }

    private static final int INF = 200 * 100_000;
    private static int solution(int n, int s, int a, int b, int[][] fares) {

        // 요금 배열 초기화
        int[][] fees = new int[n + 1][n + 1];
        for (int city = 1; city <= n; city++) {
            Arrays.fill(fees[city], INF);
            fees[city][city] = 0;
        }

        // 도로 생성
        initFees(n, fares, fees);

        // 플로이드 워셜 알고리즘
        floydWarshall(n, fees);

        // 최소 요금 구하기
        int minFee = fees[s][a] + fees[s][b]; // 최초 합승 안한 경우가 최소

        // 합승한 경우 s에서 출발해 도착할 수 있는 도시에서 다시 각각의 도시로의 최소요금 구한 값과 비교
        for (int city = 1; city <= n; city++) {
            minFee = Math.min(minFee, fees[s][city] + fees[city][a] + fees[city][b]);
        }

        return minFee;
    }

    private static void floydWarshall(int n, int[][] fees) {
        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (start == end) continue;
                    if (fees[start][end] > fees[start][mid] + fees[mid][end]) {
                        fees[start][end] = fees[start][mid] + fees[mid][end];
                    }
                }
            }
        }
    }

    private static void initFees(int n, int[][] fares, int[][] fees) {
        for (int[] fare : fares) {
            int departure = fare[0];
            int destination = fare[1];
            int fee = fare[2];

            fees[departure][destination] = fee;
            fees[destination][departure] = fee;
        }
    }
}
