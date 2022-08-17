package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_84512_모음사전 {
    public static void main(String[] args) {
        String word = "A";
        int rst = solution(word);
        System.out.println("rst = " + rst);
    }

    private static List<String> dictionary;
    private static final String[] VOWEL = {"A", "E", "I", "O", "U"};

    private static int solution(String word) {
        dictionary = new ArrayList<>();

        // 사전 생성
        makeDictionary(new StringBuilder());

        // 정렬
        Collections.sort(dictionary);

        // 해당하는 인덱스 찾기
        for (int index = 0; index < dictionary.size(); index++) {
            if(word.equals(dictionary.get(index)))
                return index;
        }

        return -1;

        // 스트림으로 바로 리턴.. 프로그래머스에서 안됌
//        return IntStream.range(0, dictionary.size()).filter(index -> dictionary.get(index).equals(word)).findFirst().getAsInt() + 1;
    }

    // 순열로 샤전 만들기
    private static void makeDictionary(StringBuilder word) {
        if (word.length() == 5) {
            return;
        }

        for (int index = 0; index < 5; index++) {
            word.append(VOWEL[index]);
            dictionary.add(word.toString());
            makeDictionary(word);
            word.deleteCharAt(word.length() - 1);
        }
    }
}
