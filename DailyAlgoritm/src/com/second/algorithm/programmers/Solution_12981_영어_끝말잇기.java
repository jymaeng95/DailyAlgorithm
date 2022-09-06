package com.second.algorithm.programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution_12981_영어_끝말잇기 {
    public static void main(String[] args) {
        int n = 5;
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};

        int[] rst = solution(n, words);
        Arrays.stream(rst).forEach(System.out::println);
    }

    private static int[] solution(int n, String[] words) {
        // 1. 마지막 단어의 문자로 시작하는 경우 아니면 탈락
        // 2. 이전에 등장한 단어 사용시 탈락
        // 3. 한글자 단어 사용시 탈락

        boolean finish = true;
        Set<String> set = new HashSet<>();

        // 끝말잇기 시작
        int order = 1;
        String target = words[0];
        set.add(target);

        while (order < words.length) {
            // 탈락 조건
            if(words[order].length() == 1
                    ||target.charAt(target.length() - 1) != words[order].charAt(0)
                    || set.contains(words[order])) {
                finish = false;
                break;
            }

            // 성공햇다면 타겟 단어 변경
            target = words[order++];
            set.add(target);
        }

        if (finish) return new int[]{0,0};

        // order 보고 순서 정하기 (몫은 회차, 나머지는 몇번째 사람)
        int round = order / n + 1;
        int user = order % n + 1;
        return new int[]{user, round};
    }
}
