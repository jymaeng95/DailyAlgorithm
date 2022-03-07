package com.algorithm.Programmers.Lv2;

import java.util.*;

public class Solution_86052_빛의_경로_사이클 {
    public static void main(String[] args) {
        String[] grid = {"R","R"};
        int[] solution = solution(grid);
        for (int x : solution) System.out.println("x = " + x);
    }
    static class Point {
        private int row;
        private int col;
        private int dir;

        public Point(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getDir() {
            return dir;
        }
    }
    private static String[][] map;
    private static boolean[][][] visited;
    private static int[] solution(String[] grid) {
        map = new String[grid.length][grid[0].length()];
        visited = new boolean[grid.length][grid[0].length()][4];
        List<Integer> answerList = new ArrayList<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length(); col++) {
                map[row][col] = String.valueOf(grid[row].charAt(col));
            }
        }
        for(int row = 0; row< map.length; row++) {
            for(int col = 0; col < map[row].length; col++){
                // 0 : 상, 1: 하, 2: 좌, 3: 우
                for(int direction = 0; direction < 4; direction++) {
                    if(!visited[row][col][direction]) {
                        answerList.add(searchPath(row, col, direction));
                    }
                }
            }
        }
        Collections.sort(answerList);
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int searchPath(int row, int col, int direction) {
        int count = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col, direction));

        while(!queue.isEmpty()) {
            Point point = queue.poll();
            int curX = point.getRow();
            int curY = point.getCol();
            int curDir = point.getDir();

            if(visited[curX][curY][curDir]) return count;
            visited[curX][curY][curDir] = true;

            // 다음 노드 진입 방향 구하기
            Point next = nextPath(curX, curY, curDir);

            // 다음 노드 큐에 추가
            queue.add(new Point(next.getRow(), next.getCol(), next.getDir()));

            // 진입한 노드 갯수 증가
            count++;
        }

        return count;
    }

    // 다음 노드 진입 방향 구하기
    private static Point nextPath(int curX, int curY, int curDir) {
        int nextRow = curX;
        int nextCol = curY;
        int nextDir = curDir;

        // 현재 노드 진입 방향 별 다음 노드 진입 방향
        if((map[curX][curY].equals("S") && curDir ==0) || (map[curX][curY].equals("L") && curDir == 3) || (map[curX][curY].equals("R") && curDir == 2)) {
            nextRow = curX +1;
            nextDir = 0;
        }
        else if((map[curX][curY].equals("S") && curDir ==1) || (map[curX][curY].equals("L") && curDir == 2) || (map[curX][curY].equals("R") && curDir == 3)) {
            nextRow = curX -1;
            nextDir = 1;
        }
        else if((map[curX][curY].equals("S") && curDir ==2) || (map[curX][curY].equals("L") && curDir == 0) || (map[curX][curY].equals("R") && curDir == 1)) {
            nextCol = curY +1;
            nextDir = 2;
        }
        else {
            nextCol =  curY-1;
            nextDir = 3;
        }

        // 다음 노드의 행 혹은 열이 범위 밖으로 벗어나는 경우 체크
        if(checkEndpoint(nextRow, nextCol)) {
            if(nextDir == 0) {
                nextRow = 0;
            }
            else if(nextDir == 1) {
                nextRow = map.length-1;
            }
            else if(nextDir == 2) {
                nextCol = 0;
            }
            else {
                nextCol = map[nextRow].length-1;
            }
        }
        return new Point(nextRow, nextCol, nextDir);
    }

    private static boolean checkEndpoint(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[nextRow].length;
    }
}
