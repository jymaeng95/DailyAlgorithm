/* 풀이 중
package com.algorithm.Baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_16236 {
    private static int N;
    private static boolean[][] visited;
    private static int second,count;
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, -1, 0, 1};
    private static Stack<Shark> stack;
    static class Shark {
        private int x;
        private int y;
        private int size;

        public Shark(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public Shark() {
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
        //먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
        //먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
        //거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
        //거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.

        //아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        visited = new boolean[N][N];
        stack = new Stack<>();
        second = 0;
        count = 0;
        Shark babyShark = new Shark();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int size = Integer.parseInt(st.nextToken());
                if (size == 9) {
                    babyShark = new Shark(i, j, 2);
                    board[i][j] = 0;
                    continue;
                }
                board[i][j] = size;
            }
        }
        bfs(board,babyShark,);
        bw.write(String.valueOf(second));
        br.close();
        bw.close();
    }

    private static void bfs(int[][] board, Shark shark, int count) {
        Queue<Shark> que = new LinkedList<>();
        que.offer(shark);
        while(!que.isEmpty()){
            Shark sh = que.poll();
            int x = sh.x;
            int y = sh.y;
            int size = sh.size;
            if(visited[x][y])
                continue;
            visited[x][y] = true;
        }
    }
}
*/
