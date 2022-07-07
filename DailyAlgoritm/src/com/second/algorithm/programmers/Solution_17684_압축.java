package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_17684_압축 {
    public static void main(String[] args) {
        String msg = "KAKAO";

        int[] rst = solution(msg);
        for (int x : rst) {
            System.out.println("x = " + x);
        }
    }

    private static int[] solution(String msg) {
        /**
         * 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
         * 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
         * 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
         * 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
         * 5. 단계 2로 돌아간다.
         */

        // 사전 인덱스 리스트
        List<Integer> indexes = new ArrayList<>();

        // 1. 길이 1 단어 사전 초기화
        Map<String, Integer> dictionary = initDictionary();

        int index = 1;
        while (index <= msg.length()) {
            // 저장된 단어
            StringBuilder word = new StringBuilder();

            // 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w 찾기
            int start = index-1;   //이전에 붙힌 인덱스 부터 시작해야하므로 -1을 해준다.
            boolean last = false;

            // 초기화 된 상태이거나, 단어가 사전에 포함되어있고, 마지막이 아닌 경우에 문자를 증가시킨다.
            while((word.length() == 0 || dictionary.containsKey(word.toString())) && !last) {
                // 마지막 단어인 경우 체크
                if(start == msg.length()) last = true;
                else word.append(msg.charAt(start++));
            }

            // 마지막 단어인 경우 사전에 있는 단어일 수도 있으므로 체크
            if(last) {
                indexes.add(dictionary.get(word.toString()));
                break;
            }

            // 3. 색인 번호 저장
            // 저장되지 않은 단어인 경우에 반복문을 탈출하므로 현재 단어의 마지막 문자를 빼주면 사전에 있는 단어이다.
            String savedWord = word.substring(0, word.length() - 1);
            indexes.add(dictionary.get(savedWord.toString()));

            // 4. w + c에 해당하는 단어 사전 등록
            // 저장되지 않은 문자를 저장해준다.
            dictionary.put(word.toString(), dictionary.size() + 1);

            index = start;
        }

        return indexes.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Map<String, Integer> initDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int index = 1; index <= 26; index++) {
            dictionary.put(String.valueOf(Character.toChars('A' + index - 1)), index);
        }

        return dictionary;
    }
}
