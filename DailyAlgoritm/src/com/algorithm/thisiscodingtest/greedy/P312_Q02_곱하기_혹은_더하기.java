package com.algorithm.thisiscodingtest.greedy;

import java.io.*;

public class P312_Q02_곱하기_혹은_더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String num = br.readLine();

        int rst = getCalc(num);
        bw.write(String.valueOf(rst));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int getCalc(String num) {
        int rst = 0;
        for(char c : num.toCharArray()) {
            int number = c -'0';
            if(number <= 1 || rst <= 1)
                rst += number;
            else
                rst *= number;
        }
        return rst;
    }
}
