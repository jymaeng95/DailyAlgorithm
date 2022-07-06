package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1052_물병_신규 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int rst = buyBottle(N, K);
        System.out.println(rst);
        br.close();
    }

    private static int buyBottle(int n, int k) {
        /**
         * 1. K-1번 돌면서 남은 물병 보다 작은 2의 base 제곱 값 빼주기 (최대한 많은 물병을 이용해 하나로 만들기)
         * 2. 마지막은 해당 수보다 큰 2의 base 제곱 값 구해서 남은 물병 빼주기
         */
        if(n <= k) return 0;

        // 1. K-1번 돌면서 남은 물병 보다 작은 2의 base 제곱 값 빼주기 (최대한 많은 물병을 이용해 하나로 만들기)
        for (int loop = 0; loop < k - 1; loop++) {
            int base = 0;
            while (Math.pow(2, base) < n) {
                base++;
            }
            n -= Math.pow(2, base - 1);

            // 남은 물병이 없는 경우 더이상 물병을 살 필요가 없으므로 return 0
            if(n == 0) return 0;
        }

        // 2. 마지막은 해당 수보다 큰 2의 base 제곱 값 구해서 남은 물병 빼주기
        int base = 0;
        while (Math.pow(2, base) < n) {
            base++;
        }
        return (int) Math.pow(2, base) - n;
    }
}
