package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_12923_숫자_블록 {
    public static void main(String[] args) {
        int begin = 999_999_000;
        int end = 1_000_000_000;
        int[] rst = solution(begin, end);

        Arrays.stream(rst).forEach(x -> System.out.print(x + " "));
    }

    private static int[] solution(long begin, long end) {
        int from = (int) begin;
        int to = (int) end;
        int[] blocks = new int[to - from + 1];
        Arrays.fill(blocks, 1);

        for (int number = from; number <= to; number++) {
            for (int next = 2; next <= Math.sqrt(number); next++) {
                if (number % next == 0 && number / next <= 10_000_000) {
                    blocks[number - from] = number / next;
                    break;
                }
            }
        }

        if (from == 1) blocks[0] = 0;
        return blocks;
    }
}

