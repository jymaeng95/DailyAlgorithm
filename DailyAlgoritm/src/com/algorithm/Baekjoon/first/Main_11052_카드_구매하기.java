package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11052_카드_구매하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] cost = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int card = 1; card <= N; card++) {
            cost[card] = Integer.parseInt(st.nextToken());
        }

        int rst = getMaxCost(N, cost);
        System.out.println(rst);
        br.close();
    }

    private static int getMaxCost(int n, int[] cost) {
        /**
         * N번째 카드 값이 최대로 되게 사려면, (N-1 + 1), (N-2 +2) ... 이런 식으로 N이 될 수 있는 최대값을 만들어 갱신해준다.
         * 카드 최대 개수가 1000개 이므로 이중 반복문해도 시간초과가 안난다.
         */
        for(int card = 2; card <= n; card++) {
            for(int prev = 1; prev < card; prev++) {
                cost[card] = Math.max(cost[card], cost[card - prev] + cost[prev]);
            }
        }
        return cost[n];
    }
}
