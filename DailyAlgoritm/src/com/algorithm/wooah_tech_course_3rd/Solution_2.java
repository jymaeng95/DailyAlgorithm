package com.algorithm.wooah_tech_course_3rd;

public class Solution_2 {
    public static void main(String[] args) {
        solution("0001112","*");
    }
    public static long[] solution(String s, String op) {
        long[] answer = new long[s.length()-1];
        for (int i = 0;i < s.length() - 1; i++){
            int first = Integer.parseInt(s.substring(0,i+1));
            int second = Integer.parseInt(s.substring(i+1,s.length()));
            answer[i] = calcWithOp(first,second,op);
        }
        return answer;
    }
    public static long calcWithOp(int first, int second, String op){
        long decrypt = 0L;
        switch (op){
            case "+" :
                decrypt = first + second;
                break;
            case "-" :
                decrypt = first - second;
                break;
            case "*" :
                decrypt = first * second;
                break;
        }
        return decrypt;
    }
}
