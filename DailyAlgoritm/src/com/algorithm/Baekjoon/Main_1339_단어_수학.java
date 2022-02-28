package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_1339_단어_수학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();
        for(int loop =0; loop < N; loop++) {
            words.add(br.readLine());
        }

        int rst = getSum(words);
        System.out.println(rst);
        br.close();
    }

    private static int getSum(List<String> words) {
        int[] alphabet = new int[26];
        for(String word : words) {
            int digit = word.length() - 1;
            for(char letter : word.toCharArray()) {
                int index = letter - 'A';
                alphabet[index] += (int) Math.pow(10,digit);
                digit--;
            }
        }
        Arrays.sort(alphabet);
        int index = alphabet.length-1;
        int maxNumber = 9;
        int sum = 0;
        while(maxNumber >= 0 &&  alphabet[index] > 0) {
            sum += alphabet[index] * maxNumber;
            maxNumber--;
            index--;
        }
        return sum;
    }
}
