package com.algorithm.Programmers.Lv2;

public class Solution_87390_n2_배열_자르기 {
    public static void main(String[] args) {
        int n = 4;
        int left = 7;
        int right = 14;
        int[] solution = solution(n, left, right);
        for(long x : solution) System.out.println("x = " + x);
    }

    private static int[] solution(int n, int left, int right) {
        int size = (int) (right - left) + 1;
        int[] answer = new int[size];

        // 조건 : target / n < target % n ->  (target % n) + 1;
        // 조건 : target / n >= target % n -> (target / n + 1)

        int loop  = 0;
        for(long target = left; target <= right; target++) {
            long rowIdx = target / n;
            long colIdx = target % n;
            if(rowIdx < colIdx) answer[loop] = (int) colIdx + 1;
            else answer[loop] = (int) rowIdx  + 1;
            loop++;
        }
        return answer;
    }
}
