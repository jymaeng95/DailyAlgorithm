package com.algorithm.Programmers.Lv2;

public class Solution_92342_양궁대회 {
    public static void main(String[] args) {
        int n = 5;
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};

        int[] solution = solution(n, info);
        for(int x: solution) {
            System.out.print(x+" ");
        }

    }
    private static int[] solution(int n, int[] info) {
        /*
         * 과녁을 돌면서 어피치가 맞춘 개수보다 크면서 가장 작은 수와 0개로 탐색
         * 탐색시 화살 개수 확인 후 체크
         */
        int[] answer = {};
        return answer;
    }
}
