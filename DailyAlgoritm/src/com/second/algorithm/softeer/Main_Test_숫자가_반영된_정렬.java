package com.second.algorithm.softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_Test_숫자가_반영된_정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        String rst = compare(first, second);
        System.out.println(rst);

        br.close();
    }

    private static String compare(String first, String second) {
        int length = Math.min(first.length(), second.length());
        for (int index = 0; index < length; index++) {
            // 현재 인덱스가 둘다 숫자인 경우
            if (Character.isDigit(first.charAt(index)) && Character.isDigit(second.charAt(index))) {
                int firstIndex = index;
                int secondIndex = index;

                // 숫자끝까지 파싱
                while(firstIndex < first.length() && Character.isDigit(first.charAt(firstIndex))) {
                    firstIndex++;
                }

                // 숫자 끝까지 파싱
                while (secondIndex < second.length() && Character.isDigit(second.charAt(secondIndex))) {
                    secondIndex++;
                }

                // 숫자가 10만자리까지 이론상 가능 Long 범위 벗어남
                BigInteger firstNumber = new BigInteger(first.substring(index, firstIndex));
                BigInteger secondNumber = new BigInteger(second.substring(index, secondIndex));

                int compare = firstNumber.compareTo(secondNumber);

                // firstNumber가 큰 경우
                if (compare > 0) return ">";
                else if(compare < 0) return "<";

                // 동일한 경우는 firstIndex or secondIndex까지 비교했기 때문에 index를 해당 위치까지 옮겨준다.
                index = firstIndex - 1;
            }
            // 둘다 숫자가 아닌경우는 아스키코드를 비교
            else {
                // 단순 문자를 비교하면된다.
                if(first.charAt(index) > second.charAt(index)) return ">";
                else if(first.charAt(index) < second.charAt(index)) return "<";
            }
        }

        // 더 짧은 문자열의 마지막 인덱스까지 비교했는데 안끝났다면
        // 길이가 짧은문자열 더 앞선다.
        if(first.length() > second.length()) return ">";
        else if(first.length() < second.length()) return "<";

        // 길이마저 동일한 경우는 동일 값
        return "=";
    }
}
