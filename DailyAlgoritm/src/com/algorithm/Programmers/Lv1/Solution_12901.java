package com.algorithm.Programmers.Lv1;

public class Solution_12901 {
    public static void main(String[] args) {
        int a = 5;
        int b = 24;
        System.out.println(solution(a,b));
    }
    private static final String[] DAY_OF_WEEK = {"FRI","SAT","SUN","MON","TUE","WED","THU"};
    private static final int[] DAY = {31,29,31,30,31,30,31,31,30,31,30,31};
    public static String solution(int a, int b) {
        int day = 0;
        for(int i=0;i<a-1;i++){
            day += DAY[i];
        }
        day += b;

        return DAY_OF_WEEK[(day-1) % 7];
    }
}
