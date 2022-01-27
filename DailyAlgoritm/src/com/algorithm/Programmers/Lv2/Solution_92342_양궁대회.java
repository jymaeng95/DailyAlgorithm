package com.algorithm.Programmers.Lv2;

public class Solution_92342_양궁대회 {
    private static int maxScore;

    public static void main(String[] args) {
        int n = 1;
        int[] info = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int[] solution = solution(n, info);
        for (int x : solution) {
            System.out.print(x + " ");
        }

    }

    private static int[] solution(int n, int[] info) {
        /*
         * 과녁을 돌면서 어피치가 맞춘 개수보다 크면서 가장 작은 수와 0개로 탐색
         * 탐색시 화살 개수 확인 후 체크
         */
        int[] answer = new int[11];
        int[] score = new int[11];
        maxScore = 0;

        goArchery(answer, score, info, 0, 0, n);
        if (maxScore == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }

    private static void goArchery(int[] answer, int[] score, int[] info, int idx, int arrow, int n) {
        //화살을 다 쓴 경우
        if (arrow == n || idx > 10) {
            int ryonScore = 0;
            int apeachScore = 0;
            for (int i = 0; i < 11; i++) {
                if (info[i] != 0 || score[i] != 0) {
                    if (score[i] > info[i]) ryonScore += 10 - i;
                    else apeachScore += 10 - i;
                }
            }

            if (maxScore <= (ryonScore - apeachScore)) {
                // 낮은 점수 많은 경우 확인
                if (maxScore == (ryonScore - apeachScore)) {
                    if(checkScore(answer, score))
                        System.arraycopy(score, 0, answer, 0, 11);
                }

                // 최대 점수 높거나 최대점수와 같지만 낮은 점수가 더 많은 경우
                if (maxScore < (ryonScore - apeachScore)) {
                    maxScore = Math.max(maxScore, ryonScore - apeachScore);
                    System.arraycopy(score, 0, answer, 0, 11);
                }
            }
            return;
        }

        // 어피치보다 한개 더많이 쏠 수 있는 경우
        if (info[idx] + 1 <= n - arrow) {
            score[idx] = info[idx] + 1;
        }
        // 남은 개수 중 최대로 쏠 수 있는 경우
        else {
            score[idx] = n - arrow;
        }
        goArchery(answer, score, info, idx + 1, arrow + score[idx], n);

        // 해당 점수 안쏘는 경우
        score[idx] = 0;
        goArchery(answer, score, info, idx + 1, arrow, n);

    }

    private static boolean checkScore(int[] answer, int[] score) {
        for (int i = 10; i >= 0; i--) {
            if (answer[i] < score[i]) return true;
            else if (answer[i] > score[i]) return false;
        }
        return false;
    }
}
