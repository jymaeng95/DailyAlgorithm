package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11054_가장_긴_바이토닉_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= N; index++) {
            arr[index] = Integer.parseInt(st.nextToken());
        }

        int rst = getBitonicSubsequence(N, arr);
        System.out.println(rst);

        br.close();
    }

    private static int getBitonicSubsequence(int n, int[] arr) {
        int[] increaseDp = new int[n + 2];
        int[] decreaseDp = new int[n + 2];

        // 증가하는 부분 수열 구하기
        for (int index = 1; index <= n; index++) {
            for (int checkIndex = 0; checkIndex < index; checkIndex++) {
                if(arr[index] > arr[checkIndex]) {
                    increaseDp[index] = Math.max(increaseDp[index], increaseDp[checkIndex] + 1);
                }
            }
        }

        // 감소하는 부분 수열 구하기
        for (int index = n; index >= 1; index--) {
            for (int checkIndex = n + 1; checkIndex > index; checkIndex--) {
                if(arr[index] > arr[checkIndex]) {
                    decreaseDp[index] = Math.max(decreaseDp[index], decreaseDp[checkIndex] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int increaseIndex = 1; increaseIndex <= n; increaseIndex++) {
            for (int decreaseIndex = increaseIndex + 1; decreaseIndex <= n+1; decreaseIndex++) {
                if(arr[increaseIndex] > arr[decreaseIndex]) {
                    // 증가한 부분의 길이 + 다음 위치부터 최대 인덱스 값보다 작은 위치까지의 감소 부분 수열 길이
                    maxLength = Math.max(maxLength, increaseDp[increaseIndex] + decreaseDp[decreaseIndex]);
                }
            }
        }

        return maxLength;
    }
}
