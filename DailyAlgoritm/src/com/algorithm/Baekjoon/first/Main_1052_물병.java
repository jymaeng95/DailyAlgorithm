package com.algorithm.Baekjoon.first;

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

        int rst = buyBottleCount(N, K);
        System.out.println(rst);
        br.close();
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
