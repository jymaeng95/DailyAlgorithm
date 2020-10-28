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
    static class Tomato implements Comparable<Tomato> {
        int row;
        int col;
        int day;

        public Tomato(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getDay() {
            return day;
        }

        @Override
        public int compareTo(Tomato o) {
            if (this.day > o.day) {
                return 1;
            }
            return -1;
        }
    }

    public static List<LinkedList<Tomato>> tomato;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        int[][] storage = new int[row][col];
        boolean[][] visited = new boolean[row][col];
        tomato = new ArrayList<>();
        List<Tomato> firstTomato = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                int tm = Integer.parseInt(st.nextToken());
                storage[i][j] = tm;
                tomato.add(new LinkedList<>());
                if (tm == 1) {
                    firstTomato.add(new Tomato(i, j, 0));
                }
            }
        }
        br.close();
        int day = bfs(row, col, firstTomato, visited, storage);
        if (!isEveryTomato(storage, row, col))
            day = -1;
        bw.write(String.valueOf(day));
        bw.close();
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

    private static int bfs(int row, int col, List<Tomato> firstTomato, boolean[][] visited, int[][] storage) {
        Queue<Tomato> que = new LinkedList<>();
        for (Tomato tm : firstTomato) {
            que.offer(tm);
        }
        int day = 0;
        while (!que.isEmpty()) {
            Tomato tm = que.poll();
            if (visited[tm.getRow()][tm.getCol()])
                continue;
            visited[tm.getRow()][tm.getCol()] = true;
            connect(tm, row, col, storage);
            int index = (tm.getRow() * row) + tm.getCol();
            for (Tomato tom : tomato.get(index)) {
                que.offer(tom);
            }
            if (que.isEmpty())
                day = tm.getDay();
        }
        return day;
    }

    private static void connect(Tomato tm, int row, int col, int[][] storage) {
        int tmRow = tm.getRow();
        int tmCol = tm.getCol();
        int orgIndex = (tmRow * row) + tmCol;
        if (tmRow > 0) {
            //위 탐색
            if (storage[tmRow - 1][tmCol] == 0) {
                storage[tmRow - 1][tmCol] = 1;
                linkNode(tmRow - 1, tmCol, orgIndex, tm.getDay());
            }
        }
        if (tmRow < row - 1) {
            //아래 탐색
            if (storage[tmRow + 1][tmCol] == 0) {
                storage[tmRow + 1][tmCol] = 1;
                linkNode(tmRow + 1, tmCol, orgIndex, tm.getDay());
            }
        }
        if (tmCol > 0) {
            //왼쪽 탐색
            if (storage[tmRow][tmCol - 1] == 0) {
                storage[tmRow][tmCol - 1] = 1;
                linkNode(tmRow, tmCol - 1, orgIndex, tm.getDay());
            }
        }
        if (tmCol < col - 1) {
            //오른쪽 탐색
            if (storage[tmRow][tmCol + 1] == 0) {
                storage[tmRow][tmCol + 1] = 1;
                linkNode(tmRow, tmCol + 1, orgIndex, tm.getDay());
            }
        }
    }

    private static void linkNode(int i, int j, int orgIndex, int day) {
        tomato.get(orgIndex).add(new Tomato(i, j, day + 1));
        Collections.sort(tomato.get(orgIndex));
    }
}
