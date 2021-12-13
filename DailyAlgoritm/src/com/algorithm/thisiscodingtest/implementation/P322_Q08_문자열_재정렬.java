package com.algorithm.thisiscodingtest.implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class P322_Q08_문자열_재정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String rst = getSortStr(str);

        bw.write(rst);
        bw.flush();
        br.close();
        bw.close();
    }

    private static String getSortStr(String str) {
        int num = 0;
        List<Character> list = new ArrayList<>();
        for(char c : str.toCharArray()) {
            if(c - '0' >=0 && c -'0' <10) num += c -'0';
            else list.add(c);
        }
        Collections.sort(list);
        Iterator<Character> iterator = list.stream().iterator();
        StringBuilder sb =new StringBuilder();
        while(iterator.hasNext()) {
            sb.append(iterator.next());
        }
        sb.append(num);

        return sb.toString();
    }
}
