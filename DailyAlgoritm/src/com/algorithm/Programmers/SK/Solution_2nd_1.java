package com.algorithm.Programmers.SK;

import java.util.*;

public class Solution_2nd_1 {
    public static void main(String[] args) {
//        String[] goods = {"pencil","cilicon","contrabase","picturelist"};
//        String[] goods = {"abcdeabcd","cdabe","abce","bcdeab"};
        String[] goods = {"abcdeabcdeabcdeabcde", "abcdefabcdefabcdefab"};

        String[] rst = solution(goods);
        for (String s : rst) {
            System.out.println("s = " + s);
        }
//        "en nc pe","ico ili lic","a b","u"
//        "abcd eabc","be da","ce","None"
    }

    private static boolean isUnique;            //고유 문자열 찾았는지 확인
    private static Set<String> uniqueSet;       //중복된 동일 길이 고유 문자열 제거 위해 Set사용

    private static String[] solution(String[] goods) {
        String[] rst = new String[goods.length];

        for (int index = 0; index < goods.length; index++) {
            String good = goods[index];
            isUnique = false;
            uniqueSet = new HashSet<>();

            for (int length = 1; length < good.length(); length++) {
                // 길이에 따른 굿즈 연속된 고유 문자열 만들기
                makeUniqueString(length, good, goods);
                if (isUnique) break; //유니크 문자열 찾은경우 유니크 문자열 길이보다 긴 유니크 문자열 찾을 필요 없음.
            }

            // 유니크 문자열 없는 경우 None처리
            if (uniqueSet.size() < 1) {
                rst[index] = "None";
                continue;
            }

            // 유니크 문자열 정렬
            List<String> uniqueList = new ArrayList<>(uniqueSet);
            Collections.sort(uniqueList);

            // 하나의 문자열로 만들기
            StringBuilder rstBuilder = new StringBuilder();
            for (String unique : uniqueList) {
                rstBuilder.append(unique).append(" ");
            }
            rst[index] = rstBuilder.substring(0, rstBuilder.length() - 1);  //마지막 공백 제거

        }
        return rst;
    }

    private static void makeUniqueString(int length, String good, String[] goods) {
        for (int loop = 0; loop <= good.length() - length; loop++) {
            StringBuilder unique = new StringBuilder();
            unique.append(good.substring(loop, loop + length));       // 현재 인덱스부터 길이만큼 부분 문자열 만들기

            if (validUniqueString(unique, goods, good)) isUnique = true; // 고유 문자열 찾았을 경우 True
        }

    }

    private static boolean validUniqueString(StringBuilder unique, String[] goods, String good) {
        // 고유 문자열 판단
        int count = 0;
        for (String oneGood : goods) {
            if (oneGood.contains(unique.toString())) count++;
        }

        if (count > 1) return false;

        // 고유 문자열인 경우 Set에 저장
        uniqueSet.add(unique.toString());
        return true;
    }
}
