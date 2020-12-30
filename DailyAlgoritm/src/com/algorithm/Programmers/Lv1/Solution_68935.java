package com.algorithm.Programmers.Lv1;

public class Solution_68935 {
    public static void main(String[] args) {
        int n = 125;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int answer = 0;
        String baseThree = conversion(n);
        for(int i=0;i<baseThree.length();i++){
            answer += Math.pow(3,i) * Integer.parseInt(String.valueOf(baseThree.charAt(i)));
        }
        return answer;
    }

    private static String conversion(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n % 3);
            n /= 3;
        }
        return sb.reverse().toString();
    }
}
