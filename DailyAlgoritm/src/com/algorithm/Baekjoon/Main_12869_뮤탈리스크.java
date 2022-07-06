package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12869_뮤탈리스크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] SCV = new int[3];
        int maxHP = 0;
        for (int number = 0; number < N; number++) {
            int HP = Integer.parseInt(st.nextToken());
            SCV[number] = HP;
            maxHP = Math.max(maxHP, HP);
        }
        count = 61;
        dp = new int[maxHP + 1][maxHP + 1][maxHP + 1];
        attackSCV(SCV[0], SCV[1], SCV[2], 0);

        System.out.println(count);
        br.close();
    }

    private static int count;
    private static int[][][] dp;

    private static void attackSCV(int scv1, int scv2, int scv3, int attackCount) {
        if (scv1 <= 0 && scv2 <= 0 && scv3 <= 0) {
            count = Math.min(count, attackCount);
            return;
        }

        if (scv1 <= 0) scv1 = 0;
        if (scv2 <= 0) scv2 = 0;
        if (scv3 <= 0) scv3 = 0;

        // 현재 SCV들의 체력에 대한 attackCount가 attack인 경우
        if (dp[scv1][scv2][scv3] <= attackCount && dp[scv1][scv2][scv3] != 0) return;

        dp[scv1][scv2][scv3] = attackCount;
        attackSCV(scv1 - 9, scv2 - 3, scv3 - 1, attackCount + 1);
        attackSCV(scv1 - 9, scv3 - 3, scv2 - 1, attackCount + 1);
        attackSCV(scv2 - 9, scv1 - 3, scv3 - 1, attackCount + 1);
        attackSCV(scv2 - 9, scv3 - 3, scv1 - 1, attackCount + 1);
        attackSCV(scv3 - 9, scv1 - 3, scv2 - 1, attackCount + 1);
        attackSCV(scv3 - 9, scv2 - 3, scv1 - 1, attackCount + 1);
    }
}
