package com.algorithm.Programmers.Lv1;

import java.util.ArrayList;
import java.util.List;

public class Solution_42840 {
    public static void main(String[] args) {
        solution(new int[]{1,3,2,4,2});
    }

    public static List<Integer> solution(int[] answers) {
        int[] first = {1, 2, 3, 4, 5};
        int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] rank = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (first[i % first.length] == answers[i])
                rank[0]++;
            if (second[i % second.length] == answers[i])
                rank[1]++;
            if (third[i % third.length] == answers[i])
                rank[2]++;
        }
        int max = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for (int x : rank)
            max = Math.max(max, x);
        for(int i=0;i<rank.length;i++) {
            if(max == rank[i])
                list.add(i+1);
        }

        return list;
    }

}
