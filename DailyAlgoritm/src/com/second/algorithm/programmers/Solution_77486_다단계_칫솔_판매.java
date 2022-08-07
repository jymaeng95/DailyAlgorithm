package com.second.algorithm.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_77486_다단계_칫솔_판매 {
    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] rst = solution(enroll, referral, seller, amount);
        Arrays.stream(rst).forEach(System.out::println);
    }
    private static Map<String, String> relations;
    private static Map<String, Integer> profits;
    private static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 추천인 관계 정리
        relations = new HashMap<>();
        for (int person = 0; person < enroll.length; person++) {
            relations.put(enroll[person], referral[person]);
        }

        // 이익 정보 정리
        profits = new HashMap<>();
        for (int person = 0; person < enroll.length; person++) {
            profits.put(enroll[person], 0);
        }

        // 이익 계산
        for (int index = 0; index < seller.length; index++) {
            getProfit(seller[index], amount[index] * 100);
        }

        return Arrays.stream(enroll).map(person -> profits.get(person)).mapToInt(Integer::intValue).toArray();
    }

    private static void getProfit(String seller, int profit) {
        if(profit < 1 || !relations.containsKey(seller))
            return;

        // 금액 계산
        int referralProfit = (int) (profit * 0.1);
        int sellerProfit = profit - referralProfit;

        // 이익 저장
        profits.replace(seller, profits.get(seller) + sellerProfit);

        getProfit(relations.get(seller), referralProfit);
    }
}
