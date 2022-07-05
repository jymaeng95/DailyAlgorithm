package com.algorithm.Baekjoon.first;


import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int delCount = Integer.parseInt(st.nextToken());
        int size = N - delCount;

        //원소 삭제 개수 카운팅
        int count = 0;
        String[] num = br.readLine().split("");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            //현재 접근한 값이 스택안에 있는 값보다 크면 스택 안에 있는 값을 삭제 후 삭제카운팅 증가
            while (!stack.isEmpty() && count < delCount && Integer.parseInt(stack.peek()) < Integer.parseInt(num[i])) {
                stack.pop();
                count++;
            }
            //스택에 현재 접근 값 넣어주기
            stack.push(num[i]);
        }

        StringBuilder sb = new StringBuilder();

        //삭제 개수 카운팅이 입력 삭제 개수만큼 못지우고 나오는 경우가 있으므로 원래길이에서 삭제개수 뺀 결과 사이즈만큼 스택에서 출력
        //6 4 199244의 경우 스택엔 99244가 들어가고 삭제카운팅이 입력된 개수만큼 지우지 않기때문
        for(int i=0;i<size;i++){
            sb.append(stack.get(i));
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}