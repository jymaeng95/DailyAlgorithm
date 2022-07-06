package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2992_크면서_작은_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String X = br.readLine();
        int[] number = new int[X.length()];

        for (int index = 0; index < X.length(); index++) {
            number[index] = Integer.parseInt(String.valueOf(X.charAt(index)));
        }

        int rst = getNumber(X, number);
        System.out.println(rst);
        br.close();
    }

    private static List<Integer> availableNumber;

    private static int getNumber(String x, int[] number) {
        availableNumber = new ArrayList<>();

        for (int index = 0; index < x.length(); index++) {
            boolean[] visited = new boolean[x.length()];
            visited[index] = true;
            StringBuilder sb = new StringBuilder();
            makeNumber(x,1, number, visited, sb.append(number[index]));
        }

        Collections.sort(availableNumber);

        return availableNumber.isEmpty() ? 0 : availableNumber.get(0);
    }

    private static void makeNumber(String x, int depth, int[] number, boolean[] visited, StringBuilder num) {
        if (num.length() == visited.length) {
            if(Integer.parseInt(x) < Integer.parseInt(num.toString()))
                availableNumber.add(Integer.parseInt(num.toString()));
            return;
        }

        for (int index = 0; index < number.length; index++) {
            if (!visited[index]) {
                visited[index] = true;
                makeNumber(x,depth + 1, number, visited, num.append(number[index]));
                num.deleteCharAt(depth);
                visited[index] = false;
            }
        }
    }
}
