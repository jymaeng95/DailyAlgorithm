package com.algorithm.Programmers.Lv2;

public class Solution_49994_방문_길이 {
    public static void main(String[] args) {
//        String dirs = "ULURRDLLU";
        String dirs = "LULLLLLLU";
        int rst = solution(dirs);
        System.out.println("rst = " + rst);
    }

    private static int solution(String dirs) {
        boolean[][][] visited = new boolean[11][11][4];
        int curX = 5;
        int curY = 5;
        int count = 0;
        for(char direction : dirs.toCharArray()) {
            if(direction == 'U') {
                int nextX = curX - 1;
                if(checkBounds(nextX)) {
                    if(!visited[nextX][curY][1]) {
                        visited[nextX][curY][1] = true;
                        visited[curX][curY][0] = true;
                        count++;
                    }
                    curX = nextX;
                }
            }
            else if(direction == 'D') {
                int nextX = curX + 1;
                if(checkBounds(nextX)) {
                    if(!visited[nextX][curY][0]) {
                        visited[nextX][curY][0] = true;
                        visited[curX][curY][1] = true;
                        count++;
                    }
                    curX = nextX;
                }
            }
            else if(direction == 'L') {
                int nextY = curY - 1;
                if(checkBounds(nextY)) {
                    if(!visited[curX][nextY][3]) {
                        visited[curX][nextY][3] = true;
                        visited[curX][curY][2] = true;
                        count++;
                    }
                    curY = nextY;
                }
            }
            else {
                int nextY = curY + 1;
                if(checkBounds(nextY)) {
                    if(!visited[curX][nextY][2]) {
                        visited[curX][nextY][2] = true;
                        visited[curX][curY][3] = true;
                        count++;
                    }
                    curY = nextY;
                }
            }
        }

        return count;
    }

    private static boolean checkBounds(int next) {
        return next >= 0 && next < 11;
    }
}
