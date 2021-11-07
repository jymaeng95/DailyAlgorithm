package com.algorithm.thisiscodingtest.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P197_부품_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] tool = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < tool.length; i++) {
            tool[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] target = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < target.length; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        searchTarget(tool, target);

        br.close();
    }

    private static void searchTarget(int[] tool, int[] target) {
        Arrays.parallelSort(tool);
        for(int tg : target) {
            int start = 0;
            int end = tool.length-1;
            boolean rst = false;
            while(start <= end){
                int mid = (start+end)/2;
                if(tg == tool[mid]) {
                    rst = true;
                    break;
                }
                if(tool[mid] > tg) {
                    end = mid -1;
                }else {
                    start = mid+1;
                }
            }
            if(rst) System.out.println("yes");
            else System.out.println("no");
        }
    }
}
