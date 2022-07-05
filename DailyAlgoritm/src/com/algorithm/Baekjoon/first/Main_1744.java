package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Integer> minusSeq = new ArrayList<>();
        List<Integer> plusSeq = new ArrayList<>();
        boolean zero = false;
        for(int i =0;i<N;i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) zero = true;
            if(num > 0) plusSeq.add(num);
            else if(num < 0)  minusSeq.add(num);
        }

        if(minusSeq.size() % 2 != 0 && zero) {
            minusSeq.add(0);
        }

        Collections.sort(minusSeq);
        Collections.sort(plusSeq,Collections.reverseOrder());
        int sum = 0;

        for (int i = 0; i < minusSeq.size() - 1; i += 2) {
            sum += minusSeq.get(i) * minusSeq.get(i + 1);
        }
        if(minusSeq.size() % 2 != 0) {
            sum += minusSeq.get(minusSeq.size()-1);
        }

        for(int i = 0;i<plusSeq.size()-1;i+=2){
            if(plusSeq.get(i+1) != 1) {
                sum+= plusSeq.get(i) * plusSeq.get(i+1);
                continue;
            }
            sum += plusSeq.get(i) +  plusSeq.get(i+1);
        }
        if(plusSeq.size() % 2 != 0) {
            sum += plusSeq.get(plusSeq.size()-1);
        }

        bw.write(String.valueOf(sum));
        br.close();
        bw.close();
    }
}
