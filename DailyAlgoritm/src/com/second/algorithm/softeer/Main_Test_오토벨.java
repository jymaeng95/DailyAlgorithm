package com.second.algorithm.softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Test_오토벨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Car[] prices = new Car[N];
        for (int index = 0; index < N; index++) {
            prices[index] = new Car(index + 1, Integer.parseInt(st.nextToken()));
        }

        // 중고차 번호 , 매물 가격 출력
        long[] rst = getMiddleCarInformation(N, prices);
        System.out.println(rst[0] + " " + rst[1]);

        br.close();
    }

    private static long[] getMiddleCarInformation(int n, Car[] prices) {
        // 가격 순 정렬 (가격 동일 시 인덱스 순 정렬)
        Arrays.sort(prices);

        // 몇번째로 비싼 차인지 순서 정하기
        int order = n / 2;
        if (n % 2 != 0) {
            order = (n + 1) / 2;
        }

        // 비싼 순서대로 몇번째인지 판단 = mid
        int mid = prices.length - order;

        // 중간 가격
        long midPrice = prices[mid].getPrice();

        // 정렬된 상태이므로 같은 가격의 끝을 찾기 = 제약조건 100만이므로 O(n) 시간 초과 안됨
        while (mid < prices.length && prices[mid].getPrice() == midPrice) {
            mid++;
        }

        // 같은 값인경우에 while문 타기 때문에++가 한번 더 된 상태이기 때문에 -1을 해줘야한다.
        return new long[]{prices[mid - 1].getIndex(), prices[mid - 1].getPrice()};
    }

    static class Car implements Comparable<Car> {
        private int index;
        private long price;

        public Car(int index, int price) {
            this.index = index;
            this.price = price;
        }

        public long getPrice() {
            return price;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Car o) {
            if (this.price == o.price)
                return Integer.compare(this.index, o.index);
            return Long.compare(this.price, o.price);
        }
    }
}
