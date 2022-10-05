package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_1264_모음의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String str = br.readLine();
            if(str.equals("#")) break;

            long count = Pattern.compile("[aeiouAEIOU]").matcher(str).results().count();
            System.out.println(count);
        }

        br.close();
    }
}
