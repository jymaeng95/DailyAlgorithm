package com.algorithm.Programmers.Lv2;

public class Solution_12951 {
    public static void main(String[] args) {
        System.out.println(solution(" 3k "));
    }

    public static String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] sp = s.split("");
        sp[0] = sp[0].toUpperCase();
        for(int i=1;i<sp.length;i++){
            if(sp[i-1].equals(" ")) {
                sp[i] = sp[i].toUpperCase();
                continue;
            }
            sp[i] = sp[i].toLowerCase();
        }

        for(String str : sp)
            answer.append(str);
        return answer.toString();
    }
}
