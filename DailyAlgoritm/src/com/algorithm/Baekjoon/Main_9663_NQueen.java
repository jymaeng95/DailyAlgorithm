package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663_NQueen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] pos = new int[N];

        int rst = nQueen(N, pos);
        System.out.println(rst);
        br.close();
    }

    private static int count;
    private static int nQueen(int N, int[] pos) {
        count = 0;

        putQueen(0, pos, N);

        return count;
    }

    private static void putQueen(int row, int[] pos, int n) {
        // 모든 행에 퀸을 다 배치한 경우 개수 증가
        if(row == n) {
            count++;
            return;
        }

        // 0번째 열 부터 n 번재 열 까지
        for(int col = 0; col < n; col++) {
            pos[row] = col; // 현재 행의 퀸의 열위치를 저장

            if(possible(row, col, pos)) {
                putQueen(row + 1, pos, n);
            }
        }
    }

    // 현재 위치에 퀸을 배치할 수 있는지 판단
    private static boolean possible(int curRow, int curCol, int[] pos) {
        // 첫 번째 행부터 현재 행 까지 놓인 퀸을 판단
        for(int row = 0; row < curRow; row++) {
            // 1. 이전에 놓인 퀸의 열과 현재 퀸의 열이 동일하면 겹치는 경우이므로 false
            if(pos[row] == curCol) return false;

            // 2. "이전 행과 현재 행의 차이"와 "이전 행의 열과 현재 행의 열의 차이"가 동일한 경우는 대각선에 퀸이 놓인 경우이므로 겹치는 경우 false
            if(Math.abs(row - curRow) == Math.abs(pos[row] - curCol)) return false;
        }
        // 3. 이외의 경우는 겹치지 않는 경우
        return true;
    }
}
