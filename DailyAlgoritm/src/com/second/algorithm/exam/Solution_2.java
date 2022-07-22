package com.second.algorithm.exam;

import java.util.HashMap;
import java.util.Map;

public class Solution_2 {
    public static void main(String[] args) {
//        String[] want = {"banana", "apple", "rice", "pork", "pot"};
//        int[] number = {3, 2, 2, 2, 1};
//        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};


        String[] want = {"apple"};
        int[] number = {10};
        String[] discount = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};
        int rst = solution(want, number, discount);
        System.out.println(rst);
    }


    private static int solution(String[] want, int[] number, String[] discount) {
        int count = 0;

        // 정현이가 살 물품 개수
        Map<String, Integer> buy = new HashMap<>();
        for (int index = 0; index < want.length; index++) {
            buy.put(want[index], number[index]);
        }

        for (int day = 0; day <= discount.length - 10; day++) {
            // 10 일간 살 수 있는 재고
            Map<String, Integer> stock = new HashMap<>();
            for (int index = day; index < day + 10; index++) {
                String item = discount[index];
                if (stock.containsKey(item))
                    stock.replace(item, stock.get(item) + 1);
                else
                    stock.put(item, 1);
            }

            // 재고와 비교
            boolean register = true;
            for (String item : want) {
                // 재고가 없는 경우 회원가입 안함 or 10일간에 상품에 포함안되는 경우
                if (!stock.containsKey(item) || stock.get(item) < buy.get(item)) {
                    register = false;
                    break;
                }
            }

            // 회원가입 하는경우
            if (register) count++;
        }

        return count;
    }
}
