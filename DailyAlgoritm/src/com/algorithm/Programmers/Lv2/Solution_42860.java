package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.Collections;

//프로그래머스 LV2(조이스틱)
public class Solution_42860 {

    public static void main(String[] args) {

        //M 12
        solution("JEROEN");
        solution("JAZ");
        solution("JAN");
        solution("AAA");
        solution("ZZZZ");
        solution("AGENDA");
        solution("BBBAAAB");
        solution("ABABAAAAABA");

    }

    public static int solution(String name) {
        int answer = 0;
        StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuilder front = new StringBuilder("ABCDEFGHIJKLMN");
        StringBuilder last = new StringBuilder("AZYXWVUTSRQPON");
        StringBuilder alpha = new StringBuilder();
        String[] sortStr = name.split("");
        Arrays.sort(sortStr, Collections.reverseOrder());
        for(String str : sortStr) {
            if (!str.equals("A"))
                alpha.append(str);
        }
        int i=0;

        while(alpha.length()>0){
            char alphabet = alpha.charAt(i);
            if(sb.indexOf(String.valueOf(alphabet)) > 13 ){
                answer += last.indexOf(String.valueOf(alphabet));

            }else{
                answer += front.indexOf(String.valueOf(alphabet));
            }
            i++;
            if(i==alpha.length())
                break ;
            answer++;
        }
        System.out.println(answer);
        return answer;
    }
}
