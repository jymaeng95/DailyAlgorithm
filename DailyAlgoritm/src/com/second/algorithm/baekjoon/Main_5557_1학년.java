package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5557_1학년 {
    private static int N;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        numbers = new int[N];
        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(st.nextToken());
        }

        long rst = getExpCount();
        System.out.println(rst);

        br.close();
    }

    private static long getExpCount() {
        long[][] dp = new long[N - 1][21];
        dp[0][numbers[0]] = 1;

        // N-1 index까지 확인하고 0부터 20이 되는 경우의 수를 더해준다.
        for (int index = 1; index < N - 1; index++) {
            for (int number = 0; number <= 20; number++) {
                // 이전 인덱스까지 더하거나 뺀 숫자 중에 0이상 20이하인 값이 있다면
                if (dp[index - 1][number] > 0) {
                    int plus = number + numbers[index];     // 이전 인덱스 더하거나 뺀 값 + 현재 위치의 숫자값
                    if (plus >= 0 && plus <= 20)
                        dp[index][plus] += dp[index - 1][number];   // 더한 값이 0 이상 20이하이면 가능한 경우의 수이므로 현재 인덱스에 경우의 수를 더해준다.

                    int minus = number - numbers[index];    // 이전 인덱스 더하거나 뺀 값 - 현재 위치의 숫자값
                    if (minus >= 0 && minus <= 20)
                        dp[index][minus] += dp[index - 1][number];  // 뺀 값이 0 이상 20이하이면 가능한 경우의 수이므로 현재 인덱스에 경우의 수를 더해준다.
                }
            }
        }

        // 마지막 숫자는 정답이 되야 하므로 제외 되기 때문에 (N-2)
        // 마지막 숫자의 경우의수를 구해야한다.
        return dp[N - 2][numbers[N - 1]];
    }
}
