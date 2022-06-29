package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class Solution_12936_줄_서는_방법 {
    public static void main(String[] args) {
        int n = 4;
        int k = 12;

        int[] rst = solution(n, k);
        for (int i : rst) {
            System.out.print(i+" ");
        }
    }


    private static int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> people = new ArrayList<>();
        long numberCase = 1;    // 경우의 수
        for (int number = 1; number <= n; number++) {
            people.add(number);
            numberCase *= number;
        }

        int index = 0;
        k--;
        while(n > 0) {
            numberCase /= n;  // n-1의 경우의 수

            /**
             * 규칙
             * 1. k / numberCase -> 시작 하는 사람의 인덱스를 알 수 있다.
             * 2. k %= numberCase -> 남은 사람의 인덱스를 구하기 위해 갱신
             * 3. k-- : people 배열의 index는 0 부터 시작, k는 1부터 시작
             */
            int startIndex = (int)(k / numberCase);
            answer[index] = people.get(startIndex);
            people.remove(startIndex);

            k %= numberCase;

            n--;
            index++;
        }
        return answer;
    }
}
