package com.algorithm.Programmers.Lv1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_12915_문자열_내_마음대로_정렬하기 {
    public static void main(String[] args) {
        String[] strings = {"abce", "abcd", "cdx"};
        int n = 2;
        String[] solution = solution(strings, n);
        for(String s : solution){
            System.out.println(s);
        }
    }
    public static String[] solution(String[] strings, int n) {
        List<String> list = Arrays.asList(strings);
        Collections.sort(list,((o1, o2) -> {
            if(o1.charAt(n) > o2.charAt(n))
                return 1;
            return -1;
        }));

        return list.toArray(String[]::new);
    }
}
