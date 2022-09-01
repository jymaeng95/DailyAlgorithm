package com.second.algorithm.programmers;

public class Solution_60059_자물쇠와_열쇠 {
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        boolean rst = solution(key, lock);
    }

    private static boolean solution(int[][] key, int[][] lock) {
        // 배열 증가 시키기
        int m = key.length;
        int n = lock.length;

        // 자물쇠 확장
        int[][] expandLock = new int[n * 3][n * 3];
        for (int row = n; row < n + n; row++) {
            for (int col = n; col < n + n; col++) {
                expandLock[row][col] = lock[row - n][col - n];
            }
        }

        // 열쇠 맞추기 (확장한 배열 가장 작은 홈 1*1에 들어가는 사이즈부터 시작)
        for (int row = n - m + 1; row < n + n; row++) {
            for (int col = n - m + 1; col < n + n; col++) {
                for (int rotate = 0; rotate < 4; rotate++) {
                    // expandLock에 key 더하기
                    putKey(row, col, expandLock, key);

                    if (releaseLock(expandLock, n)) return true;
                    // expandLock에 key 빼기
                    takeOutKey(row, col, expandLock, key);

                    // 열쇠 회전
                    key = rotateKey(key);
                }
            }
        }

        return false;
    }

    // 열쇠 넣기
    private static void putKey(int row, int col, int[][] expandLock, int[][] key) {
        for (int expandRow = row, keyRow = 0; expandRow < row + key.length; expandRow++, keyRow++) {
            for (int expandCol = col, keyCol = 0; expandCol < col + key.length; expandCol++, keyCol++) {
                expandLock[expandRow][expandCol] += key[keyRow][keyCol];
            }
        }
    }

    // 열쇠 맞는지 확인하기
    private static boolean releaseLock(int[][] expandLock, int n) {
        for (int row = n; row < n + n; row++) {
            for (int col = n; col < n + n; col++) {
                if (expandLock[row][col] != 1) return false;
            }
        }
        return true;
    }

    // 열쇠 빼기
    private static void takeOutKey(int row, int col, int[][] expandLock, int[][] key) {
        for (int expandRow = row, keyRow = 0; expandRow < row + key.length; expandRow++, keyRow++) {
            for (int expandCol = col, keyCol = 0; expandCol < col + key.length; expandCol++, keyCol++) {
                expandLock[expandRow][expandCol] -= key[keyRow][keyCol];
            }
        }
    }

    // 열쇠 90도 회전
    private static int[][] rotateKey(int[][] key) {
        int[][] rotateKey = new int[key.length][key.length];
        for (int row = 0; row < key.length; row++) {
            for (int col = 0; col < key.length; col++) {
                rotateKey[row][col] = key[key.length - 1 - col][row];
            }
        }

        return rotateKey;
    }
}
