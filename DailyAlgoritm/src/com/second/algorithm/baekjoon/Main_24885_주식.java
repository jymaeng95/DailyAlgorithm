package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_24885_주식 {
    private static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] prices = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int day = 0; day < N; day++) {
            prices[day] = Integer.parseInt(st.nextToken());
        }

        long rst = getMaxProfit(prices);
        System.out.println(rst);

        br.close();
    }

    private static long maxProfit;

    private static long getMaxProfit(int[] prices) {
        maxProfit = 0;

        // 현재 금액, 주식 수, 날짜, 대출금
        stockMarket(prices, M, 0, 0, 0);
        return maxProfit;
    }

    private static void stockMarket(int[] prices, long m, long stockCount, int day, long loan) {
        if (day == N) return;

        // 돌아올 때는 대출금 상환 안해도 된다.
        maxProfit = Math.max(maxProfit, m + stockCount * prices[day]);

        // 그냥 지나가는 경우
        stockMarket(prices, m, stockCount, day + 1, loan);

        // 상환금 남아있는 경우
        if (loan > 0) {
            // 매도 후 상환
            long  curPrice = m + stockCount * prices[day] - loan;

            // 한개의 주식이라도 살 수 있는 경우 대출 가능
            if (curPrice / prices[day] > 0) {
                long curLoan = curPrice * K;
                long curStockCount = (curPrice + curLoan) / prices[day];
                long remainPrice = (curPrice + curLoan) % prices[day];
                stockMarket(prices, remainPrice, curStockCount, day + 1, curLoan);
            }

            // 매도 후 상환
            stockMarket(prices, curPrice, 0, day + 1, 0);
        } else {
            // 살수 있는 경우에만 대출 후 매수
            long curPrice = m + m * K;
            if (curPrice / prices[day] > 0) {
                long curStockCount = curPrice / prices[day];
                long remainPrice = curPrice % prices[day];
                stockMarket(prices, remainPrice, curStockCount, day + 1, m * K);
            }
        }
    }
}
