package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16953_A_B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int rst = changeAtoB(A, B);
        System.out.println(rst);

        br.close();
    }

    private static int changeAtoB(int a, int b) {
        /**
         * 매 순간 최적의 경우를 구하기 위해 b로 시작
         * 가장 오른쪽이 1인 경우 1을 제거하면 자릿수를 하나 줄일 수 있으므로 제거
         * 그 외의 경우 2로 나뉘어 떨어지면 2로 나눠준다. 중간에 2로 나눠떨어지지 않으면 만들 수 없으므로 -1 리턴
         * b가 a와 같거나 작아지면 비교 후 결과 리턴
         */
        int count = 0;
        StringBuilder sb = new StringBuilder(String.valueOf(b));
        while(Integer.parseInt(sb.toString()) > a) {
            // 1. 가장 오른쪽이 1인 경우 1을 제거
            if(sb.charAt(sb.length()-1) == '1') {
                sb.deleteCharAt(sb.length() - 1);
                count++;
            }
            else {
                // 2로 나눠 떨어지지 않으면 -1 return
                if(Integer.parseInt(sb.toString()) % 2 != 0) return -1;
                int now = Integer.parseInt(sb.toString());
                sb = new StringBuilder(String.valueOf(now / 2));
                count++;
            }
        }

        return Integer.parseInt(sb.toString()) != a ? -1 : count+1;
    }
}
