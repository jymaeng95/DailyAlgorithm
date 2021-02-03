package com.algorithm.SWEA.D3;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_11387_몬스터_사냥 {
    /*
    용사가 몬스터를 공격할 때는 기본적으로 D만큼의 데미지를 입힌다. 여기에, 용사가 익힌 공격의 레벨 L에 따라 추가적인 데미지가 있는데,
    지금까지 몬스터를 때린 횟수가 n번이라고 하면, 다음 공격이 몬스터에게 입히는 데미지는 D(1+nㆍL%)가 된다. %는 1/100을 의미한다.
    지금까지 용사가 몬스터를 때린 횟수가 0번이라고 할 때, 앞으로 총 N번의 공격을 하면 몬스터에게 가한 총 데미지가 몇이 되는지 구하는 프로그램을 작성하라.
     */

    private static int D, N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            double answer = 0.0;
            for(int i = 0; i < N; i++) {
                answer += D * (1 + i * L / 100.0);
            }
            int totalDamage = (int) answer;
            bw.write("#"+tc+" "+totalDamage);
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
