package com.algorithm.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<6;i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * 3면 : 4개
         * 2면 : 4(n-1)
         * 1면 : 5(n-2)(n-2)
         */
        int[] minDice = new int[3];
        minDice[0] = Math.min(dice[0], dice[5]);
        minDice[1] = Math.min(dice[1], dice[4]);
        minDice[2] = Math.min(dice[2], dice[3]);

        Arrays.sort(minDice);
        Arrays.sort(dice);

        int sum = 0;
        if(N == 1){
            for(int i=0;i<5;i++)
                sum += dice[i];
        }else{
            int oneSide = 9 * (N-2) * (N-2);
            int secondSide = 4 * (N-1) + 4 * (N-2);
            int thirdSide = 4;

            sum += oneSide * minDice[0];
            sum += secondSide * (minDice[0]+minDice[1]);
            sum += thirdSide * (minDice[0]+minDice[1]+minDice[2]);
        }

        bw.write(String.valueOf(sum));

        br.close();
        bw.close();
    }
}
