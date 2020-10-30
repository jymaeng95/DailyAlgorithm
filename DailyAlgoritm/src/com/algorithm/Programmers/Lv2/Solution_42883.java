package com.algorithm.Programmers.Lv2;
// 프로그래머스 LV2(큰 수 만들기)

import java.io.*;

public class Solution_42883 {
    /*
    입력 : 문자열(숫자)number, k(제거할 개수)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String number = br.readLine().trim();
        int k = Integer.parseInt(br.readLine().trim());
        String answer = solution(number, k);
        bw.write(answer);
        br.close();
        bw.close();
    }

    public static String solution(String number, int k) {
        String answer = "";
        String[] num = number.split("");
        int size = num.length - k;
        int max = Integer.parseInt(num[0]);
        int i = 0;
        while (true) {
            for (int j = i; j <= num.length - size; j++) {
                int getNum = Integer.parseInt(num[j]);
                if(getNum == 9 ){
                    max = getNum;
                    i = j;
                    break;
                }
                if (getNum > max) {
                    max = getNum;
                    i = j;
                }
            }
            size--;
            answer += max;
            if (size == 0)
                break;
            max = Integer.parseInt(num[i + 1]);
            i += 1;
        }

        return answer;
    }
}
