package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1495_기타리스트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] volume = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int song = 1; song <= N; song++) {
            volume[song] = Integer.parseInt(st.nextToken());
        }

//        int rst = getLastVolume(N, S, M, volume);
        int rst = getTest(N, S, M, volume);
        System.out.println(rst);

        br.close();
    }
    private static int max;
    private static int getTest(int songCount, int startVolume, int maxVolume, int[] volume) {
        max = startVolume;
        end =false;
        dfs(1, songCount, startVolume, maxVolume, volume);
        return end ? max : -1;
    }
    private static boolean end;
    private static void dfs(int song, int songCount, int curVolume, int maxVolume, int[] volume) {
        if(song > songCount) {
            end = true;
            max = Math.max(max, curVolume);
            return;
        }
        boolean run = false;
        if(curVolume + volume[song] >= 0 && curVolume + volume[song] <= maxVolume)
            dfs(song + 1, songCount, curVolume + volume[song], maxVolume, volume);

        if(curVolume - volume[song] >= 0 && curVolume - volume[song] <= maxVolume)
            dfs(song + 1, songCount, curVolume - volume[song], maxVolume, volume);

    }

    private static final int UP = 0, DOWN = 1;

    private static int getLastVolume(int songCount, int startVolume, int maxVolume, int[] volume) {
        /**
         * 1. dp 배열은 볼륨을 더하는 경우와 빼는 경우 두가지로 나뉘므로 2차원 dp로 선언
         * 2. 더했을 때의 최대 경우 : dp[0][col - 1] + volume[col], dp[1][col -1] + volume[col] => Math.max(dp[0][col -1], dp[1][col-1]) + volume[col]
         * 3. 뺏을 때의 최대 경우 : Math.max(dp[0][col-1], dp[1][col-1]) - volume[col]
         * 4. 2번과 3번의 경우 각각 maxVolume, 0을 넘어가는 경우는 반영이 되면 안됨 -> Max값 비교시 이전열에서 더한값 두개의 범위를 검증 후 Math.max진행
         * 5. n 번째 열의 0행 1행 중 최대값 리턴
         */

        int[][] dp = new int[2][songCount + 1];
        dp[UP][0] = dp[DOWN][0] = startVolume;

        for (int song = 1; song <= songCount; song++) {
            if(dp[UP][song-1] + volume[song] >= 0 && dp[UP][song-1] + volume[song] <= maxVolume)
                dp[UP][song] = Math.max(dp[UP][song - 1] + volume[song], dp[UP][song]);
        }
        return Math.max(dp[UP][songCount], dp[DOWN][songCount]);
    }

    private static void turnDownVolume(int song, int[][] dp, int one, int two) {
        if (one >= 0 && two >= 0) dp[DOWN][song] = Math.max(one, two);
        else if (one >= 0) dp[DOWN][song] = one;
        else if (two >= 0) dp[DOWN][song] = two;
        else dp[DOWN][song] = -1;

    }

    private static void turnUpVolume(int song, int[][] dp, int maxVolume, int one, int two) {
        if (one <= maxVolume && two <= maxVolume) dp[UP][song] = Math.max(one, two);
        else if (one <= maxVolume) dp[UP][song] = one;
        else if (two <= maxVolume) dp[UP][song] = two;
        else dp[UP][song] = -1;
    }
}
