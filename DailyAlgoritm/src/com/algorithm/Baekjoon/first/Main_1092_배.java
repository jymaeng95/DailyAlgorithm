package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1092_배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int[] craneWeight = new int[N];
        for (int crane = 0; crane < N; crane++) {
            craneWeight[crane] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        stringTokenizer = new StringTokenizer(br.readLine());
        int[] boxWeight = new int[M];
        for (int box = 0; box < M; box++) {
            boxWeight[box] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int minute = getMinute(N, M, craneWeight, boxWeight);
        System.out.println(minute);

        br.close();
    }

    private static int getMinute(int n, int m, int[] craneWeight, int[] boxWeight) {
        Arrays.sort(boxWeight);
        Arrays.sort(craneWeight);


        if (boxWeight[m-1] > craneWeight[n-1]) return -1;

        int minute = 0;
        int boxCount = m;
        while (boxCount > 0) {
            // 크레인 작동
            for (int crane : craneWeight) {
                if(boxCount < 1) break;
                // 박스가 남아있다면,
                for(int boxIndex = m-1; boxIndex >= 0; boxIndex--) {
                    if(boxWeight[boxIndex] != 0 && crane >= boxWeight[boxIndex]) {
                        boxWeight[boxIndex] = 0;
                        boxCount--;
                        break;
                    }
                }
            }
            minute++;
        }
        return minute;
    }
}
