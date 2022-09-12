package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

public class Solution_1835_단체사진_찍기 {
    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        int rst = solution(n, data);
        System.out.println("rst = " + rst);
    }

    private static List<String> orders;
    private static final String[] FRIENDS = {"A", "C", "F", "J", "M", "N", "R", "T"};
    private static int solution(int n, String[] data) {
        boolean[] ordered = new boolean[8];
        StringBuilder order = new StringBuilder();
        orders = new ArrayList<>();

        // A, C, F, J, M, N, R, T에 대한 조합 구하기
        lineUpFriends(0, ordered, order);

        // Data를 이용한 쿼리 확인
        int count = 0;
        for(String numberCase : orders) {
            if (judgeOrder(numberCase, data)) count++;
        }

        return count;
    }

    private static void lineUpFriends(int count, boolean[] ordered, StringBuilder order) {
        // 8명 모두 배치된 경우 List에 넣기
        if (count == 8) {
            orders.add(order.toString());
            return;
        }

        // 백트래킹
        for(int index = 0; index < 8; index++) {
            if(!ordered[index]) {
                ordered[index] = true;
                order.append(FRIENDS[index]);

                lineUpFriends(count + 1, ordered, order);

                order.deleteCharAt(order.length() - 1);
                ordered[index] = false;
            }
        }
    }

    private static boolean judgeOrder(String order, String[] data) {
        for(String query : data) {
            int range = Math.abs(order.indexOf(query.charAt(0)) - order.indexOf(query.charAt(2))) - 1;
            char op = query.charAt(3);
            int queryRange = query.charAt(4) - '0';

            // queryRange가 range 보다 크기를 원함
            if(op == '<') {
                if(range >= queryRange) return false;
            }
            // queryRange가 range 보다 작기를 원함
            else if(op == '>') {
                if(range <= queryRange) return false;
            }
            else {
                if(queryRange != range) return false;
            }
        }
        return true;
    }
}
