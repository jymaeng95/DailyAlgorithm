package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_암호_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String[] alpha = new String[C];
        boolean[] visited = new boolean[C];
        for(int i=0;i<C;i++) {
            alpha[i] = st.nextToken();
        }
        Arrays.parallelSort(alpha);

        makePassword(0,L,C,alpha,visited,new StringBuilder());
        //4 6
        //a t c i s w

        br.close();
    }

    private static void makePassword(int start, int l, int c, String[] alpha, boolean[] visited, StringBuilder s) {
        if(l == 0) {
            // 최소 모음 1개 자음 2개 체크 = 모음 체크 ,자음 = 길이 -모음
            int vowels = 0;
            for(int i=0;i<s.length();i++) {
                char chr = s.charAt(i);
                if(chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u') vowels ++;
            }
            int consonant = s.length() - vowels;

            if(vowels >= 1 && consonant >= 2) {
                System.out.println(s);
            }
            return;
        }
        for(int i=start;i<c;i++) {
            visited[i] = true;
            s.append(alpha[i]);
            makePassword(i+1, l-1, c, alpha,visited,s);
            s.deleteCharAt(s.length()-1);
            visited[i] = false;
        }
    }
}
