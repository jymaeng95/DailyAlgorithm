package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1701_Cubeditor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int rst = findPatternLength(str);
        System.out.println(rst);
        br.close();
    }

    private static int findPatternLength(String str) {
        int max = 0;
        // KMP알고리즘 -> 패턴에 대해 길이 1부터 패턴 길이 까지 접두사, 접미사 동일한 길이 체크
        // str 길이를 앞부터 줄여가면서 패턴에 대해 체크하면 모든 패턴 두번 이상 나오는 것에 대해 체크 가능
        for (int index = 0; index < str.length(); index++) {
            String pattern = str.substring(index);
            max = Math.max(max, makeTable(pattern));
        }
        return max;
    }

    private static int makeTable(String pattern) {
        int max = 0;
        int[] table = new int[pattern.length()];
        char[] patternArray = pattern.toCharArray();

        int patternIndex = 0;
        for (int index = 1; index < pattern.length(); index++) {
            while(patternIndex > 0 && patternArray[patternIndex] != patternArray[index]) {
                patternIndex = table[patternIndex - 1];
            }

            if(patternArray[patternIndex] == patternArray[index]) {
                patternIndex += 1;
                table[index] = patternIndex;

                // 현재 패턴에서 접두사 접미사가 겹치는 길이 구하기
                max = Math.max(max, table[index]);
            }
        }

        return max;
    }
}
