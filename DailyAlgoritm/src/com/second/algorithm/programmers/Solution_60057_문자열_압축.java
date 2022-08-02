package com.second.algorithm.programmers;
public class Solution_60057_문자열_압축 {
    public static void main(String[] args) {
        String s = "aabbaccc";
//        String s = "ababcdcdababcdcd";
//        String s = "abcabcdede";
//        String s = "abcabcabcabcdededededede";
//        String s = "xababcdcdababcdcd";
        int rst = solution(s);
        System.out.println("rst = " + rst);
    }

    private static int solution(String s) {
        int minLength = s.length();
        // 개수 별로 압축해보기
        for (int compact = 1; compact <= s.length(); compact++) {
            for (int index = 0; index < compact; index++) {
                StringBuilder compactString = new StringBuilder();
                compactString.append(s, 0, index);

                String prev = s.substring(index, index + compact);
                int compactIndex = index + compact;
                int compactCount = 1;
                while(compactIndex + compact <= s.length()) {
                    String next = s.substring(compactIndex, compactIndex + compact);
                    // 이전과 다음이 같은 경우 압축
                    if(prev.equals(next)) {
                        compactCount++;
                    }
                    // 아닌 경우 그대로 builder에 붙힘
                    else {
                        if(compactCount > 1) compactString.append(compactCount).append(prev);
                        else compactString.append(prev);
                        compactCount = 1;
                        prev = next;
                    }

                    compactIndex += compact;
                }
                compactString.append(s,compactIndex, s.length());

                minLength = Math.min(minLength, compactString.length());
            }
        }

        return minLength;
    }
}
