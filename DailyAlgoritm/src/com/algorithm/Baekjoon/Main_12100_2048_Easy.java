package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12100_2048_Easy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        int rst = play2048(N, board);
        System.out.println(rst);

        br.close();
    }

    private static int number;

    private static int play2048(int n, int[][] board) {
        number = 2;

        start2048(1, n, board);

        return number;
    }

    private static void start2048(int turn, int n, int[][] board) {
        if (turn > 5) {
            // 5턴 모두 소진한 경우 최대 숫자값 갱신
            number = Math.max(number, getMaxNumber(n, board));
            return;
        }

        // 배열 깊은 복사
        int[][] originalBoard = copyBoard(board, n);

        // 상
        mergeUp(board, n);
        start2048(turn + 1, n, board);
        board = copyBoard(originalBoard, n);

        // 하
        mergeDown(board, n);
        start2048(turn + 1, n, board);
        board = copyBoard(originalBoard, n);

        // 좌
        mergeLeft(board, n);
        start2048(turn + 1, n, board);
        board = copyBoard(originalBoard, n);

        // 우
        mergeRight(board, n);
        start2048(turn + 1, n, board);
    }

    // 오른쪽 합치기
    private static void mergeRight(int[][] board, int n) {
        for (int row = 0; row < n; row++) {
            for (int col = n - 1; col >= 0; col--) {
                // 병합 가장 첫번재 병합 셀 찾기
                for (int mergeCol = col - 1; mergeCol >= 0; mergeCol--) {
                    // 병합할 셀이 빈칸이 아닐 때 까지 이동
                    if (board[row][mergeCol] != 0) {
                        // 병합할 셀과 합칠 셀이 동일 하다면 합치기
                        if (board[row][mergeCol] == board[row][col]) {
                            board[row][col] *= 2;
                            board[row][mergeCol] = 0;
                        }
                        //첫번째 것만 비교하면 되기 때문에 멈추기
                        break;
                    }
                }
            }
        }

        // 오른쪽으로 밀어주기
        for (int row = 0; row < n; row++) {
            for (int col = n - 1; col >= 0; col--) {
                //빈칸인 경우
                if (board[row][col] == 0) {
                    int nextCol = col - 1;
                    // 다음 열이 0이 아닐때까지 이동하기
                    while (nextCol >= 0 && board[row][nextCol] == 0) nextCol--;
                    // 범위 벗어나지 않고 다른 값을 찾앗다면 올려주기
                    if (nextCol >= 0) {
                        board[row][col] = board[row][nextCol];
                        board[row][nextCol] = 0;
                    }
                }
            }
        }
    }

    private static void mergeLeft(int[][] board, int n) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // 병합 가장 첫번재 병합 셀 찾기
                for (int mergeCol = col + 1; mergeCol < n; mergeCol++) {
                    // 병합할 셀이 빈칸이 아닐 때 까지 이동
                    if (board[row][mergeCol] != 0) {
                        // 병합할 셀과 합칠 셀이 동일 하다면 합치기
                        if (board[row][mergeCol] == board[row][col]) {
                            board[row][col] *= 2;
                            board[row][mergeCol] = 0;
                        }
                        //첫번째 것만 비교하면 되기 때문에 멈추기
                        break;
                    }
                }
            }
        }

        // 왼쪽을로 밀어주기
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                //빈칸인 경우
                if (board[row][col] == 0) {
                    int nextCol = col + 1;
                    // 다음 열이 0이 아닐때까지 내려가기
                    while (nextCol < n && board[row][nextCol] == 0) nextCol++;
                    // 범위 벗어나지 않고 다른 값을 찾앗다면 올려주기
                    if (nextCol < n) {
                        board[row][col] = board[row][nextCol];
                        board[row][nextCol] = 0;
                    }
                }
            }
        }
    }

    private static void mergeDown(int[][] board, int n) {
        for (int col = 0; col < n; col++) {
            for (int row = n - 1; row >= 0; row--) {
                // 병합 가장 첫번재 병합 셀 찾기
                for (int mergeRow = row - 1; mergeRow >= 0; mergeRow--) {
                    // 병합할 셀이 빈칸이 아닐 때 까지 이동
                    if (board[mergeRow][col] != 0) {
                        // 병합할 셀과 합칠 셀이 동일 하다면 합치기
                        if (board[mergeRow][col] == board[row][col]) {
                            board[row][col] *= 2;
                            board[mergeRow][col] = 0;
                        }
                        //첫번째 것만 비교하면 되기 때문에 멈추기
                        break;
                    }
                }
            }
        }

        // 밑으로 밀어주기
        for (int col = 0; col < n; col++) {
            for (int row = n - 1; row >= 0; row--) {
                //빈칸인 경우
                if (board[row][col] == 0) {
                    int nextRow = row - 1;
                    // 다음 이 0이 아닐때까지 내려가기
                    while (nextRow >= 0 && board[nextRow][col] == 0) nextRow--;
                    // 범위 벗어나지 않고 다른 값을 찾앗다면 올려주기
                    if (nextRow >= 0) {
                        board[row][col] = board[nextRow][col];
                        board[nextRow][col] = 0;
                    }
                }
            }
        }
    }

    private static void mergeUp(int[][] board, int n) {
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                // 병합 가장 첫번재 병합 셀 찾기
                for (int mergeRow = row + 1; mergeRow < n; mergeRow++) {
                    // 병합할 셀이 빈칸이 아닐 때 까지 이동
                    if (board[mergeRow][col] != 0) {
                        // 병합할 셀과 합칠 셀이 동일 하다면 합치기
                        if (board[mergeRow][col] == board[row][col]) {
                            board[row][col] *= 2;
                            board[mergeRow][col] = 0;
                        }
                        //첫번째 것만 비교하면 되기 때문에 멈추기
                        break;
                    }
                }
            }
        }

        // 위로 밀어주기
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                //빈칸인 경우
                if (board[row][col] == 0) {
                    int nextRow = row + 1;
                    // 다음 행이 0이 아닐때까지 내려가기
                    while (nextRow < n && board[nextRow][col] == 0) nextRow++;
                    // 범위 벗어나지 않고 다른 값을 찾앗다면 올려주기
                    if (nextRow < n) {
                        board[row][col] = board[nextRow][col];
                        board[nextRow][col] = 0;
                    }
                }
            }
        }
    }

    private static int[][] copyBoard(int[][] board, int n) {
        int[][] originalBoard = new int[n][n];
        for (int row = 0; row < n; row++) {
            System.arraycopy(board[row], 0, originalBoard[row], 0, n);
        }
        return originalBoard;
    }

    private static int getMaxNumber(int n, int[][] board) {
        int max = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (max < board[row][col]) {
                    max = board[row][col];
                }
            }
        }
        return max;
    }
}
