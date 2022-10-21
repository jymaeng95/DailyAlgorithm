package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_131130_혼자_놀기의_달인 {
    public static void main(String[] args) {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};

        int rst = solution(cards);
        System.out.println("rst = " + rst);
    }

    private static List<Integer> scores;
    private static List<List<Integer>> graph;
    private static int solution(int[] cards) {
        scores = new ArrayList<>();
        graph = new ArrayList<>();

        for (int box = 0; box <= cards.length; box++) {
            graph.add(new ArrayList<>());
        }

        for (int box = 0; box < cards.length; box++) {
            int start = box + 1;
            int end = cards[box];

            graph.get(start).add(end);
        }

        boolean[] check = new boolean[cards.length + 1];
        for (int box = 1; box <= cards.length; box++) {
            if(!check[box]) calculateScore(box, 0, check);
        }

        if(scores.size() <= 1) return 0;
        scores.sort(Collections.reverseOrder());

        return scores.get(0) * scores.get(1);
    }

    private static void calculateScore(int box, int score, boolean[] check) {
        if(check[box]) {
            scores.add(score);
            return;
        }

        check[box] = true;
        for (Integer nextBox : graph.get(box)) {
            calculateScore(nextBox, score + 1, check);
        }
    }
}
