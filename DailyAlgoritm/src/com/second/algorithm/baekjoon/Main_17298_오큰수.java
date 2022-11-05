package com.second.algorithm.baekjoon;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17298_오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            arr[index] = Integer.parseInt(st.nextToken());
        }

        int[] rst = rightBiggerNumber(N, arr);
        for (int index = 0; index < N; index++) {
            bw.write(rst[index] + " ");
        }

        bw.flush();
        br.close();
    }

    private static int[] rightBiggerNumber(int n, int[] arr) {
        int[] bigNumber = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int index = n - 1; index >= 0; index--) {
            if (!stack.isEmpty()) {
                // 현재 인덱스 숫자와 비교
                while (!stack.isEmpty() && arr[index] >= stack.peek()) {
                    stack.pop();
                }

                // 스택이 빈 경우 현재 숫자보다 큰 숫자 없는 것
                if (stack.isEmpty()) bigNumber[index] = -1;
                else bigNumber[index] = stack.peek();

                stack.push(arr[index]);
            } else {
                bigNumber[index] = -1;
                stack.push(arr[index]);
            }
        }

        return bigNumber;
    }
}
