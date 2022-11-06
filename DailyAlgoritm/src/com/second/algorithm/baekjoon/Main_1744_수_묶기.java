package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_1744_수_묶기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        for (int index = 0; index < N; index++) {
            int number = Integer.parseInt(br.readLine());
            if (number <= 0) negative.add(number);
            else positive.add(number);
        }

        int sum = getMaxSum(positive, true) + getMaxSum(negative, false);
        System.out.println(sum);

        br.close();
    }

    private static int getMaxSum(List<Integer> numbers, boolean positive) {
        if (numbers.isEmpty()) return 0;
        if (numbers.size() == 1) return numbers.get(0);

        if (positive) numbers.sort(Collections.reverseOrder());
        else Collections.sort(numbers);

        int maxSum = 0;
        for (int index = 0; index < numbers.size(); index++) {
            if (index == numbers.size() - 1) {
                maxSum += numbers.get(index);
            } else {
                int multiply = numbers.get(index) * numbers.get(index + 1);
                int sum = numbers.get(index) + numbers.get(index + 1);

                if (multiply > sum) {
                    maxSum += multiply;
                    index++;
                } else {
                    maxSum += numbers.get(index);
                }
            }
        }

        return maxSum;
    }
}
