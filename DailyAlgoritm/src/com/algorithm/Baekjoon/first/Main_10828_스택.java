package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_10828_스택 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int loop = 0; loop < N; loop++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if(st.hasMoreTokens()) stack.push(Integer.parseInt(st.nextToken()));
            else {
                if(op.equals("pop")) {
                    if(!stack.isEmpty()) System.out.println(stack.pop());
                    else System.out.println(-1);
                }
                else if(op.equals("size")) System.out.println(stack.size());
                else if(op.equals("empty")) {
                    if(!stack.isEmpty()) System.out.println(0);
                    else System.out.println(1);
                }
                else {
                    if(!stack.isEmpty()) System.out.println(stack.peek());
                    else System.out.println(-1);
                }
            }
        }

        br.close();
    }
}
