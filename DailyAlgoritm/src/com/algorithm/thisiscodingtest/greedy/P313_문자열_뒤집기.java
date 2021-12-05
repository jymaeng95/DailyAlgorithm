package com.algorithm.thisiscodingtest.greedy;

import java.io.*;

public class P313_문자열_뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int rst = getReverseCount(s);

        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getReverseCount(String s) {
        int rst = 0;
        char now = s.charAt(0);
        int one = 0;
        int zero = 0;
        if(now == '1') one = 1;
        else zero = 1;
        for(char c : s.toCharArray()) {
            if(now != c) {
                now = c;
                if(c == '1') one++;
                else zero++;
            }
        }

        return Math.min(one,zero);

    }
}
