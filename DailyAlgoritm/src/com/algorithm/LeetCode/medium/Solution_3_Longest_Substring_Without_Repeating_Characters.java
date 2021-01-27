package com.algorithm.LeetCode.medium;

public class Solution_3_Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args) {
        String s = "abcabcbb";
        int len = lengthOfLongestSubstring(s);
        System.out.println(len);
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder(String.valueOf(s.charAt(i)));
            for (int j = i + 1; j < s.length(); j++) {
                if (sb.toString().contains(String.valueOf(s.charAt(j))))
                    break;
                sb.append(s.charAt(j));
            }
            max = Math.max(max, sb.length());
        }
        return max;
    }
}
