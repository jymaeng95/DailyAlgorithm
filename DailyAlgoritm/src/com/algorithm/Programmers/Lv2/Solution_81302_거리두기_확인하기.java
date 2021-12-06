package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_81302_거리두기_확인하기 {
    static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        int[] solution = solution(places);
        for (int s : solution) {
            System.out.print(s + " ");
        }
    }

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer,1);
        int room = 0;
        for (String[] place : places) {
            boolean rst = true;
            for (int i = 0; i < 5; i++) {
                boolean[][] visited = new boolean[5][5];
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        if(!bfs(i,j,place,visited))
                            rst = false;

                    }
                }
            }
            if(!rst)
                answer[room] = 0;
            room++;
        }

        return answer;
    }

    private static boolean bfs(int i, int j, String[] place, boolean[][] visited) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(i, j));
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            int x = pos.getX();
            int y = pos.getY();
            if(visited[x][y]) continue;
            visited[x][y] = true;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !visited[nx][ny] && place[nx].charAt(ny) != 'X') {
                    int dist = Math.abs(i - nx) + Math.abs(j - ny);
                    if (place[nx].charAt(ny) == 'P' && dist <= 2) {
                            return false;
                    }
                    if(dist < 2)
                        queue.add(new Position(nx, ny));
                }
            }
        }
        return true;
    }
}
