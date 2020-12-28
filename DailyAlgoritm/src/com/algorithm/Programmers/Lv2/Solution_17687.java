package com.algorithm.Programmers.Lv2;

public class Solution_17687 {
    public static void main(String[] args) {
        int n = 11;
        int t = 5;
        int m = 3;
        int p = 1;
        System.out.println(solution(n, t, m, p));
    }

    public static String solution(int n, int t, int m, int p) {
        // t * m = length;
        // length % m == p - 1 인경우만 출력
        // i증가하면서 i/n , i%n 붙히기
        // String length == length break;
        int stringSize = t * m;
        StringBuilder sb = new StringBuilder();
        int num = 0;

        //숫자 증가시키면서 넣기
        while (sb.length() <= stringSize) {
            char[] strArr = convert(num,n).toCharArray();
            for(char c : strArr)
                sb.append(c);
            num++;
        }

        //튜브 차례에 출력
        StringBuilder answer = new StringBuilder();
        for (int i = p-1; i < sb.length(); i+=m) {
                answer.append(sb.charAt(i));
                if(answer.length() == t)
                    break;
        }
        return answer.toString();
    }

    //진법 변환
    private static String convert(int num, int n){
        if(num == 0 ) return "0";
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            String eachRemainder = String.valueOf(num % n).replace("10", "A").replace("11", "B")
                    .replace("12", "C").replace("13", "D")
                    .replace("14", "E").replace("15", "F");
            sb.append(eachRemainder);
            num /= n;
        }
        return sb.reverse().toString();
    }
}
