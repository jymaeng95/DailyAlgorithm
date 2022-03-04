package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806_부분합 {
    private static int[] num;
    private static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        num = new int[N + 1];
        sum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int loop = 1; loop <= N; loop++) {
            num[loop] = Integer.parseInt(st.nextToken());
            sum[loop] = sum[loop - 1] + num[loop];
        }

        int rst = getLength(N, S);
        System.out.println(rst);
        br.close();
    }

    private static int getLength(int n, int s) {
        int length = n + 1;
        int left = 0, right = 0;

        while(left <= n || right <= n) {
            if(right <=n) {
                if (sum[right] - sum[left] < s) {
                    right++;
                } else {
                    length = Math.min(length, right-left);
                    left++;
                }
            }
            else {
                if(sum[right-1] - sum[left] >= s) {
                    length = Math.min(length, right-left);
                }
                left++;
            }
        }
        return length == n+1  ? 0 : length;
    }
}
