package com.algorithm.Programmers.wooah_tech_course_2nd;

public class Solution_7 {
    public static void main(String[] args) {
        System.out.println(solution("browoanoommnaon"));
    }
    public static String solution(String cryptogram){
        StringBuilder answer = new StringBuilder(cryptogram);
        boolean flag = false;
        while(!flag && answer.length()>0){
            for(int i=0;i<answer.length()-1;i++){
                if(answer.charAt(i)==answer.charAt(i+1)){
                    answer.replace(i,i+2,"");
                    flag = false;
                    break;
                }
                flag = true;
            }
            if(!flag)
                continue;
            flag = true;
        }
        return answer.toString();
    }
}
