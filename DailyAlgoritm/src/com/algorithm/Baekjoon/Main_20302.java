package com.algorithm.Baekjoon;

import java.io.*;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main_20302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        String[] str = new String[N + N -1];
        for(int i=0;i<str.length;i++)
            str[i] = st.nextToken();
        BigDecimal first = new BigDecimal(str[0]);
        BigDecimal bottom = new BigDecimal(1);
        for (int i = 1; i < str.length - 1; i++) {
            if (str[i].equals("/")) {
                bottom = bottom.multiply(new BigDecimal(str[i+1]));
            }
            if (str[i].equals("*"))
                first = first.multiply(new BigDecimal(str[i+1]));
        }
        if(first.remainder(bottom).intValue() == 0)
            bw.write("mint chocolate");
        else
            bw.write("toothpaste");

        br.close();
        bw.close();
    }
}
