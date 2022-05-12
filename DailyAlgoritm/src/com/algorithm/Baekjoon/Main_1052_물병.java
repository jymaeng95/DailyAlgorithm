package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1052_물병 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

//        int rst = buyBottle(N, K);
        int rst = buyBottleCount(N, K);
        System.out.println(rst);
        br.close();
    }

    // 실패 코드
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
        }

        // 2. 마지막은 해당 수보다 큰 2의 base 제곱 값 구해서 남은 물병 빼주기
        int base = 0;
        while (Math.pow(2, base) < n) {
            base++;
        }
        return (int) Math.pow(2, base) - n;
    }

    // 정답 코드
    private static int buyBottleCount(int n, int k) {
        /**
         * 1. 현재 가진 물병과 물병 구매 개수를 더해 하나의 물병으로 합쳐진 개수를 구한다.
         * 2. 합쳐진 물병 개수가 K개보다 작거나 같으면 성공
         * 3. 큰 경우 현재 물병으로 K개를 만들 수없으므로 물병 추가 구매
         */
        if(n <= k) return 0;

        int buy = 0;
        while(true) {
            int bottle = n + buy;
            int count = 0;
            while(bottle > 0) {
                if(bottle % 2 != 0) {
                    // 2로 나누어 떨어지지 않으면 하나의 물병으로 다 합쳐진 것이므로 개수 증가
                    count++;
                }
                bottle /= 2;
            }
            // 합쳐진 물병 개수가 k보다 작으면 성공
            if(count <= k) break;

            // 물병 구매 개수 증가
            buy++;
        }
        return buy;
    }
}
