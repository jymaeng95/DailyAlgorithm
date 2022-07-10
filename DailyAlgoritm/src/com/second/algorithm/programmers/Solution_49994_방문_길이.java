package com.second.algorithm.programmers;

public class Solution_49994_방문_길이 {
    public static void main(String[] args) {
//        String dirs = "ULURRDLLU";
        String dirs = "LULLLLLLU";

        int rst = solution(dirs);
        System.out.println("rst = " + rst);
    }

    private static final int U = 0, D = 1, R = 2, L = 3;
    private static int startRow, startCol;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    private static int solution(String dirs) {
        /**
         * U: 위쪽으로 한 칸 가기
         * D: 아래쪽으로 한 칸 가기
         * R: 오른쪽으로 한 칸 가기
         * L: 왼쪽으로 한 칸 가기
         * 지도 : 10 * 10 -> 좌표로하면 11 * 11
         */
        boolean[][][] walk = new boolean[11][11][4]; // 걸은 곳인지 체크
        startRow = startCol = 5;    // 첫 위치 : 5,5

        int count = 0;
        for (char direction : dirs.toCharArray()) {
            if (direction == 'U') count += moveCharacter(U, D, walk);
            else if (direction == 'D') count += moveCharacter(D, U, walk);
            else if (direction == 'R') count += moveCharacter(R, L, walk);
            else count += moveCharacter(L, R, walk);
        }
        return count;
    }

    private static int moveCharacter(int direction, int reverse, boolean[][][] walk) {
        int nextRow = startRow + DX[direction];
        int nextCol = startCol + DY[direction];

        // 좌표경계 안넘는 경우
        if (checkBoundary(nextRow, nextCol)) {
            // 현재 행, 현재 열 -> 다음 행, 다음 열 가는 경우 == 다음 행, 다음 열 -> 현재 행, 현재 열 가는 경우
            if (!walk[nextRow][nextCol][direction]) {
                walk[nextRow][nextCol][direction] = true;   // 현재 행, 현재 열 -> 다음 행, 다음 열로 가는 길 체크
                walk[startRow][startCol][reverse] = true;   // 반대의 경우도 동일한 길이기 때문에 체크

                // 다음 시작점 체크
                startRow = nextRow;
                startCol = nextCol;

                return 1;
            }

            // 다음 시작점 체크
            startRow = nextRow;
            startCol = nextCol;
        }
        return 0;
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < 11 && nextCol >= 0 && nextCol < 11;
    }
}
