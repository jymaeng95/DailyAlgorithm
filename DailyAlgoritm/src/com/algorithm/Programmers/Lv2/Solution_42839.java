package com.algorithm.Programmers.Lv2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution_42839 {
    public static Set<Integer> set;

    public static void main(String[] args) {
        System.out.println(solution("011"));
    }

    public static int solution(String numbers) {
        set = new HashSet<>();
        for (int i = 0; i < numbers.length(); i++) {
            permutation(numbers.split(""), 0, numbers.length(), i + 1);
        }
        return isPrime();
    }


    //
    public static void permutation(String[] arr, int depth, int n, int r) {
        if (depth == r) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < r; i++) {
                s.append(arr[i]);
            }
            set.add(Integer.parseInt(s.toString()));
            return;
        }
        for (int i = depth; i < n; i++) {
            swap(arr, i, depth);
            permutation(arr, depth + 1, n, r);
            swap(arr, i, depth);
        }

    }

    public static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int isPrime() {
        int count = 0;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            boolean flag = true;
            int num = it.next();
            it.remove();
            if (num == 0 || num == 1)
                continue;
            // N이 N의 제곱근보다 크지 않은 어떤 소수로도 나눠지지 않는원칙을 통해 효율성 증대
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                count++;
        }
        return count;
    }
}
