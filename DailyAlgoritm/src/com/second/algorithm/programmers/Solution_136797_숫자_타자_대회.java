package com.second.algorithm.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_136797_숫자_타자_대회 {
    public static void main(String[] args) {
//        String numbers = "1756";
        String numbers = "1756";

        int rst = solution(numbers);
        System.out.println("rst = " + rst);
    }

    private static final int[][] KEYBOARD = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}}; // *, # -> -1로 치환 (인풋은 아라비아 숫자만)
    private static final int[] DX = {-1, 1, 0, 0, -1, -1, 1, 1};    // 0~3 : 상하좌우, 4~7 : 대각
    private static final int[] DY = {0, 0, -1, 1, -1, 1, -1, 1};
    private static int[][] arr;

    private static int solution(String numbers) {
        // 각 자판으로부터 이동가능한 최소 거리 구하기
        arr = new int[10][10];

        // 각 자판으로부터 도달할 수 있는 최소 가중치 구하기
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                int target = KEYBOARD[row][col];
                if (target != -1) {
                    minDistance(row, col, target);
                    arr[target][target] = 1;
                }
            }
        }

        return makePath(4, 6, numbers);
    }

    private static int makePath(int left, int right, String numbers) {
        int[][][] check = new int[numbers.length()][10][10];
        for (int index = 0; index < numbers.length(); index++) {
            for (int row = 0; row < 10; row++) {
                Arrays.fill(check[index][row], (int) 1e10);

            }
        }

        PriorityQueue<Keyboard> pq = new PriorityQueue<>();
        pq.add(new Keyboard(0, left, right, 0));

        /**
         * 우선순위 큐 (거리 기준)를 이용해 가장 짧은 경로 도달 시 종료
         * 아닌 경우 예외 처리
         */
        while (!pq.isEmpty()) {
            Keyboard keyboard = pq.poll();
            int leftNumber = keyboard.getLeft();
            int rightNumber = keyboard.getRight();
            int index = keyboard.getIndex();
            int weight = keyboard.getWeight();

            // 최단 거리로 도착하는 경우
            if (index == numbers.length()) return weight;

            // 중복 제거
            if(check[index][leftNumber][rightNumber] > weight) {
                check[index][leftNumber][rightNumber] = check[index][rightNumber][leftNumber] = weight;

                int nextNumber = numbers.charAt(index) - '0';

                // 왼쪽 손가락 번호와 다음 숫자가 동일한 경우 반드시 왼쪽 손가락으로 눌러야함
                if (leftNumber == nextNumber)
                    pq.add(new Keyboard(index + 1, nextNumber, rightNumber, weight + arr[leftNumber][nextNumber]));
                // 오른쪽 손가락 번호화 다음 숫자가 동일한 경우 반드시 오른쪽 손가락으로 눌러야함
                else if (rightNumber == nextNumber)
                    pq.add(new Keyboard(index + 1, leftNumber, nextNumber, weight + 1));
                // 그 외 아무 손가락 가능
                else {
                    pq.add(new Keyboard(index + 1, nextNumber, rightNumber, weight + arr[leftNumber][nextNumber]));
                    pq.add(new Keyboard(index + 1, leftNumber, nextNumber, weight + arr[rightNumber][nextNumber]));
                }
            }
        }

        throw new IllegalStateException("예외 체크");
    }

    /**
     * 각 자판으로 부터 도달할 수 있는 최소 가중치 구하기
     *
     * @param row    행
     * @param col    열
     * @param target 자판 기준 숫자
     */
    private static void minDistance(int row, int col, int target) {
        boolean[] check = new boolean[10];

        PriorityQueue<Thumb> pq = new PriorityQueue<>();
        pq.add(new Thumb(row, col, 0));
        check[KEYBOARD[row][col]] = true;

        while (!pq.isEmpty()) {
            Thumb curPositionThumb = pq.poll();
            int r = curPositionThumb.getRow();
            int c = curPositionThumb.getCol();
            int weight = curPositionThumb.getWeight();

            for (int direction = 0; direction < 8; direction++) {
                int nextRow = r + DX[direction];
                int nextCol = c + DY[direction];

                if (!outOfKeyboard(nextRow, nextCol) && KEYBOARD[nextRow][nextCol] != -1 && !check[KEYBOARD[nextRow][nextCol]]) {
                    // 0~3 인 경우 가중치 2; 4-7인 경우 3
                    check[KEYBOARD[nextRow][nextCol]] = true;
                    if (direction < 4) {
                        pq.add(new Thumb(nextRow, nextCol, weight + 2));
                        arr[target][KEYBOARD[nextRow][nextCol]] = weight + 2;
                    } else {
                        pq.add(new Thumb(nextRow, nextCol, weight + 3));
                        arr[target][KEYBOARD[nextRow][nextCol]] = weight + 3;
                    }
                }
            }
        }
    }

    // 키보드 범위 체크
    private static boolean outOfKeyboard(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 3;
    }

    // 손가락 위치
    private static class Thumb implements Comparable<Thumb> {
        private final int row;
        private final int col;
        private final int weight;

        public Thumb(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Thumb o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    // 키보드
    private static class Keyboard implements Comparable<Keyboard> {
        private final int index;
        private final int left;
        private final int right;
        private final int weight;

        public Keyboard(int index, int left, int right, int weight) {
            this.index = index;
            this.left = left;
            this.right = right;
            this.weight = weight;
        }

        public int getIndex() {
            return index;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Keyboard o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
