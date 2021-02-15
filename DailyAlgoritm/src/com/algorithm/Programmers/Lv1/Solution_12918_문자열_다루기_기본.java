package com.algorithm.Programmers.Lv1;

public class Solution_12918_문자열_다루기_기본 {
    public static void main(String[] args) {
        boolean solution = solution("1234");
        System.out.println(solution);
    }

    public static boolean solution(String s) {
        String num = "0123456789";
        if(s.length() == 4 || s.length() == 6 ) {
            for(char c : s.toCharArray()){
                if(!num.contains(String.valueOf(c)))
                    return false;
            }
            return true;
        }
        return false;
    }
}
