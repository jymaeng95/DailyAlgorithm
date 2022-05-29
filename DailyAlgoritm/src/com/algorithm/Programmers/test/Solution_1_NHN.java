package com.algorithm.Programmers.test;

import java.util.HashSet;
import java.util.Set;

public class Solution_1_NHN {
    public static void main(String[] args) {
//        int[][] cards1 = {
//                {13, 21, 24, 29, 50},
//                {1, 12, 20, 21, 32},
//                {16, 26, 34, 46, 52},
//                {9, 11, 16, 16, 21},
//                {3, 8, 10, 16, 20}};
        int[][] cards1 = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        int[][] cards2 = {{5, 7, 9, 11, 13}, {11, 13, 15, 17, 19}};
//        int[][] cards2 = {{5, 10, 15, 41, 49}, {6, 14, 15, 19, 46}, {5, 42, 43, 51, 52}, {5, 6, 11, 13, 45}, {5, 9, 11, 13, 45}};
        int solution = solution(cards1, cards2);
        System.out.println(solution);
    }

    private static int solution(int[][] cards1, int[][] cards2) {
        int answer = 0;

        // 각 라운드 별 판단
        boolean[] prevRoundPlayer1 = new boolean[53];
        boolean[] prevRoundPlayer2 = new boolean[52];
        for(int round = 0; round < cards1.length; round++) {
            Set<Integer> curRoundCardSet = new HashSet<>();
            int errCountPlayer1 = 0;
            int errCountPlayer2 = 0;
            for(int card = 0; card <5; card++) {
                curRoundCardSet.add(cards1[round][card]);
                curRoundCardSet.add(cards2[round][card]);

                // 이전 라운드 플레이어 별 에러 개수 비교
                if(round > 0) {
                    if(prevRoundPlayer1[cards1[round][card]]) errCountPlayer1++;
                    if(prevRoundPlayer2[cards2[round][card]]) errCountPlayer2++;
                }
            }

            // 사이즈가 10개가 아니면 중복 있음, 각 플레이어별 에러 개수 2개 이상인 경우 잘못된 라운드
            if(curRoundCardSet.size() != 10 || errCountPlayer1 >= 2 || errCountPlayer2 >= 2) {
                answer++;
            }

            // 이전 라운드 초기화
            prevRoundPlayer1 = new boolean[53];
            prevRoundPlayer2 = new boolean[53];

            // 현재 라운드 카드 이전 라운드 배열에 저장
            for(int card = 0; card < 5; card++) {
                prevRoundPlayer1[cards1[round][card]] = true;
                prevRoundPlayer2[cards2[round][card]] = true;
            }
        }

        return answer;
    }
}
