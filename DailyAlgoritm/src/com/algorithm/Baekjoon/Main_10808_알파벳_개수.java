package com.algorithm.Baekjoon;

import java.io.*;

public class Main_10808_알파벳_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();

        for (char c = 'a'; c <= 'z'; c++) {
            int count=0;
            for(char ch :  word.toCharArray()){
                if(ch == c){
                    count++;
                }
            }
            bw.write(count+" ");
        }

        br.close();
        bw.close();
    }
}
