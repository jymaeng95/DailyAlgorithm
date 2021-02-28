package com.algorithm.Programmers.Lv1;

public class Solution_12922_수박수박수박수박수 {
    public static void main(String[] args) {
        int n = 3;
        String solution = solution(n);
        System.out.println(solution);
    }
    public static String solution(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<n;i++){
            if(i%2==0) {
                sb.append("수");
                continue;
            }
            sb.append("박");
        }
        return sb.toString();
    }
}
