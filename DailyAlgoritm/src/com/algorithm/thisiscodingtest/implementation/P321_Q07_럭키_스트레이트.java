package com.algorithm.thisiscodingtest.implementation;

import java.io.*;

public class P321_Q07_럭키_스트레이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String N = br.readLine();
        String rst = isLucky(N);

        bw.write(rst);
        bw.flush();

        br.close();
        bw.close();
    }

    private static String isLucky(String n) {
        int left =0;
        int right = 0;
        for(int i =0;i<n.length()/2;i++) {
            left += Integer.parseInt(String.valueOf(n.charAt(i)));
            right += Integer.parseInt(String.valueOf(n.charAt(i+n.length()/2)));
        }

        return left == right ? "LUCKY" : "READY";
    }
}
