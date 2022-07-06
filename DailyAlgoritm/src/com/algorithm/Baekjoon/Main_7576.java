package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;

public class Main_7576 {
    /*
   (입력)
   첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다.
   M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다.
   단, 2 ≤ M,N ≤ 1,000 이다.
   둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다.
   즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다.
   하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다.
   정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
   (출력)
   여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
    만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
   6 4                 8
   0 0 0 0 0 0
   0 0 0 0 0 0
   0 0 0 0 0 0
   0 0 0 0 0 1
   */
    static class Tomato {
        int row;
        int col;
        int day;

        public Tomato(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int[][] storage = new int[row][col];
        List<Tomato> firstTomato = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < col; j++) {
                int isTomato = Integer.parseInt(st.nextToken());
                storage[i][j] = isTomato;
                if (isTomato == 1) {
                    firstTomato.add(new Tomato(i, j, 0));
                }
            }
        }
        boolean[][] visited = new boolean[row][col];
        int day = bfs(firstTomato, storage, row, col, visited);
        if (!isEveryTomato(storage, row, col) || isEveryTomatoMinus(storage,row,col))
            day = -1;
        bw.write(String.valueOf(day));
        br.close();
        bw.close();
    }

    private static int bfs(List<Tomato> firstTomato, int[][] storage, int row, int col, boolean[][] visited) {
        Queue<Tomato> que = new LinkedList<>();
        int count = 0;
        for (Tomato tm : firstTomato) {
            que.offer(tm);
        }
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Tomato ripeTm = que.poll();
                if (visited[ripeTm.row][ripeTm.col])
                    continue;
                visited[ripeTm.row][ripeTm.col] = true;
                connect(ripeTm, row, col, storage, que);
            }
            count++;
        }
        return count-1;
    }

    private static void connect(Tomato tm, int row, int col, int[][] storage, Queue<Tomato> que) {
        int tmRow = tm.row;
        int tmCol = tm.col;
        int orgIndex = (tmRow * row) + tmCol;
        if (tmRow > 0) {
            //위 탐색
            if (storage[tmRow - 1][tmCol] == 0) {
                storage[tmRow - 1][tmCol] = 1;
                que.offer(new Tomato(tmRow - 1, tmCol, tm.day + 1));
            }
        }
        if (tmRow < row - 1) {
            //아래 탐색
            if (storage[tmRow + 1][tmCol] == 0) {
                storage[tmRow + 1][tmCol] = 1;
                que.offer(new Tomato(tmRow + 1, tmCol, tm.day + 1));
            }
        }
        if (tmCol > 0) {
            //왼쪽 탐색
            if (storage[tmRow][tmCol - 1] == 0) {
                storage[tmRow][tmCol - 1] = 1;
                que.offer(new Tomato(tmRow, tmCol - 1, tm.day + 1));
            }
        }
        if (tmCol < col - 1) {
            //오른쪽 탐색
            if (storage[tmRow][tmCol + 1] == 0) {
                storage[tmRow][tmCol + 1] = 1;
                que.offer(new Tomato(tmRow, tmCol + 1, tm.day + 1));
            }
        }
    }

    private static boolean isEveryTomatoMinus(int[][] storage, int row, int col) {
        boolean flag = false;
        for (int i = 0; i < row; i++) {
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

    private static boolean isEveryTomato(int[][] storage, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (storage[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
