package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SkillCheck_3 {
    public static void main(String[] args) {
        String numbers = "011";
        int rst = solution(numbers);
        System.out.println(rst);
    }

    private static int solution(String numbers) {
        set = new HashSet<>();
        StringBuilder number = new StringBuilder();
        boolean[] visited = new boolean[numbers.length()];
        dfs(0, number, numbers, visited);

        int count = 0;
        for (Integer n : set) {
            boolean[] isPrime = new boolean[n + 1];
            Arrays.fill(isPrime, true);

            for (int basic = 2; basic * basic <= n; basic++) {
                if (isPrime[basic]) {
                    for (int next = basic * basic; next <= n; next += basic) {
                        isPrime[next] = false;
                    }
                }
            }

            if (n > 1 && isPrime[n]) count++;
        }

        return count;
    }

    private static Set<Integer> set;

    private static void dfs(int depth, StringBuilder number, String numbers, boolean[] visited) {
        if (depth == numbers.length()) return;
        for (int start = 0; start < numbers.length(); start++) {
            if (!visited[start]) {
                visited[start] = true;
                number.append(numbers.charAt(start));
                set.add(Integer.parseInt(number.toString()));
                dfs(depth + 1, number, numbers, visited);
                number.deleteCharAt(number.length() - 1);
                visited[start] = false;
            }
        }
    }
}
