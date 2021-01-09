package com.algorithm.Programmers.Lv1;

public class Solution_12912 {
    public static void main(String[] args) {
        solution(a,b);
    }

    public static long solution(int a, int b) {
        long answer = 0;
        if(a>b) {
            int temp = a;
            a = b;
            b = temp;
        }

        for(int i =a;i<=b;i++){
            answer += i;
        }
        return answer;
    }
}
