package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1107_리모컨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] remote = new boolean[10];
        Arrays.fill(remote, true);
        if(M>0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                remote[x] = false;
            }
        }

        int rst = clickCount(target, M, remote);
        System.out.println(rst);

        br.close();
    }

    private static int clickCount(int target, int m, boolean[] remote) {
        int channel = 100;
        int count = Math.abs(target-channel);

        for(int i =0;i<=1000000; i++) {
            int click = check(i, remote);
            if(click > 0) {
                count = Math.min(count, click + Math.abs(i - target));
            }
        }
        return count;
    }

    private static int check(int i, boolean[] remote) {
        int num = i;
        if(i==0) {
            if(remote[0]) return 1;
            return 0;
        }
        else {
            // 완탐 시 고장난 버튼 클릭 불가
            while(i > 0) {
                int button = i%10;
                if(!remote[button]) return 0;
                i /= 10;
            }
        }
        return String.valueOf(num).length();
    }
}
