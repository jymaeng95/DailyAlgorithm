package com.algorithm.thisiscodingtest.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P201_떡볶이_떡_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] riceCake = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < riceCake.length; i++) {
            riceCake[i] = Integer.parseInt(st.nextToken());
        }

        int length = makeDdeokBokki(M, riceCake);
        System.out.println(length);
        br.close();
    }

    private static int makeDdeokBokki(int length, int[] riceCake) {
        int max = 0;
        for (int x : riceCake) {
            max = Math.max(max, x);
        }

        int start = 0;
        int end = max;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            int cutLength = 0;
            for (int x : riceCake) {
                if(x > mid)
                cutLength += x - mid;
            }
            if(cutLength == length){
                break;
            }
            if(cutLength > length) {
                start = mid + 1;
            }else {
                end = mid -1;
            }
        }
        return mid;
    }
}
