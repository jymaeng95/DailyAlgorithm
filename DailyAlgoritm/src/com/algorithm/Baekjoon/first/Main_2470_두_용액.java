package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2470_두_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] liquor = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int count = 0; count < N; count++) {
            liquor[count] = Integer.parseInt(st.nextToken());
        }

        String rst = mergeTwoLiquor(N, liquor);
        System.out.println(rst);


        br.close();
    }

    private static String mergeTwoLiquor(int n, int[] liquor) {
        StringBuilder rst = new StringBuilder();
        Arrays.sort(liquor);

        int right = n - 1;
        int left = 0;
        int merge = liquor[right] + liquor[left];
        int leftIdx = left, rightIdx = right;
        while (left < right) {
            // 이전 용해액 합친 경우와 현재 인덱스 합친경우 비교
            if (Math.abs(merge) > Math.abs(liquor[right] + liquor[left])) {
                merge = liquor[right] + liquor[left];
                leftIdx = left;
                rightIdx = right;
            }
            //용해액 합친게 양수인 경우 양수값 줄여주기
            if (liquor[right] + liquor[left] > 0) right--;

            // 용해액 합친게 음수인 경우 음수값 늘려주기
            else if (liquor[right] + liquor[left] < 0) left++;

            // 0인 경우는 아무거나 출력 가능
            else return rst.append(liquor[left]).append(" ").append(liquor[right]).toString();
        }

        return rst.append(liquor[leftIdx]).append(" ").append(liquor[rightIdx]).toString();
    }
}
