package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_2853_배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] days = new int[N];
        for(int i =0;i<N;i++) {
            days[i] = Integer.parseInt(br.readLine());
        }

        int rst = getMinShip(N, days);
        System.out.println(rst);

        br.close();
    }

    private static int getMinShip(int n, int[] days) {
        int ship = 0;
        List<Integer> list = new ArrayList<>();
        boolean[] check = new boolean[n];
        for(int i=1; i<n;i++) {
            if(!check[i]) {
                list.add(days[i]);
                for (int j = i + 1; j < n; j++) {
                    if ((days[j] - 1) % (days[i] - 1) == 0) {
                        check[j] = true;
                    }
                }
            }
        }
        return list.size();
    }
}
