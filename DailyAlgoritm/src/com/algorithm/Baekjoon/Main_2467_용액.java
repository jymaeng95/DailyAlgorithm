package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] liquor = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            liquor[index] = Integer.parseInt(st.nextToken());
        }

        String rst = getLiquor(N, liquor);
        System.out.println(rst);

        br.close();
    }

    private static String getLiquor(int n, int[] liquor) {
        int left = 0;
        int right = n - 1;
        int sumLiquor = liquor[0] + liquor[n - 1];
        int leftLiquor = liquor[0];
        int rightLiquor = liquor[n - 1];
        StringBuilder sb = new StringBuilder();

        while (left < right) {
            if (Math.abs(liquor[left] + liquor[right]) < Math.abs(sumLiquor)) {
                sumLiquor = liquor[left] + liquor[right];
                leftLiquor = liquor[left];
                rightLiquor = liquor[right];
            }

            if(liquor[left] + liquor[right] > 0)  {
                right--;
            }
            else if(liquor[left] + liquor[right] < 0) {
                left++;
            }
            else {
                return sb.append(liquor[left]).append(" ").append(liquor[right]).toString();
            }
        }
        return sb.append(leftLiquor).append(" ").append(rightLiquor).toString();
    }
}
