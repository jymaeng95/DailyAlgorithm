package com.algorithm.Programmers.Lv2;

public class Challenge_7928 {
    public static void main(String[] args) {
        long[] numbers = {10000000000000L, 7};
        long[] solution = solution(numbers);
        for (long x : solution) System.out.println(x);
    }

    private static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int loop = 0;
        for (long num : numbers) {
            long target = num + 1;
            while (true) {
                long rst = target ^ num;
                int count = Long.bitCount(rst);
                if(count > 0 && count < 3) {
                    answer[loop] = target;
                    break;
                }
                target++;
            }
            loop++;
        }
        return answer;
    }

}
