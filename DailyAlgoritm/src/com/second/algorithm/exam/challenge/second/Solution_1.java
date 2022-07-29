package com.second.algorithm.exam.challenge.second;

public class Solution_1 {
    public static void main(String[] args) {
//        int[] number = {-2, 3, 0, 2, -5};
        int[] number = {-3, -2, -1, 0, 1, 2, 3};

        int rst = solution(number);
        System.out.println(rst);

    }

    private static int count;
    private static int solution(int[] number) {
        count = 0;
        boolean[] students = new boolean[number.length];

        findTrio(0,0, students, number, 0);

        return count;
    }

    private static void findTrio(int student, int studentCount, boolean[] students, int[] number, int sum) {
        if (studentCount == 3) {
            // 합이 0이면 카운트 증가
            if(sum == 0) count++;
            return;
        }

        for (int start = student; start < number.length; start++) {
            if(!students[start]) {
                students[start] = true;
                findTrio(start+1,studentCount + 1, students, number, sum + number[start]);
                students[start] = false;
            }
        }
    }

}
