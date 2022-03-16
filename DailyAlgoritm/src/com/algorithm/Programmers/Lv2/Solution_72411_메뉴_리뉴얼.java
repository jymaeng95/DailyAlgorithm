package com.algorithm.Programmers.Lv2;

import java.util.*;

public class Solution_72411_메뉴_리뉴얼 {
    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] courses = {2, 3, 4};
        String[] rst = solution(orders, courses);
        for(String str :  rst) {
            System.out.println("str = " + str);
        }
    }

    private static List<String> orderList;
    private static Set<String> menu;
    private static int maxCount;

    private static String[] solution(String[] orders, int[] courses) {
        orderList = Arrays.asList(orders);
        List<String> menuList = new ArrayList<>();
        for (int course : courses) {
            menu = new HashSet<>();     // 메뉴리스트별 동일한 조합이 나올수 있기 때문에 Set
            maxCount = 0;
            for (String order : orderList) {
                selectMenu(0, order, course, new StringBuilder());
            }
            menuList.addAll(menu); // 해당 코스에서 만들어진 모든 메뉴를 리스트에 저장
        }
        Collections.sort(menuList);
        return menuList.toArray(String[]::new);
    }

    private static void selectMenu(int start, String order, int course, StringBuilder sb) {
        if (sb.length() == course) {
            int count = 0;      //현재 해당 메뉴 포함한 개수 변수
            char[] charArray = sb.toString().toCharArray();
            Arrays.sort(charArray);     // WX == XW 이런 식으로 알파벳이 각각 동일한 메뉴이므로 동일한 메뉴
            sb = new StringBuilder();
            for(char c : charArray) sb.append(c);

            // 메뉴에 포함되어 있는지 확인
            for (String orders : orderList) {
                boolean isContain = true;
                for (char c : charArray) {
                    // 하나라도 메뉴에 생성한 메뉴가 포함되지 않으면 false
                    if (!orders.contains(String.valueOf(c))) {
                        isContain = false;
                        break;
                    }
                }
                if (isContain) count++; // 모두 포함되면
            }

            // 메뉴 리스트에 포함된 메뉴가 두개이상이어야 하고 최신에 만든 메뉴가 이전 메뉴 최대 개수보다 더 큰경우 메뉴 리스트 갱신
            if (count > 1 && count > maxCount) {
                menu.clear();
                menu.add(sb.toString());
                maxCount = count;
            }
            // 메뉴 리스트에 든 개수와 동일하면 메뉴만 넣어주기
            else if (count > 1 && count == maxCount) {
                menu.add(sb.toString());
            }
            return;
        }

        // 메뉴 조합
        for (int index = start; index < order.length(); index++) {
            sb.append(order.charAt(index));
            selectMenu(index + 1, order, course, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
