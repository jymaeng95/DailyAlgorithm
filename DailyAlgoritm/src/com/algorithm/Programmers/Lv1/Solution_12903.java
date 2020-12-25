package com.algorithm.Programmers.Lv1;

public class Solution_12903 {
    public static void main(String[] args) {
        String s = "qwr";
        System.out.println(solution(s));
    }

    public static String solution(String s) {
        int mid = s.length() / 2;
        return s.length() % 2 == 0 ? Character.toString(s.charAt(mid-1)) + Character.toString(s.charAt(mid)) : Character.toString(s.charAt(mid));
    }
}
