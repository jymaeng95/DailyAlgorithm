package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_5430_AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            String[] function = br.readLine().split("");
            int size = Integer.parseInt(br.readLine());
            Integer[] numbers = new Integer[size];

            // 파싱
            String input = br.readLine();

            // 사이즈 2개 이상인 경우 파싱 필요
            if (size > 1) {
                int index = input.lastIndexOf(']');
                StringTokenizer st = new StringTokenizer(input.substring(1, index), ",");

                for (int loop = 0; loop < size; loop++) {
                    numbers[loop] = Integer.parseInt(st.nextToken());
                }

                String rst = startAC(function, numbers);
                System.out.println(rst);
            }
            // 사이즈 1개인 경우 첫번째 원소만 파싱
            else if (size == 1){
                int index = input.lastIndexOf(']');
                numbers[0] = Integer.parseInt(input.substring(1, index));
                String rst = startAC(function, numbers);
                System.out.println(rst);
            }
            // 배열 사이즈가 0인 경우
            else {
                String rst = startAC(function, numbers);
                System.out.println(rst);
            }

        }


        br.close();
    }

    private static String startAC(String[] function, Integer[] numbers) {
        Deque<Integer> deque = new LinkedList<>(Arrays.asList(numbers));

        // R이 나올때마다 바뀜 -1인 경우 앞에서 poll 1인 경우 뒤에서 poll
        int reverse = -1;

        for (String func : function) {
            // R 인경우 (방향 바꾸기)
            if (func.equals("R")) reverse *= -1;
            else {
                // 덱이 빈 경우 D 함수 들어오면 error
                if (deque.isEmpty()) return "error";
                else {
                    if (reverse < 0)
                        deque.pollFirst();
                    else
                        deque.pollLast();
                }
            }
        }

        // 모든 함수를 실행한 후 리스트가 빈 경우 빈 배열 출력
        if(deque.isEmpty()) {
            return "[]";
        }

        // 덱의 원소 배열 문자열로 바꾸기
        StringBuilder rst = new StringBuilder();
        rst.append("[");
        while(!deque.isEmpty()) {
            if(reverse < 0) rst.append(deque.pollFirst());
            else rst.append(deque.pollLast());

            rst.append(",");
        }
        rst.deleteCharAt(rst.length() - 1).append("]");

        return rst.toString();
    }
}
