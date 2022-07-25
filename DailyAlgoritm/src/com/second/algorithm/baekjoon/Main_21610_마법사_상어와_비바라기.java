package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_21610_마법사_상어와_비바라기 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] baskets = new int[N][N];

        // 바구니 물로 채우기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                baskets[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 구름 표시
        boolean[][] cloud = new boolean[N][N];

        // 최초 구름  (N, 1), (N, 2), (N-1, 1), (N-1, 2)
        cloud[N - 1][0] = cloud[N - 1][1] = cloud[N - 2][0] = cloud[N - 2][1] = true;

        // 방향 정보
        for (int move = 1; move <= M; move++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());       // d
            int distance = Integer.parseInt(st.nextToken());        // s

            cloud = commandSkill(cloud, baskets, direction, distance);
        }

        int sum = Arrays.stream(baskets).flatMapToInt(Arrays::stream)
                .sum();
        System.out.println(sum);
        br.close();
    }

    private static final int[] DX = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] DY = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static final int LEFT = 1, LEFT_UP = 2, UP = 3, RIGHT_UP = 4, RIGHT = 5, RIGHT_DOWN = 6, DOWN = 7, LEFT_DOWN = 8;

    private static boolean[][] commandSkill(boolean[][] cloud, int[][] baskets, int direction, int distance) {
        /**
         * 1. 모든 구름이 di 방향으로 si칸 이동한다.
         * 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
         * 3. 구름이 모두 사라진다.
         * 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
         * - 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
         * - 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
         * 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다. -> true인 경우는 false로 , false && basket >= 2인 경우 해당
         */

        // 1. 구름 이동시키기
        boolean[][] afterMoveCloud = moveCloud(cloud, baskets, direction, distance);

        // 4. 물복사 버그 시작
        copyWaterBug(baskets, afterMoveCloud);

        // 5. 구름 생성하기
        createNewCloud(baskets, afterMoveCloud);

        return afterMoveCloud;
    }

    // 새로운 구름 생성하기
    private static void createNewCloud(int[][] baskets, boolean[][] afterMoveCloud) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                // 현재 턴에 사라진 구름의 경우는 새롭게 생성하지 않는다. (현재 턴 구름 true)
                if (afterMoveCloud[row][col]) afterMoveCloud[row][col] = false;

                // 다음 턴에 이동할 구름 생성 (현재 턴 구름 아닌 경우 false, 바구니에 2이상)
                else if (!afterMoveCloud[row][col] && baskets[row][col] >= 2) {
                    afterMoveCloud[row][col] = true;
                    baskets[row][col] -= 2;
                }
            }
        }
    }

    // 물복사 버그 시작
    private static void copyWaterBug(int[][] baskets, boolean[][] afterMoveCloud) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                // 2에서 증가한 칸에 물복사 버그 시전
                if (afterMoveCloud[row][col]) {
                    int count = 0;

                    // 찍수인 경우 대각선
                    for (int directions = 2; directions <= 8; directions += 2) {
                        int nextRow = row + DX[directions];
                        int nextCol = col + DY[directions];

                        // 경계를 벗어나지 않고 물이 있는 경우 개수 증가
                        if (checkBoundary(nextRow, nextCol) && baskets[nextRow][nextCol] > 0)
                            count++;
                    }

                    // 바구니 물 늘리기
                    baskets[row][col] += count;
                }
            }
        }
    }

    private static boolean[][] moveCloud(boolean[][] cloud, int[][] baskets, int direction, int distance) {
        boolean[][] afterMoveCloud = new boolean[N][N];

        // 1. 구름 이동 시키기
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (cloud[row][col]) {
                    int nextRow = row + (DX[direction] * distance);
                    int nextCol = col + (DY[direction] * distance);

                    // 범위 벗어난 경우 (행)
                    if (nextRow >= N) nextRow %= N;
                    else if (nextRow < 0) nextRow = (nextRow + 1) % N + N - 1;

                    // 범위 벗어난 경우 (열)
                    if (nextCol >= N) nextCol %= N;
                    else if (nextCol < 0) nextCol = (nextCol + 1) % N + N - 1;

                    // 이동 후 구름표시 배열에 체크
                    afterMoveCloud[nextRow][nextCol] = true;

                    // 2. 이동 한 부분에 물 뿌리기
                    baskets[nextRow][nextCol] += 1;
                }
            }
        }
        return afterMoveCloud;
    }

    // 경계 체크
    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N;
    }
}
