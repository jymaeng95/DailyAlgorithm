package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10725_빛의_왕과_거울의_미로_1 {
    private static int N, M, x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        char[][] board = new char[N + 2][M + 2];
        for (int row = 1; row <= N; row++) {
            String input = br.readLine();
            for (int col = 1; col <= M; col++) {
                board[row][col] = input.charAt(col - 1);
            }
        }

        long rst = shootLaser(board);
        System.out.println(rst);

        br.close();
    }

    private static final long MOD = 10007;
    private static long count;
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static long shootLaser(char[][] board) {
        /**
         * / : 왼쪽에서 레이저가 들어오면 위쪽으로 반사, 위쪽에서 레이저가 들어오면 왼쪽으로 반사, 오른쪽에서 레이저가 들어오면 아래쪽으로 반사, 아래쪽에서 레이저가 들어오면 오른쪽으로 반사하는 거울.
         * \ : 왼쪽에서 레이저가 들어오면 아래쪽으로 반사, 위쪽에서 레이저가 들어오면 오른쪽으로 반사, 오른쪽에서 레이저가 들어오면 위쪽으로 반사, 아래쪽에서 레이저가 들어오면 왼쪽으로 반사하는 거울.
         * . : 거울이 없어 레이저가 아무 방해를 받지 않고 지나갈 수 있는 칸.
         * ? : 위의 셋 중 어떤 것이라도 상관 없는 칸.
         */

        /**
         * 위쪽의 \(M\)개의 칸 : 왼쪽에서 오른쪽으로 \(1\)에서 \(M\)까지
         * 왼쪽의 \(N\)개의 칸 : 위쪽에서 아래쪽으로 \(M+1\)에서 \(M+N\)까지
         * 오른쪽의 \(N\)개의 칸 : 위쪽에서 아래쪽으로 \(M+N+1\)에서 \(M+N+N\)까지
         * 아래쪽의 \(M\)개의 칸 : 왼쪽에서 오른쪽으로 \(M+N+N+1\)에서 \(M+N+N+M\)까지
         */

        count = 0;

        // x,y를 통해 시작 행,열 방향을 구한다.
        int[] startPosition = convertCoordinate(x,false);
        int[] endPosition = convertCoordinate(y,true);
        boolean[][][] visited = new boolean[N + 2][M + 2][4];
        visited[startPosition[0]][startPosition[1]][startPosition[2]] = true;

        // 레이저 쏘기
        shoot(startPosition[0], startPosition[1], startPosition[2], endPosition[0], endPosition[1], board, visited);

        return count;
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static void shoot(int startRow, int startCol, int direction, int endRow, int endCol, char[][] board, boolean[][][] visited) {
        // endRow, endCol에 도착한 경우
        if(startRow == endRow && startCol == endCol) {
            int questionCount = 0;

            // 물음표 개수
            for (int row = 1; row <= N; row++) {
                for (int col = 1; col <= M; col++) {
                    if(board[row][col] == '?') questionCount++;
                }
            }

            // 개수 계산
            long available = 1;
            for (int loop = 1; loop <= questionCount; loop++) {
                available *= 3;
                available %= MOD;
            }

            count += available;
            count %= MOD;
        }

        // 경계 체크
        if(!checkBoundary(startRow, startCol)) return;

        // / 인 경우
        if(board[startRow][startCol] == '/' || board[startRow][startCol] == '\\' || board[startRow][startCol] == '.') {
            if(board[startRow][startCol] == '/') direction = mirrorOne(direction);
            else if(board[startRow][startCol] == '\\') direction = mirrorTwo(direction);
            int nextRow = startRow + DX[direction];
            int nextCol = startCol + DY[direction];
            if(!visited[nextRow][nextCol][direction]) {
                visited[nextRow][nextCol][direction] = true;
                shoot(nextRow, nextCol, direction, endRow, endCol, board, visited);
                visited[nextRow][nextCol][direction] = false;
            }
        }

        // ?인 경우 네가지 경우 다 가능
        else {
            // mirror1 가능
            int nextDirection = mirrorOne(direction);
            int nextRow = startRow + DX[nextDirection];
            int nextCol = startCol + DY[nextDirection];
            if(!visited[nextRow][nextCol][nextDirection]) {
                visited[nextRow][nextCol][nextDirection] = true;
                board[startRow][startCol] = '/';
                shoot(nextRow, nextCol, nextDirection, endRow, endCol, board, visited);
                board[startRow][startCol] = '?';
                visited[nextRow][nextCol][nextDirection] = false;
            }

            // mirror2 가능
            nextDirection = mirrorTwo(direction);
            nextRow = startRow + DX[nextDirection];
            nextCol = startCol + DY[nextDirection];
            if(!visited[nextRow][nextCol][nextDirection]) {
                visited[nextRow][nextCol][nextDirection] = true;
                board[startRow][startCol] = '\\';
                shoot(nextRow, nextCol, nextDirection, endRow, endCol, board, visited);
                board[startRow][startCol] = '?';
                visited[nextRow][nextCol][nextDirection] = false;
            }
            // . 가능
            nextRow = startRow + DX[direction];
            nextCol = startCol + DY[direction];
            if(!visited[nextRow][nextCol][direction]) {
                visited[nextRow][nextCol][direction] = true;
                board[startRow][startCol] = '.';
                shoot(nextRow, nextCol, direction, endRow, endCol, board, visited);
                board[startRow][startCol] = '?';
                visited[nextRow][nextCol][direction] = false;
            }
        }
    }

    private static boolean checkBoundary(int startRow, int startCol) {
        return startRow >= 1 && startRow <= N && startCol >= 1 && startCol <= M;
    }

    private static int mirrorTwo(int direction) {
        if(direction == UP) direction = LEFT;
        else if (direction == DOWN) direction = RIGHT;
        else if (direction == LEFT) direction = UP;
        else direction = DOWN;
        return direction;
    }

    private static int mirrorOne(int direction) {
        if(direction == UP) direction = RIGHT;
        else if (direction == DOWN) direction = LEFT;
        else if (direction == LEFT) direction = DOWN;
        else direction = UP;
        return direction;
    }

    private static int[] convertCoordinate(int pos, boolean end) {
        if (1 <= pos && pos <= M) {
            if(!end) return new int[]{1, pos, DOWN};
            return new int[]{0, pos};
        }
        else if (M + 1 <= pos && pos <= M + N) {
            if(!end) return new int[]{pos - M, 1, RIGHT};
            return new int[]{pos - M, 0};
        }
        else if (M + N + 1 <= pos && pos <= M + N + N) {
            if(!end) return new int[]{pos - M - N, M, LEFT};
            return new int[]{pos - M - N, M + 1};
        }
        if(!end) return new int[]{N, pos - M - N - N, UP};
        return new int[]{N + 1, pos - M - N - N};
    }
}
