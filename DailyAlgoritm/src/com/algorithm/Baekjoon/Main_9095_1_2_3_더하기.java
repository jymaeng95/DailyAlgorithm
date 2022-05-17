package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9095_1_2_3_더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int testCase = 0;
        while(testCase < N) {
            int rst = sum123(Integer.parseInt(br.readLine()));
            System.out.println(rst);

            testCase++;
        }
        br.close();
    }

    private static int sum123(int input) {
        if(input == 1) return 1;
        if(input == 2) return 2;
        if(input == 3) return 4;

        int[] arr = new int[input+1];
        arr[1] = 1;     // 1
        arr[2] = 2;     // 1+1, 2;
        arr[3] = 4;     // 1+1, 1+2, 2+1, 3

        for (int index = 4; index <= input; index++) {
            // arr[index - 숫자] => 해당 인덱스 값에 각각 숫자를 붙여서 만들 수 있는 경우의 수
            arr[index] = arr[index - 1] + arr[index - 2] + arr[index - 3];
        }

        return arr[input];
    }
}
