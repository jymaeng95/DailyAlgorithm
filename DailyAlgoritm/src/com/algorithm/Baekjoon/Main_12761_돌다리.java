package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12761_돌다리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int rst = countJumping(A, B, N, M);
        System.out.println(rst);

        br.close();
    }

    private static final char[] op = {'+', '-', '+', '-', '+', '-', '*', '*'};
    private static int countJumping(int a, int b, int n, int m) {
        final int[] jump = {1, 1, a, a, b, b, a, b};

        if(n == m) return 0;
        int[] count = new int[100001]; // 점프 횟수
        Arrays.fill(count, Integer.MAX_VALUE); // MAX 가 방문안한 상태

        Queue<Integer> queue = new LinkedList<>();
        // 출발지 넣기
        queue.add(n);
        count[n] = 0;

        // a = 2, b= 3 , n = 1 m = 20
        // BFS
        while (!queue.isEmpty()) {
            int pos = queue.poll(); // 0

            // 주미한테 도착한 경우 (최초)
            if(pos == m) break;

            // 8가지 경우의 수 (+1, -1, +a, -a, +b, -b, *a, *b)
            for(int loop = 0; loop < 8; loop++) {
                int nextPos = afterJump(pos, op[loop], jump[loop]);  // pos : n , op[0] : +, jump[0] : 1
                // n = 1 , 3번위치: n + a, n * b  count[3(점프한 위치)] = count[n(현재위치)] + 1
                // a = 2 b = 3, n = 1 ==> 1: 0 2: , 3: 1, 4: MAX ... MAX
                if (checkBound(nextPos) && count[nextPos] == Integer.MAX_VALUE) {
                    count[nextPos] = count[pos] + 1;  // 현재 횟수에서 한번 추가 점프한 값이 다음 최소 점프 횟수
                    queue.add(nextPos);
                }
            }
        }

        return count[m];
    }

    private static boolean checkBound(int nextPos) {
        // 배열 범위 체크
        return 0 <= nextPos && 100000 >= nextPos;
    }

    // 연산 (다음 위치하는 곳) int nx = tmp + 1;
    private static int afterJump(int pos, char op, int jump) {
        if(op == '+') return pos + jump; // 현재 위치에서 jump만큼 가는 것
        if(op == '-') return pos - jump;
        return pos * jump;
    }
}
