package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_15886_내_선물을_받아줘_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String road = br.readLine();

        int rst = putPresent(N, road);
        System.out.println(rst);
        br.close();
    }

    private static int putPresent(int n, String road) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (char direction : road.toCharArray()) {
            if (direction == 'W' && !stack.isEmpty() && stack.peek() == 'E') {
                count++;
            }
            stack.push(direction);
        }

        return count;
    }
}
