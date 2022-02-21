package com.algorithm.Programmers.Lv3;

import java.util.*;

public class Solution_77486_다단계_칫솔_판매 {
    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] solution = solution(enroll, referral, seller, amount);
        for(int x : solution) System.out.println("x = " + x);
    }

    private static List<List<String>> tree;
    private static Map<String, Integer> sellerMap;
    private static Map<String, Integer> profit;
    private static boolean[] visited;
    private static List<List<String>> path;
    private static Stack<String> stack;
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        sellerMap = new HashMap<>();
        profit = new HashMap<>();
        tree = new ArrayList<>();
        path = new ArrayList<>();
        // 판매자 인덱스 매핑
        for(int loop = 0; loop <= enroll.length; loop++) {
            tree.add(new ArrayList<>());
            path.add(new ArrayList<>());
            if(loop == 0) {
                sellerMap.put("center", loop);
                profit.put("center", 0);
            }
            else {
                sellerMap.put(enroll[loop-1], loop);
                profit.put(enroll[loop-1], 0);
            }
        }

        // 트리 생성
        for(int loop =0; loop < enroll.length; loop++) {
            if(referral[loop].equals("-")) {
                tree.get(sellerMap.get("center")).add(enroll[loop]);
            }
            else {
                tree.get(sellerMap.get(referral[loop])).add(enroll[loop]);
            }
        }

        // 패스 설정
        path.get(sellerMap.get("center")).add("center");
        for (String target : enroll) {
            stack = new Stack<>();
            visited = new boolean[enroll.length + 1];
            buildPath("center", target);
        }

        // 금액 계산
        for(int loop = 0; loop < seller.length; loop++) {
            devideProfit(seller[loop], amount[loop]);
        }

        int idx = 0;
        for(String name : enroll) {
            if(!name.equals("center")) {
                answer[idx++] = profit.get(name);
            }
        }
        return answer;
    }

    private static void devideProfit(String seller, int amount) {
        int sellAmount = amount * 100;
        for(String name : path.get(sellerMap.get(seller))) {
            int sellerAmount = sellAmount - (sellAmount / 10);
            profit.replace(name, profit.get(name) + sellerAmount);
            sellAmount /= 10;
            if(sellAmount == 0) break;
        }
    }

    private static boolean buildPath(String root, String target) {
        stack.push(root);
        visited[sellerMap.get(root)] = true;
        if(root.equals(target)) {
            while(!stack.isEmpty()) {
                path.get(sellerMap.get(target)).add(stack.pop());
            }
            return true;
        }
        for(String name : tree.get(sellerMap.get(root))) {
            if(!visited[sellerMap.get(name)]) {
                if(buildPath(name, target)) return true;
                stack.pop();
            }
        }
        return false;
    }
}
