package com.algorithm.thisiscodingtest.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class P99_1이_될_때까지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = getCountWhenOne(N,K);
        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getCountWhenOne(int n, int k) {
        int count = 0;
        count += n - (n/k) * k;
        while(true) {
            count++;
            if(n/k ==  1)
                break;
            n/=k;
        }

        return count;
    }
}
