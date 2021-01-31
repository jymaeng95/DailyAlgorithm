package com.algorithm.LeetCode.easy;

public class Solution_7_Reverse_Integer {
    public static void main(String[] args) {
        int reverse = reverse(111111111111);
        System.out.println(reverse);
    }
    public static int reverse(int x) {
        long reverse = 0;
        while(x!=0){
            reverse *= 10;
            reverse += x % 10;
            x /= 10;
        }
        return (int) reverse == reverse ? (int) reverse : 0;
    }
}
