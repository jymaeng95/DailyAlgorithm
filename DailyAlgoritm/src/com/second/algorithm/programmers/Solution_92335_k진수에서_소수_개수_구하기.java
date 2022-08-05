package com.second.algorithm.programmers;

public class Solution_92335_k진수에서_소수_개수_구하기 {
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;

        int rst = solution(n, k);
        System.out.println("rst = " + rst);
    }

    private static int solution(int n, int k) {
        String convertN = convert(n, k);
        String[] numbers = convertN.split("0");

        // 에라토스테네스의 체
        int count = 0;
        for (String number : numbers) {
            if (number.length() > 0 && isPrime(number)) count++;
        }
        return count;
    }

    private static boolean isPrime(String number) {
        long target = Long.parseLong(number);
        if (target < 2) return false;

        for (long n = 2; n * n <= target; n++) {
            if (target % n == 0) return false;
        }
        return true;
    }

    private static String convert(int n, int k) {
        StringBuilder convert = new StringBuilder();
        while (n >= k) {
            convert.append(n % k);
            n /= k;
        }
        return convert.append(n).reverse().toString();
    }
}
