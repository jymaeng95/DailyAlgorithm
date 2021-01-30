package com.algorithm.LeetCode.easy;

public class Solution_7_Reverse_Integer {
    public static void main(String[] args) {
        int reverse = reverse(333);
        System.out.println(reverse);
    }
    public static int reverse(int x) {
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        if(x < 0) {
            sb.deleteCharAt(0);

            return Integer.parseInt(sb.append("-").reverse().toString());
        }
        return Integer.parseInt(sb.reverse().toString());
    }
}
