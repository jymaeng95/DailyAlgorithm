package com.algorithm.Programmers.wooah_tech_course_2;

public class Solution_3 {
    public static void main(String[] args) {
        System.out.println(solution("I love you"));
    }
    public static String solution(String word){
        StringBuilder answer = new StringBuilder();
        String alpha = "abcdefghijklmnopqrstuvwxyz ";
        String reversAlpha = "zyxwvutsrqponmlkjihgfedcba ";
        for(int i=0; i<word.length(); i++){
            int index=alpha.indexOf(word.charAt(i));
            if(index == -1){
                index = alpha.indexOf(String.valueOf(word.charAt(i)).toLowerCase());
                answer.append(String.valueOf(reversAlpha.charAt(index)).toUpperCase());
                continue;
            }
            answer.append(reversAlpha.charAt(index));
        }

        return answer.toString();
    }
}
