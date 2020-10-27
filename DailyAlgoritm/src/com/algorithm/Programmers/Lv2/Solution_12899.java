package com.algorithm.Programmers.Lv2;

//프로그래머스 (124 나라의 숫자)
public class Solution_12899 {
    public static String solution(int n){
        //String 효율성 에러 해결
        StringBuilder answer = new StringBuilder();

        while(n>0){
            if(n%3 == 0) {
                //n이 3으로 나누어 떨어지면 몫을 -1 해주고 4를 붙여줌
                answer.append(4);
                n = n/3 -1 ;
            } else {
                answer.append(n%3);
                n /= 3;
            }
        }
        return answer.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(Solution_12899.solution(11));
    }
}
