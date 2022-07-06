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
                // 1. 부분합이 찾고자 하는 값 보다 작으므로 부분합을 증가시켜야함
                if (sum[right] - sum[left] < s) {
                    right++;
                }
                // 2. 부분합이 찾고자 하는 값 보다 크거나 같으므로 길이 비교후 부분합을 감소시켜야함
                else {
                    length = Math.min(length, right-left);
                    left++;
                }
            }
            // 3. 오른쪽 포인터가 마지막에 가도 왼쪽 포인터는 값을 끝까지 비교해줘야함
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
