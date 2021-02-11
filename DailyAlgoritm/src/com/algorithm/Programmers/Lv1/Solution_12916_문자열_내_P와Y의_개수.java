package com.algorithm.Programmers.Lv1;

public class Solution_12916_문자열_내_P와Y의_개수 {
    public static void main(String[] args) {
        boolean result = solution("pPoooyY");
        System.out.println(result);
    }
    static boolean solution(String s) {
        int p = 0;
        int y = 0;
        for(char c : s.toLowerCase().toCharArray()){
            if(c == 'p')
                p++;
            if(c == 'y')
                y++;
        }
        return p == y;
    }
}
