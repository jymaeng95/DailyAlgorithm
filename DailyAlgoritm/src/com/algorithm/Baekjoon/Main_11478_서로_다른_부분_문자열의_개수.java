package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_11478_서로_다른_부분_문자열의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int rst = subStringCount(S);
        System.out.println(rst);

        br.close();
    }

    private static Set<String> subset;
    private static int subStringCount(String s) {
        subset = new HashSet<>();
        makeSubString(1,  s);
        return subset.size();
    }

    private static void makeSubString(int length, String s) {
        if(length == s.length()) {
            subset.add(s);
            return;
        }

        for (int index = 0; index <= s.length()-length; index++) {
            subset.add(s.substring(index, index + length));
        }
        makeSubString(length + 1, s);
    }


}
