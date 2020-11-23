package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.LinkedList;

public class Solution_42885 {
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50, 30};
        int limit = 100;
        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.parallelSort(people);
        LinkedList<Integer> kg = new LinkedList<>();
        for (int pp : people)
            kg.offer(pp);
        while (kg.size() > 0) {
            int indexPeople = kg.pollLast();
            if (kg.size() > 0 && indexPeople + kg.getFirst() <= limit) {
                answer++;
                kg.removeFirst();
                continue;
            }
            answer++;
        }
        return answer;
    }
}


