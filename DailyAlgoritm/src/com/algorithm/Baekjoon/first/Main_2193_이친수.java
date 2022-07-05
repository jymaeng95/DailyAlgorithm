package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2193_이친수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long rst = getPinaryNumber(N);
        System.out.println(rst);

        br.close();
    }

    private static long getPinaryNumber(int n) {
        if(n < 3) return 1;

        long[] arr = new long[n + 1];
        arr[1] = arr[2] = 1;

        for(int loop = 3; loop <= n; loop++) {
            arr[loop] = arr[loop - 1] + arr[loop - 2];
        }

        return arr[n];
    }
}
