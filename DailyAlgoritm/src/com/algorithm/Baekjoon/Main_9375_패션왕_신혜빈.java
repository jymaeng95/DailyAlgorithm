package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_9375_패션왕_신혜빈 {
    private static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for(int i=0;i<TC;i++) {
            int count = Integer.parseInt(br.readLine());
            int fashion = getFashion(count);
            System.out.println(fashion);
        }

        br.close();
    }

    private static int getFashion(int count) throws IOException {
        int rst = 1;
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<count; i++) {
            String[] clothes = br.readLine().split(" ");
            if(map.containsKey(clothes[1])) {
                map.replace(clothes[1],map.get(clothes[1])+1);
                continue;
            }
            map.put(clothes[1],1);
        }

        for(int x : map.values()) {
            rst *= (x+1);
        }

        return rst - 1;
    }
}
