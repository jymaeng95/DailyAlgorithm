package com.algorithm.LeetCode.greedy;

public class Solution_680_Valid_Palindrome_II {
    public static void main(String[] args) {
        String s = "abada";
        boolean rst = validPalindrome(s);
        System.out.println(rst);
    }

    private static boolean validPalindrome(String s) {
        // 앞 : i, 뒤 : s.length-i-1
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // 처음과 끝이 다른 경우
            if (s.charAt(left) != s.charAt(right)) {
                // 끝 한자리 삭제 후 확인
                if (valid(left, right - 1, s)) {
                    return true;
                }

                // 앞 한자리 삭제 후 확인
                if (valid(left + 1, right, s)) {
                    return true;
                }

                // 최대 한번만 삭제 가능하므로 이외 다른 경우 판별 X
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 펠린드롬 문자 확인
    private static boolean valid(int left, int right, String s) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
