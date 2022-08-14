package com.second.algorithm.exam.street;

public class Solution_1 {
    public static void main(String[] args) {
        int[] A = {3,2,5,4,1};

        int rst = solution(A);
        System.out.println("rst = " + rst);

    }

    private static int solution(int[] A) {
        boolean[] turnOn = new boolean[A.length + 1];
        int curLight = 1;
        int count = 0;
        for (int index = 0; index < A.length; index++) {
            // 스위치 누르기
            turnOn[A[index]] = true;

            // 불이 켜지는 경우
            if (curLight == A[index]){
                // 이 스위치를 누르면 불이 켜지는 경우
                // 카운트를 증가시키고
                count++;

                // 다음 누르면 불이 켜지는 곳 까지 이동
                while(curLight < turnOn.length && turnOn[curLight]) {
                    curLight++;
                }
            }
        }

        return count;
    }
}
