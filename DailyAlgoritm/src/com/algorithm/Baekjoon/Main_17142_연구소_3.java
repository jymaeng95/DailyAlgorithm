package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17142_연구소_3 {
    static class Virus {
        private int row;
        private int col;

        public Virus(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private static int second;
    private static List<Virus> virusList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] lab = new int[N][N];
        virusList = new ArrayList<>();
        boolean result = false;
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 2) virusList.add(new Virus(row, col));
                if (type == 0) result = true;
                lab[row][col] = type;
            }
        }
        int rst = 0;
        if(result) rst =  enableVirus(N, M, lab);
        System.out.println(rst);
        br.close();
    }

    private static int enableVirus(int n, int m, int[][] lab) {
        second = Integer.MAX_VALUE;
        boolean[] virusVisited = new boolean[virusList.size()];
        selectVirus(0, 0, n, m, lab, virusVisited);
        return second == Integer.MAX_VALUE ? -1 : second;
    }

    private static void selectVirus(int start, int count, int n, int m, int[][] lab, boolean[] virusVisited) {
        if (count == m) {
            int[][] originalLab = copyLab(lab, n);
            calcSecond(n, m, originalLab, virusVisited);
            return;
        }

        for (int index = start; index < virusList.size(); index++) {
            if (!virusVisited[index]) {
                virusVisited[index] = true;
                Virus virus = virusList.get(index);
                lab[virus.getRow()][virus.getCol()] = 9;
                selectVirus(index + 1, count + 1, n, m, lab, virusVisited);
                lab[virus.getRow()][virus.getCol()] = 2;
                virusVisited[index] = false;
            }
        }
    }

    private static int[][] copyLab(int[][] lab, int n) {
        int[][] copyLab = new int[n][n];
        for(int row = 0; row < n; row ++) {
            for(int col = 0; col < n; col++) {
                copyLab[row][col] = lab[row][col];
            }
        }
        return copyLab;
    }

    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {1, -1, 0, 0};
    private static void calcSecond(int n, int m, int[][] lab, boolean[] virusVisited) {
        boolean[][] visited = new boolean[n][n];
        Queue<Virus> queue = new LinkedList<>();

        // 초기 활성 상태 바이러스 m개 넣어주기
        for (int index = 0; index < virusVisited.length; index++) {
            if (virusVisited[index]) {
                Virus virus = virusList.get(index);
                visited[virus.getRow()][virus.getCol()] = true;
                queue.add(virus);
            }
        }

        int time = 0;
        while (!queue.isEmpty()) {
            int curEnableSize = queue.size();       // 현재 시간에 활성화 되서 퍼져나갈 바이러스 개수
            for (int size = 0; size < curEnableSize; size++) {
                Virus virus = queue.poll();
                int curRow = virus.getRow();
                int curCol = virus.getCol();

                for(int direction = 0; direction < 4; direction++) {
                    int nextRow = curRow + DX[direction];
                    int nextCol = curCol + DY[direction];

                    if(checkBound(nextRow, nextCol, n) && !visited[nextRow][nextCol]) {
                        if(lab[nextRow][nextCol] == 0 || lab[nextRow][nextCol] == 2) {
                            lab[nextRow][nextCol] = 9;
                            visited[nextRow][nextCol] = true;
                            queue.add(new Virus(nextRow, nextCol));
                        }
                    }
                }
            }
            // 모든 칸이 활성화 되었는지 확인
            boolean finish = true;
            for(int row = 0; row < n; row ++) {
                for (int col = 0; col < n; col ++) {
                    if(lab[row][col] == 0){
                        finish = false;
                        break;
                    }
                }
                if(!finish) break;
            }
            if(finish) {
                time++;
                second = Math.min(second, time);
                break;
            }
            time++;
        }
    }

    private static boolean checkBound(int nextRow, int nextCol, int n) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n;
    }
}
