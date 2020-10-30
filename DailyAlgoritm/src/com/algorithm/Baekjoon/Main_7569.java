package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;

public class Main_7569 {
    /*
    첫 줄에는 상자의 크기를 나타내는 두 정수 M,N과 쌓아올려지는 상자의 수를 나타내는 H가 주어진다.
    M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100 이다.
    둘째 줄부터는 가장 밑의 상자부터 가장 위의 상자까지에 저장된 토마토들의 정보가 주어진다.
    즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다.
    각 줄에는 상자 가로줄에 들어있는 토마토들의 상태가 M개의 정수로 주어진다.
    정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
    이러한 N개의 줄이 H번 반복하여 주어진다.
     */
    static class Tomato {
        int row;
        int col;

        public Tomato(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int[][] storage = new int[row * height][col];
        List<Tomato> firstTomato = new ArrayList<>();
        for (int i = 0; i < row * height; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < col; j++) {
                int tm = Integer.parseInt(st.nextToken());
                storage[i][j] = tm;
                if (tm == 1) {
                    firstTomato.add(new Tomato(i, j));
                }
            }
        }
        boolean[][] visited = new boolean[row * height][col];
        int day = bfs(row, col, height, firstTomato, storage, visited);
        if (!isEveryTomato(storage, row, col, height) || isEveryTomatoMinus(storage, row, col, height)) {
            day = -1;
        }
        bw.write(String.valueOf(day));
        bw.close();
        br.close();

    }

    private static int bfs(int row, int col, int height, List<Tomato> firstTomato, int[][] storage, boolean[][] visited) {
        Queue<Tomato> que = new LinkedList<>();
        for (Tomato firstTm : firstTomato) {
            que.offer(firstTm);
        }
        int count = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Tomato ripeTm = que.poll();
                if (visited[ripeTm.row][ripeTm.col])
                    continue;
                visited[ripeTm.row][ripeTm.col] = true;
                connect(row, col, height, ripeTm, storage, que);
            }
            count++;
        }
        return count - 1;
    }

    private static void connect(int row, int col, int height, Tomato tm, int[][] storage, Queue<Tomato> que) {
        int tmRow = tm.row;
        int tmCol = tm.col;
        if (tmRow > 0 && (tmRow % row != 0)) {
            //앞 탐색
            if (storage[tmRow - 1][tmCol] == 0) {
                storage[tmRow - 1][tmCol] = 1;
                que.offer(new Tomato(tmRow - 1, tmCol));
            }
        }
        if (tmRow < row * height && ((tmRow + 1) % row != 0)) {
            //뒤 탐색
            if (storage[tmRow + 1][tmCol] == 0) {
                storage[tmRow + 1][tmCol] = 1;
                que.offer(new Tomato(tmRow + 1, tmCol));
            }
        }
        if (tmCol > 0) {
            //왼쪽 탐색
            if (storage[tmRow][tmCol - 1] == 0) {
                storage[tmRow][tmCol - 1] = 1;
                que.offer(new Tomato(tmRow, tmCol - 1));
            }
        }
        if (tmCol < col - 1) {
            //오른쪽 탐색
            if (storage[tmRow][tmCol + 1] == 0) {
                storage[tmRow][tmCol + 1] = 1;
                que.offer(new Tomato(tmRow, tmCol + 1));
            }
        }
        if (tmRow - row >= 0) {
            //아래 탐색
            if (storage[tmRow - row][tmCol] == 0) {
                storage[tmRow - row][tmCol] = 1;
                que.offer(new Tomato(tmRow - row, tmCol));
            }
        }
        if (tmRow + row < row * height) {
            //위 탐색
            if (storage[tmRow + row][tmCol] == 0) {
                storage[tmRow + row][tmCol] = 1;
                que.offer(new Tomato(tmRow + row, tmCol));
            }
        }
    }

    private static boolean isEveryTomatoMinus(int[][] storage, int row, int col, int height) {
        boolean flag = false;
        for (int i = 0; i < row * height; i++) {
            for (int j = 0; j < col; j++) {
                if (storage[i][j] == -1) {
                    flag = true;
                } else {
                    return false;
                }
            }
        }
        return flag;
    }

    private static boolean isEveryTomato(int[][] storage, int row, int col, int height) {
        for (int i = 0; i < row * height; i++) {
            for (int j = 0; j < col; j++) {
                if (storage[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
