package com.second.algorithm.practice;

public class KMP {
    public static void main(String[] args) {
        String pattern = "abacaaba";
        String str = "ababacabacaabacaaba";

        int[] table = makePrefixAndSuffix(pattern);
        int match = findPattern(table, pattern, str);
        System.out.println("match = " + match);
    }

    private static int findPattern(int[] table, String pattern, String str) {
        char[] strArray = str.toCharArray();
        char[] patternArray = pattern.toCharArray();
        int patternIndex = 0;
        int count = 0;
        for (int index = 0; index < str.length(); index++) {

            while(patternIndex > 0 && strArray[index] != patternArray[patternIndex]) {
                patternIndex = table[patternIndex - 1];
            }

            // 패턴이 일치하는 경우
            if (strArray[index] == patternArray[patternIndex]) {
                // 패턴 찾은 경우
                if(patternIndex == pattern.length() - 1) {
                    System.out.println(index - patternIndex + 1);
                    patternIndex = table[patternIndex];
                    count++;
                }
                else {
                    patternIndex++;
                }
            }
        }
        return count;
    }

    private static int[] makePrefixAndSuffix(String pattern) {
        int pLen = pattern.length();
        int[] table = new int[pLen];

        int index = 0;
        for(int i = 1; i < pLen; i++) {
			/* index > 0 => 문자열 매칭이 시작됨
			  	연속으로 일치하지 않으면 index 값을 돌려주어 다시 매칭 시작 */
            while(index > 0 && pattern.charAt(i) != pattern.charAt(index)) {
                index = table[index - 1];
            }

            if(pattern.charAt(i) == pattern.charAt(index)) {
                index += 1;
                table[i] = index;
            }
        }

        return table;
    }
}
