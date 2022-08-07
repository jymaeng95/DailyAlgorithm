package com.second.algorithm.programmers;

import java.util.*;

public class Solution_72441_메뉴_리뉴얼 {
    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] rst = solution(orders, course);
        Arrays.stream(rst).forEach(System.out::println);
    }

    private static String[] solution(String[] orders, int[] course) {
        // 단일 메뉴 만들기
        singleMenu = makeSingleMenu(orders);

        Map<Integer, List<String>> courseMenu = new HashMap<>();
        // 코스 개수로 코스 만들기
        for (int count : course) {
            //코스 메뉴 초기화
            courseMenu.put(count, new ArrayList<>());

            // 후보 메뉴만들기
            candidateMenu = new ArrayList<>();
            boolean[] check = new boolean[singleMenu.length];
            makeCourseMenu(0, 0, count, check, "");

            // 2명 이상 조합된 경우에만 갱신하고 넣어주기
            availableMenu(orders, courseMenu, count);
        }


        List<String> menuList = new ArrayList<>();
        for (int count : course) {
            menuList.addAll(courseMenu.get(count));
        }

        return menuList.stream().sorted().toArray(String[]::new);
    }

    private static void availableMenu(String[] orders, Map<Integer, List<String>> courseMenu, int count) {
        int maxCombinationMenu = 1; // 현재 구성 메뉴 개수에서 최대 몇명이 해당 단일 메뉴를 가지고 있는지

        // 모든 후보 메뉴에 대해
        for (String candidate : candidateMenu) {
            int consistCount = 0; // 구성 인원수

            // 모든 주문에 대해 단일 메뉴 포함 여부 판단
            for (String order : orders) {
                boolean consist = true;
                for (char menu : candidate.toCharArray()) {
                    // 단일 메뉴 하나라도 포함안했다면 인원 체크 안함
                    if (!order.contains(String.valueOf(menu))) {
                        consist = false;
                        break;
                    }
                }
                if (consist) consistCount++;
            }

            // 2명 이상이 구성하고 있고 현재 구성 인원이 최대 구성 인원 보다 많거나 같다면
            if (consistCount > 1 && consistCount >= maxCombinationMenu) {

                // 많은 경우는 메뉴 초기화
                if (consistCount > maxCombinationMenu)
                    courseMenu.get(count).clear();

                // 메뉴 넣어주기
                maxCombinationMenu = consistCount;
                courseMenu.get(count).add(candidate);
            }
        }
    }

    // 조합을 통해 모든 후보 코스메뉴 구하기
    private static void makeCourseMenu(int index, int consistCount, int count, boolean[] check, String menu) {
        if (consistCount == count) {
            candidateMenu.add(menu);
            return;
        }

        for (int start = index; start < singleMenu.length; start++) {
            if (!check[start]) {
                check[start] = true;
                makeCourseMenu(start + 1, consistCount + 1, count, check, menu + singleMenu[start]);
                check[start] = false;
            }
        }
    }

    private static String[] singleMenu;
    private static List<String> candidateMenu;

    // 단일 메ㅔ뉴 구하기
    private static String[] makeSingleMenu(String[] orders) {
        Set<Character> singleMenuSet = new HashSet<>();
        for (String order : orders) {
            for (char c : order.toCharArray()) {
                singleMenuSet.add(c);
            }
        }

        return singleMenuSet.stream().map(Object::toString).toArray(String[]::new);
    }
}
