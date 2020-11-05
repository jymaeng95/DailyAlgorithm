package com.algorithm.Programmers.month_challenge;

public class Solution_2 {
    public static int count;
    public static int zeroCount;
    public static void main(String[] args) {
        int [] answer = solution("110010101001");
        for(int x :  answer){
            System.out.println(x);
        }
    }
    public static int[] solution(String s) {
        int[] answer = new int[2];
        count =0;
        zeroCount=0;
        recursive(s);
        answer[0] = count;
        answer[1] = zeroCount;
        return answer;
    }
    public static void recursive(String s){
        if(s.equals("1"))
            return;
        count++;
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c=='0'){
                zeroCount++;
                continue;
            }
            sb.append(c);
        }
        recursive(Integer.toBinaryString(sb.length()));
    }
}
