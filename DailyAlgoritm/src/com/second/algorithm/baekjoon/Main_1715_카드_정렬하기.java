package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1715_카드_정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        for (int index = 0; index < N; index++) {
            arr[index] = Integer.parseInt(br.readLine());
        }

        long rst = getCompareCount(N, arr);
        System.out.println(rst);

        br.close();
    }

    private static long getCompareCount(int n, long[] arr) {
        // 사이즈 1인 경우 비교 안함
        if(n == 1) return 0;

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (long card : arr) {
            pq.add(card);
        }

        long count = 0;
        long compare = 0;

        // 사이즈 1인 경우 = 카드 모두 석은 경우
        while(!pq.isEmpty() && pq.size() >= 2) {
            // 두개씩 빼서 비교
            long first = pq.poll();
            long second = pq.poll();

            // 첫번째 두번째 카드 비교
            count = first + second;

            // 비교횟수 더해주기
            compare += count;
            pq.add(count);
        }

        return compare;
    }
}
