package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1339_단어_수학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for(int loop =0; loop < N; loop++) {
            words[loop] = br.readLine();
        }

        int rst = getSum(words);
        System.out.println(rst);
        br.close();
    }

    private static int getSum(String[] words) {
        int[] alphabet = new int[26];

        for(String word : words) {
            int digit = word.length() - 1;
            // 알파벳 A 가 0번 인덱스, 먼저 나온 수부터 자릿수 -1씩 Loop
            for(char letter : word.toCharArray()) {
                int index = letter - 'A';
                alphabet[index] += (int) Math.pow(10,digit);
                digit--;
            }
        }

        // 정렬
        Arrays.sort(alphabet);
        int index = alphabet.length-1;
        int maxNumber = 9;
        int sum = 0;

        // 자릿수의 값이 가장 높은 순서부터 차례로 숫자 부여
        while(maxNumber >= 0 &&  alphabet[index] > 0) {
            sum += alphabet[index] * maxNumber;
            maxNumber--;
            index--;
        }
        return sum;
    }
}
