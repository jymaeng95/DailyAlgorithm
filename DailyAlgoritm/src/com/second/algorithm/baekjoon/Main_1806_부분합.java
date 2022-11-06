package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806_부분합 {
    private static int N, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(st.nextToken());
        }

        int length = getMinLength(numbers);
        System.out.println(length);

        br.close();
    }

    private static int getMinLength(int[] numbers) {
        int[] prefix = makePartSum(numbers);

        int min = 1;
        int max = N;
        int minLength = N + 1;
        while (min <= max) {
            int mid = (min + max) / 2;

            // 부분합을 만들 수 있다면 길이를 줄인다.
            if (isPossibleSum(mid, prefix)) {
                minLength = Math.min(minLength, mid);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return minLength == N + 1 ? 0 : minLength;
    }

    private static boolean isPossibleSum(int mid, int[] prefix) {
        for (int index = 0; index <= N - mid; index++) {
            int sum = prefix[index + mid] - prefix[index];
            if (sum >= S) return true;
        }
        return false;
    }

    private static int[] makePartSum(int[] numbers) {
        int[] prefix = new int[N + 1];
        for (int index = 0; index < N; index++) {
            prefix[index + 1] = prefix[index] + numbers[index];
        }

        return prefix;
    }
}
