package com.algorithm.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main_2667 {
    /*
    첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고,
    그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

    첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
    (입력)                (출력)
    7                       3
    0110100                 7
    0110101                 8
    1110101                 9
    0000111
    0100000
    0111110
    0111000
    */
    class Index {
        int xIndex;
        int yIndex;

        public Index(int xIndex, int yIndex) {
            this.xIndex = xIndex;
            this.yIndex = yIndex;
        }

        public int getxIndex() {
            return this.xIndex;
        }

        public int getyIndex() {
            return this.yIndex;
        }
    }

    public static List<LinkedList<Index>> node = new ArrayList<LinkedList<Index>>();
    public static int vilCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int mapSize = Integer.parseInt(br.readLine());
        int[][] map = new int[mapSize][mapSize];
        Main_2667 test = new Main_2667();

        for (int i = 0; i < map.length; i++) {
            String[] token = br.readLine().split("");
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(token[j]);
                node.add(new LinkedList<>());

            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1)
                    test.checkConnect(map, i, j, mapSize);
            }
        }
        boolean[][] visited = new boolean[mapSize][mapSize];
        List<Integer> village = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    if (!visited[i][j]) {
                        village.add(test.dfs(i, j, visited,mapSize));
                    }
                }
            }
        }
        Collections.sort(village);
        bw.write(String.valueOf(village.size()));
        bw.newLine();
        for (int count : village) {
            bw.write(String.valueOf(count));
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    public void checkConnect(int[][] map, int i, int j, int mapSize) {
        int orgIndex = (i * mapSize) + j;
        if (i > 0) {
            //위 탐색
            if (map[i - 1][j] == 1)
                linkNode(i - 1, j, orgIndex);
        }
        if (i < mapSize - 1) {
            //아래 탐색
            if (map[i + 1][j] == 1)
                linkNode(i + 1, j, orgIndex);
        }
        if (j > 0) {
            //왼쪽 탐색
            if (map[i][j - 1] == 1)
                linkNode(i, j - 1, orgIndex);
        }
        if (j < mapSize - 1) {
            //오른쪽 탐색
            if (map[i][j + 1] == 1)
                linkNode(i, j + 1, orgIndex);
        }

    }

    public void linkNode(int i, int j, int orgIndex) {
        node.get(orgIndex).add(new Index(i, j));
    }

    public int dfs(int i, int j, boolean[][] visited,int mapSize) {
        vilCount = 0;
        dfsUtil(i, j, visited,mapSize);
        return vilCount;
    }

    public void dfsUtil(int i, int j, boolean[][] visited,int mapSize) {
        if (visited[i][j]) return;
        visited[i][j] = true;
        vilCount++;
        int nodeIndex = (i * mapSize) + j;
        for (Index index : node.get(nodeIndex)) {
            dfsUtil(index.getxIndex(), index.getyIndex(), visited,mapSize);
        }
    }
}
