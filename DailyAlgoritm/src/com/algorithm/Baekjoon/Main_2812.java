package com.algorithm.Baekjoon;


import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

//1876591234   91234
public class Main_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int delCount = Integer.parseInt(st.nextToken());
        int size = N - delCount;
        int count = 0;
        String[] num = br.readLine().split("");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && count < delCount && Integer.parseInt(stack.peek()) < Integer.parseInt(num[i])) {
                stack.pop();
                count++;
            }
            stack.push(num[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<size;i++){
            sb.append(stack.get(i));
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}