package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1246_온라인_판매 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Integer[] price = new Integer[M];
        for(int i=0;i<M;i++) {
            price[i] = Integer.parseInt(br.readLine());
        }

        int[] rst = getMaxPrice(N,M,price);
        System.out.println(rst[0]+" "+rst[1]);
        br.close();
    }

    private static int[] getMaxPrice(int n, int m, Integer[] price) {
        // 달걀 개수가 고객 보다 작은 경우
        Arrays.parallelSort(price, Collections.reverseOrder());
        int eggs = Math.min(n, m);
        int profit = 0;
        int egg = 1;
        int index =0;
        for(int i=0;i<price.length;i++) {
            int pc = price[i]* egg;
            if(profit < pc) {
                profit = pc;
                index = i;
            }
            if(egg < eggs) egg++;
        }
        return new int[]{price[index], profit};

    }
}
