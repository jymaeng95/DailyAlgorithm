package com.algorithm.Programmers.SK;

import java.util.PriorityQueue;

public class Solution_1 {
    public static void main(String[] args) {
        int money = 1999;
        int[] costs = {2, 11, 20, 100, 200, 600};

        // 2308
        int rst = solution(money, costs);
        System.out.println("rst = " + rst);

    }
    static class Money implements Comparable<Money>{
        private int money;
        private int cost;

        public Money(int money, int cost) {
            this.money = money;
            this.cost = cost;
        }

        public int getMoney() {
            return money;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Money o) {
            if((double) this.cost / this.money == (double) o.cost / o.money) {
                return Integer.compare(o.money, this.money);
            }
            return Double.compare((double) this.cost/this.money, (double)o.cost / o.money);
        }
    }
    private static int solution(int money, int[] costs) {
        int[] basicMoney = {1,5,10,50,100,500};
        PriorityQueue<Money> pq = new PriorityQueue<>();
        for(int count = 0; count < 6; count++) {
            pq.add(new Money(basicMoney[count], costs[count]));
        }
        return getMinCost(money, pq);
    }

    private static int getMinCost(int money, PriorityQueue<Money> pq) {
        int cost = 0;

        while(!pq.isEmpty()) {
            Money basicMoney = pq.poll();
            int basic = basicMoney.getMoney();
            int costs = basicMoney.getCost();
            int count = money / basic;
            if(count > 0) {
                cost += costs * count;
                money -= count * basic;
            }
        }

        return cost;
    }
}
