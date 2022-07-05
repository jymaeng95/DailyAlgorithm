package com.algorithm.Baekjoon.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_배열_돌리기_3 {
    private static int N, M, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // -- Input -- //
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 연산 개수

        // Input 배열
        int[][] arr = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                arr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 연산 시작
        st = new StringTokenizer(br.readLine());
        for (int count = 1; count <= R; count++) {
            int operation = Integer.parseInt(st.nextToken());
            arr = turnArray(arr, operation);
        }

        // 정답 출력
        for (int[] row : arr) {
            for (int number : row) {
                System.out.print(number+" ");
            }
            System.out.println();
        }
        br.close();
    }

    private static int[][] turnArray(int[][] arr, int operation) {
        // 들어오는 배열 길이 갱신
        N = arr.length;
        M = arr[0].length;

        if (operation == 1) return reverseUpAndDown(arr);
        else if (operation == 2) return reverseLeftAndRight(arr);
        else if(operation == 3) return turnRight(arr);
        else if(operation== 4) return turnLeft(arr);
        else if(operation == 5) return turnRightEach(arr);
        return turnLeftEach(arr);
    }

    // 1번 연산 배열 상하반전
    private static int[][] reverseUpAndDown(int[][] arr) {
        // 복사할 배열
        int[][] afterArr = new int[N][M];

        // 1번 상하반전 (row : 기존 배열 Row, afterRow : 복사할 배열 행)
        for (int row = N - 1, afterRow = 0; afterRow < N; row--, afterRow++) {
            for (int col = 0, afterCol = 0; col < M; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        return afterArr;
    }

    // 2번 연산 : 배열 좌우 반전
    private static int[][] reverseLeftAndRight(int[][] arr) {
        // 복사할 배열
        int[][] afterArr = new int[N][M];

        // 좌우 반전 시작 (col : 기존 배열 col, afterCol : 복사할 배열 col)
        for (int col = M - 1, afterCol = 0; afterCol < M; col--, afterCol++) {
            for (int row = 0, afterRow = 0; row < N; row++, afterRow++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        return afterArr;
    }

    // 3번 연산 : 배열 90도 회전
    private static int[][] turnRight(int[][] arr) {
        // 복사할 배열 (90도 회전은 행 열의 크기가 바뀜)
        int[][] afterArr = new int[M][N];

        // 배열 90도 회전 (기존 row가 afterCol의 길이와 동일, 기존 col이 afterRow의 길이와 동일)
        for (int row = 0, afterCol = N - 1; row < N; row++, afterCol--) {
            for (int col = 0, afterRow = 0; col < M; col++, afterRow++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        return afterArr;
    }

    // 4번 연산 : 배열 왼쪽 90도 회전
    private static int[][] turnLeft(int[][] arr) {
        // 복사할 배열
        int[][] afterArr = new int[M][N];

        // 배열 90도 왼쪽 회전
        for (int row = 0, afterCol = 0; row < N; row++, afterCol++) {
            for (int col = 0, afterRow = M - 1; col < M; col++, afterRow--) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        return afterArr;
    }

    // 5번 연산 : 1->2, 2->3, 3->4, 4->1
    private static int[][] turnRightEach(int[][] arr) {
        // 복사할 배열
        int[][] afterArr = new int[N][M];

        // 1->2
        for (int row = 0, afterRow = 0; row < N / 2; row++, afterRow++) {
            for (int col = 0, afterCol = M / 2; col < M / 2; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        // 2->3
        for (int row = 0, afterRow = N / 2; row < N / 2; row++, afterRow++) {
            for (int col = M / 2, afterCol = M / 2; col < M; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        // 3->4
        for (int row = N / 2, afterRow = N / 2; row < N; row++, afterRow++) {
            for (int col = M / 2, afterCol = 0; col < M; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        // 4->1
        for (int row = N / 2, afterRow = 0; row < N; row++, afterRow++) {
            for (int col = 0, afterCol = 0; col < M / 2; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        return afterArr;
    }

    // 6번 연산 : 1->4, 4->3, 3->2, 2->1
    private static int[][] turnLeftEach(int[][] arr) {
        // 복사할 배열
        int[][] afterArr = new int[N][M];

        // 1->4
        for (int row = 0, afterRow = N/2; row < N / 2; row++, afterRow++) {
            for (int col = 0, afterCol = 0; col < M / 2; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        // 4->3
        for (int row = N / 2, afterRow = N / 2; row < N; row++, afterRow++) {
            for (int col = 0, afterCol = M / 2; col < M / 2; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        // 3->2
        for (int row = N / 2, afterRow = 0; row < N; row++, afterRow++) {
            for (int col = M / 2, afterCol = M / 2; col < M; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        // 2-> 1
        for (int row = 0, afterRow = 0; row < N / 2; row++, afterRow++) {
            for (int col = M / 2, afterCol = 0; col < M; col++, afterCol++) {
                afterArr[afterRow][afterCol] = arr[row][col];
            }
        }

        return afterArr;
    }
}
