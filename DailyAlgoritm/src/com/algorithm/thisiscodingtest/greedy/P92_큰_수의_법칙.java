package com.algorithm.thisiscodingtest.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class P92_큰_수의_법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] splitData = br.readLine().split(" ");
        int n = Integer.parseInt(splitData[0]);
        int m = Integer.parseInt(splitData[1]);
        int k = Integer.parseInt(splitData[2]);

        splitData = br.readLine().split(" ");
        int[] data = new int[splitData.length];

        for(int i=0;i<splitData.length;i++) {
            data[i] = Integer.parseInt(splitData[i]);
        }

        int answer = bigNumberEasy(n, m, k, data);
        int answer2 = bigNumber(m,k,data);
        bw.write(String.valueOf(answer));
        bw.write(String.valueOf(answer2));
        bw.flush();
        br.close();
        bw.close();
    }
    private static int bigNumberEasy(int n, int m, int k, int[] data) {
        int rst = 0;

        List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
        list.sort(Collections.reverseOrder());

        int cnt = 0 ;
        // 숫자가 더해지눈 횟수 m,  연속해서 더할 수 있는 횟수 k
        for(int i =0; i < m ; i++) {
            if(cnt < k) {
                rst += list.get(0);
                cnt++;
            }else {
                rst += list.get(1);
                cnt = 0;
            }
        }

        return rst;
    }
    private static int bigNumber(int m, int k, int[] data) {
        int rst = 0;
        List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
        list.sort(Collections.reverseOrder());
        int repeat = m / (k+1);
        int remain = m % (k+1);
        int firstCycle = list.get(0) * k + list.get(1);

        rst = firstCycle * repeat + list.get(0) * remain;
        return rst;
    }
}
