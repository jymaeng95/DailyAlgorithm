package com.algorithm.LeetCode.greedy;

public class Solution_122_Best_Time_To_Buy_And_Sell_Stock_II {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        int rst = maxProfit(prices);
        System.out.println(rst);
    }

    private static int maxProfit(int[] prices) {
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            int dailyProfit = prices[i] - prices[i-1];
            if(dailyProfit > 0)
                profit += dailyProfit;
        }
        return profit;
    }
}
