package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_92342_양궁대회 {
    public static void main(String[] args) {
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] rst = solution(n, info);
        Arrays.stream(rst).forEach(System.out::println);
    }

    private static int[] ryan;
    private static int maxScore;

    private static int[] solution(int n, int[] info) {
        ryan = new int[11];
        maxScore = 0;

        int[] ryanArrow = new int[11];
        shotArrow(n, 0, info, ryanArrow);

        return maxScore > 0 ? ryan : new int[]{-1};
    }

    private static void shotArrow(int n, int scoreIndex, int[] info, int[] ryanArrow) {
        // 화살을 다 쏜 경우
        if (n == 0 || scoreIndex > 10) {
            // 점수 비교
            int ryanScore = 0;
            int apeachScore = 0;
            for (int score = 0; score < 11; score++) {
                if (ryanArrow[score] > info[score]) ryanScore += 10 - score;
                else if (info[score] != 0) apeachScore += 10 - score;
            }

            // 최대 점수차 갱신 한 경우
            if (maxScore < ryanScore - apeachScore) {
                // 점수가 동일한 경우 낮은 점수가 많은 걸로 비교
                maxScore = ryanScore - apeachScore;
                System.arraycopy(ryanArrow, 0, ryan, 0, 11);
            }
            // 최대 점수차와 점수가 같고 라이언과 어피치 점수가 동일하지 않은 경우에만
            else if (maxScore == ryanScore - apeachScore && ryanScore > apeachScore) {
                if (diffLowerScore(ryanArrow)) System.arraycopy(ryanArrow, 0, ryan, 0, 11);
            }

            return;
        }

        // 현재 점수에서 이기는 경우(남은 화살 쏠수 있는 경우에만 사용)
        if (n > info[scoreIndex]) {
            ryanArrow[scoreIndex] = info[scoreIndex] + 1;
            shotArrow(n - ryanArrow[scoreIndex], scoreIndex + 1, info, ryanArrow);
            ryanArrow[scoreIndex] = 0;
        }
        // 남는 화살 다 쏘는 경우
        else {
            ryanArrow[scoreIndex] = n;
            shotArrow(0, scoreIndex + 1, info, ryanArrow);
            ryanArrow[scoreIndex] = 0;
        }

        // 진 경우 0발 쏘기
        shotArrow(n, scoreIndex + 1, info, ryanArrow);

    }

    private static boolean diffLowerScore(int[] ryanArrow) {
        for (int score = 10; score >= 0; score--) {
            if (ryanArrow[score] > ryan[score]) return true;
            else if(ryanArrow[score] < ryan[score]) return false;
        }
        return false;
    }
}
