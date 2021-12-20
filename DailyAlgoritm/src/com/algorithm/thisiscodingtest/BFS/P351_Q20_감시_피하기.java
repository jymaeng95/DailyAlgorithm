package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.StringTokenizer;

public class P351_Q20_감시_피하기 {
    public static void main(String[] args) throws IOException {
        /*
         * 1. N: 복도 크기 ,S : student, T : Teacher, O : Obstacle
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[][] aisle = new String[N][N];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j= 0;j<N;j++) {
                aisle[i][j] = st.nextToken();
            }
        }
        String rst = avoidTeacher(aisle, N);

        br.close();
        bw.close();
    }

    private static String avoidTeacher(String[][] aisle, int n) {
        /*
         * 1. T -> 상하좌우 감시 가능 / 방해물 있을 시 감시 못함
         * 2. 장애물 3개를 X 위치에 한개씩 설치 후 완탐 -> 이후 장애물 제거
         * 3. 3개 설치한 경우 상하좌우로 DFS
         * 3-1. DFS에서 벽에 부딪히거나 방해물에 부딪히는 경우 return yes 이외 false
         */

        return null;
    }


}
