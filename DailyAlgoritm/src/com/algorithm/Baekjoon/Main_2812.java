package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;


//1876591234   91234
public class Main_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int delCount = Integer.parseInt(st.nextToken());
        int size = N - delCount;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        String[] num = br.readLine().split("");

        while(sb.length() < size){
            int max = 0;
            int newIndex = 0;
            if(index + delCount <num.length) {
                for (int i = index; i <= num.length - delCount; i++) {
                    if (max < Integer.parseInt(num[i])) {
                        newIndex = i;
                        max = Integer.parseInt(num[i]);
                    }
                }
            }
            sb.append(max);
            delCount -= newIndex - index;
            index = newIndex+1;
            if(delCount == 0||num.length-index+sb.length() == size) {
                for (int i = index; i < num.length; i++)
                    sb.append(num[i]);
                break;
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
