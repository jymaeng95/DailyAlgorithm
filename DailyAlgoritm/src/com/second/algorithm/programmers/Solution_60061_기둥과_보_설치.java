package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_60061_기둥과_보_설치 {
    public static void main(String[] args) {
        int n = 5;
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};

//        int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
        int[][] rst = solution(n, build_frame);
        for (int[] ints : rst) {
            for (int x : ints) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    private static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    private static final int COLUMN = 0, BEAM = 1;
    private static final int DELETE = 0, BUILD = 1;

    private static int[][] solution(int n, int[][] build_frame) {
        boolean[][][] built = new boolean[n + 1][n + 1][4];

        /**
         * 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
         * 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
         * x, y, a, b -> (x,y), a 기둥/보, b 설치 / 삭제
         * x,y a -> (x,y), a 기둥/보 (x,y,a) 오름차순 정렬
         */

        for (int[] frame : build_frame) {
            int y = frame[0];
            int x = frame[1];
            int structure = frame[2];
            int method = frame[3];

            if (method == BUILD) {
                // 기둥 설치 경우
                if (structure == COLUMN) {
                    if (checkBuild(x, y, built, structure)) {
                        built[x][y][UP] = built[x + 1][y][DOWN] = true;
                    }
                }
                // 보 설치 경우
                else {
                    if (checkBuild(x, y, built, structure)) {
                        built[x][y][RIGHT] = built[x][y + 1][LEFT] = true;
                    }
                }
            }
            // 삭제 하는 경우
            else {
                if (structure == COLUMN) {
                    if (checkDelete(x, y, built, structure)) {
                        built[x][y][UP] = built[x+1][y][DOWN] = false;
                    }
                } else {
                    if (checkDelete(x, y, built, structure)) {
                        built[x][y][RIGHT] = built[x][y+1][LEFT] = false;
                    }
                }
            }
        }

        List<Structure> structureList = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (built[row][col][UP] && built[row + 1][col][DOWN]) {
                    structureList.add(new Structure(row, col, COLUMN));
                }
                if(built[row][col][RIGHT] && built[row][col+1][LEFT]) {
                    structureList.add(new Structure(row, col, BEAM));
                }
            }
        }

        Collections.sort(structureList);
        int[][] result = new int[structureList.size()][3];
        for (int index = 0; index < structureList.size(); index++) {
            Structure structure = structureList.get(index);
            result[index][0] = structure.getX();
            result[index][1] = structure.getY();
            result[index][2] = structure.getStructure();
        }

        return result;
    }

    private static boolean checkDelete(int x, int y, boolean[][][] built, int structure) {
        if (structure == COLUMN) {
            // 기둥을 삭제하는 경우 해당 좌표에서 연결된 보가 있거나 위에 기둥이 있다면 삭제할 수 없다.
            return (built[x][y][LEFT] && built[x][y - 1][DOWN]) || (built[x][y][RIGHT] && built[x][y + 1][DOWN])
                    || (built[x][y - 1][RIGHT] && built[x][y][LEFT] && built[x][y][RIGHT] && built[x][y + 1][LEFT]);
        } else {
            // 보를 삭제할 때 해당 좌표 양쪽에 보를 확인하고 위에 현재 좌표에 기둥이 있는지 확인한다.
            return (built[x][y][DOWN] || built[x][y - 1][DOWN]) || (built[x][y + 1][DOWN] || built[x][y + 2][DOWN]) || (!built[x][y][UP] && !built[x][y + 1][UP]);
        }
    }

    private static boolean checkBuild(int x, int y, boolean[][][] built, int structure) {
        if (structure == COLUMN) {
            // 바닥에 있거나, 기둥 위에 있거나, 보의 끝 부분에 있거나
            return x == 0 || built[x][y][DOWN] || (built[x][y][LEFT] && !built[x][y][RIGHT]) || (!built[x][y][LEFT]) && built[x][y][RIGHT];
        } else {
            // 한쪽 끝 부분에 기둥 위이거나 양쪽 끝부분이 동시 연결된 경우
            return built[x][y][DOWN] || built[x][y + 1][DOWN] || (built[x][y][LEFT] && built[x][y + 1][RIGHT]);
        }
    }

    static class Structure implements Comparable<Structure> {
        private int x;
        private int y;
        private int structure;

        public Structure(int x, int y, int structure) {
            this.x = x;
            this.y = y;
            this.structure = structure;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getStructure() {
            return structure;
        }

        @Override

        public int compareTo(Structure o) {
            if(x == o.x) {
                if(y == o.y) {
                    return Integer.compare(structure, o.structure);
                }
                return Integer.compare(y, o.y);
            }
            return Integer.compare(x, o.x);
        }
    }
}
