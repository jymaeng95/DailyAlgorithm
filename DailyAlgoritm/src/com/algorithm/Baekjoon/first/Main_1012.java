package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.*;

/*
 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50),
 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다.
 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다.
 2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5

(출력 5 1)
 */
public class Main_1012 {
    static class Cabbage {
        int xIndex;
        int yIndex;

        public Cabbage(int xIndex, int yIndex) {
            this.xIndex = xIndex;
            this.yIndex = yIndex;
        }

    }

    public static int[][] cabbage;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tCase = 0; tCase < testCase; tCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int cabCount = Integer.parseInt(st.nextToken());
            cabbage = new int[row][col];
            boolean[][] visited = new boolean[row][col];
            int count = 0;

            for (int i = 0; i < cabCount; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                cabbage[first][second] = 1;
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (!visited[i][j] && cabbage[i][j] == 1) {
                        bfs(i, j, visited, row, col);
                        count++;
                    }
                }
            }
            bw.write(String.valueOf(count));
            bw.newLine();
        }
        br.close();
        bw.close();

    }

    private static void bfs(int i, int j, boolean[][] visited, int row, int col) {
        Queue<Cabbage> que = new LinkedList<>();
        que.offer(new Cabbage(i, j));
        while (!que.isEmpty()) {
            Cabbage cab = que.poll();
            if (visited[cab.xIndex][cab.yIndex])
                continue;
            visited[cab.xIndex][cab.yIndex] = true;
            checkConnect(cabbage, row, col, que, cab);
        }
    }

    private static void checkConnect(int[][] cabbage, int row, int col, Queue<Cabbage> que, Cabbage cab) {
        int i = cab.xIndex;
        int j = cab.yIndex;
        if (i > 0) {
            //위 탐색
            if (cabbage[i - 1][j] == 1) {
                que.offer(new Cabbage(i - 1, j));
            }
        }
        if (i < row - 1) {
            //아래 탐색
            if (cabbage[i + 1][j] == 1)
                que.offer(new Cabbage(i + 1, j));
        }
        if (j > 0) {
            //왼쪽 탐색
            if (cabbage[i][j - 1] == 1)
                que.offer(new Cabbage(i, j - 1));
        }
        if (j < col - 1) {
            //오른쪽 탐색
            if (cabbage[i][j + 1] == 1)
                que.offer(new Cabbage(i, j + 1));
        }

    }
}
