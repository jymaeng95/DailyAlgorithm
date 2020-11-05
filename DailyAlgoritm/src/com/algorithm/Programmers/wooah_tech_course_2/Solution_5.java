package com.algorithm.Programmers.wooah_tech_course_2;

public class Solution_5 {
    public static void main(String[] args) {
        System.out.println(solution(10000));
    }
    public static int solution(int number){
        int answer = 0;

        for(int i=0;i<=number;i++){
            String num = String.valueOf(i);
            for(int j=0;j<num.length();j++){
                if(num.charAt(j)=='3' || num.charAt(j)=='6' || num.charAt(j)=='9'){
                    answer++;
                }
            }
        }

        return answer;
    }

}
