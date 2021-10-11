package com.algorithm.LeetCode.greedy;

import java.io.*;
import java.util.Stack;

public class Solution_1221_Split_A_String_In_Balanced_String {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = "RRRLLRLRLLRLRL";
        int count = balancedStringSplit(s);

        bw.write(String.valueOf(count));
        bw.flush();
        br.close();
        bw.close();
    }
    private static int balancedStringSplit(String s) {
        int count = 0;
        Stack<String> stack = new Stack<>();

        for(String str : s.split("")) {
            if(stack.isEmpty() || str.equals(stack.peek())) {
                stack.push(str);
                continue;
            }

            stack.pop();

            if(stack.isEmpty()) {
                count++;
            }
        }

        return count;
    }
}
