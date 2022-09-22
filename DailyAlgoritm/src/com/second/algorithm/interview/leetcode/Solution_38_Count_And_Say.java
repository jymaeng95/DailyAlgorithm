package com.second.algorithm.interview.leetcode;

public class Solution_38_Count_And_Say {
    public static void main(String[] args) {
        int n = 5;
        String rst = countAndSay(30);
        System.out.println("rst = " + rst);
    }
    private static String countAndSay(int n) {
        if(n == 1) return "1";

        String answer = "1";
        for(int loop = 2; loop <= n; loop++) {
            StringBuilder builder = new StringBuilder();
            int count = 1;
            for(int index = 0; index < answer.length() - 1; index++) {
                // 다음 문자와 다른경우
                if(answer.charAt(index) != answer.charAt(index + 1)) {
                    builder.append(count).append(answer.charAt(index));
                    count = 1;
                } else {
                    count++;
                }
            }

            // 마지막 문자 붙히기
            builder.append(count).append(answer.charAt(answer.length() - 1));
            answer = builder.toString();
        }

        return answer;
    }
}
