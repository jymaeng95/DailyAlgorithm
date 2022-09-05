package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_77484_로또의_최고_순위와_최저_순위 {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};

        int[] rst = solution(lottos, win_nums);
        System.out.println("rst = " + rst);
    }

    private static int[] solution(int[] lottos, int[] win_nums) {
        // 로또 당첨 번호 배열에 체크
        // 0이 아닌 숫자 확인 해서 체크 개수 + 0의 개수 더하기 - 높은 순위
        // 개수가 낮은 순위

        boolean[] win = new boolean[46];
        for(int number : win_nums) win[number] = true;

        int zeroCount = (int) Arrays.stream(lottos).filter(x -> x == 0).count();
        int winCount = 0;
        for(int number : lottos) {
            if(number != 0 && win[number]) winCount++;
        }

        int maxGrade = 6 - (zeroCount + winCount) + 1;
        int minGrade = 6 - winCount + 1;

        if(maxGrade >= 6) maxGrade = 6;
        if(minGrade >= 6) minGrade = 6;

        return new int[]{maxGrade, minGrade};
    }
}
