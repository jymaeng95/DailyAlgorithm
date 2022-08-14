package com.second.algorithm.exam.challenge.third;

public class Solution_1 {
    public static void main(String[] args) {
        int a =3;
        int b = 1;
        int n = 20;

        int rst = solution(a, b, n);
        System.out.println("rst = " + rst);
    }

    private static int solution(int a, int b, int n) {
        int count = 0;
        int remain = 0;

        // 식대로 일반화
        while(n >= a) {
            remain = n % a;
            int receive = (n / a) * b;

            n = receive + remain;
            count += receive;
        }
        return count;
    }
}
