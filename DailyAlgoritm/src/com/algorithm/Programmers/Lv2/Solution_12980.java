package com.algorithm.Programmers.Lv2;

public class Solution_12980 {
    public static void main(String[] args) {
        System.out.println(solution(1000000000));
    }

    public static int solution(int n) {
        int ans = 0;
        while(n>0) {
            if(n%2==0){
                n /= 2;
                continue;
            }
            n -= 1;
            ans++;
        }

        return ans;
    }
}
