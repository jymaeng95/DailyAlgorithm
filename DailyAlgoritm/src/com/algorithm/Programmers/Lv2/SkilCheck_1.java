package com.algorithm.Programmers.Lv2;

import java.util.Stack;

public class SkilCheck_1 {
    public static void main(String[] args) {
        int n = 3;
        long k = 5;

        int[] rst = solution(n, k);
        for (int r : rst) {
            System.out.println(r);
        }
    }

    private static long count;
    private static Stack<Integer> stack;
    private static int[] answer;
    private static boolean end;
    private static int[] solution(int n, long k) {
        int[] people = new int[n];
        for (int person = 0; person < n; person++) {
            people[person] = person + 1;
        }

        count = 1;
        boolean[] visited = new boolean[n];
        stack = new Stack<>();
        end = false;
        answer = new int[n];

        standPerson(0, people, k, visited);
        return answer;
    }

    private static void standPerson(int start, int[] people, long k, boolean[] visited) {
        if(end) return;
        if (stack.size() == people.length) {
            if(count == k) {
                for (int index = 0; index < people.length; index++) {
                    answer[index] = stack.get(index);
                }
                end = true;
            }
            count++;
            return;
        }

        for (int index = 0; index < people.length; index++) {
            if(!visited[index]) {
                visited[index] = true;
                stack.push(people[index]);
                standPerson(index + 1, people, k, visited);
                stack.pop();
                visited[index] = false;
            }
        }
    }
}
