package com.algorithm.thisiscodingtest.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P363_Q26_카드_정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> card = new PriorityQueue<>();
        for(int i=0;i<N;i++) {
            card.offer(Integer.parseInt(br.readLine()));
        }
        int min = cardSort(N,card);
        System.out.println(min);
        br.close();
    }

    private static int cardSort(int n, PriorityQueue<Integer> card) {
        int count = 0;
        while(card.size() >= 2) {
            int cardOne = card.poll();
            int cardTwo = card.poll();
            count += cardOne + cardTwo;
            card.offer(cardOne + cardTwo);
        }

        return count;
    }
}
