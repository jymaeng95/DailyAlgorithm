package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683_감시 {
    static class Camera {
        private int type;
        private int row;
        private int col;

        public Camera(int type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }

        public int getType() {
            return type;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private static List<Camera> cameraList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        cameraList = new ArrayList<>();

        int[][] office = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int input = Integer.parseInt(st.nextToken());
                if (input > 0 && input < 6) {
                    cameraList.add(new Camera(input, row, col));
                }
                office[row][col] = input;
            }
        }

        int rst = getBlindSpot(N, M, office);
        System.out.println(rst);

        br.close();
    }

    private static int getBlindSpot(int n, int m, int[][] office) {
        blindSpot = Integer.MAX_VALUE;
        makeBlindSpot(0, n, m, office);
        return blindSpot;
    }

    private static int blindSpot;

    private static void makeBlindSpot(int index, int n, int m, int[][] office) {
        if (index == cameraList.size()) {
            int spot = 0;

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    if (office[row][col] == 0) {
                        spot++;
                    }
                }
            }
            blindSpot = Math.min(blindSpot, spot);
            return;
        }

        Camera camera = cameraList.get(index);
        if (camera.getType() == 1) checkSpotOne(camera, index, n, m, office);
        if (camera.getType() == 2) checkSpotTwo(camera, index, n, m, office);
        if (camera.getType() == 3) checkSpotThree(camera, index, n, m, office);
        if (camera.getType() == 4) checkSpotFour(camera, index, n, m, office);
        if (camera.getType() == 5)checkSpotFive(camera, index, n, m, office);
    }

    private static void checkSpotFive(Camera camera, int index, int n, int m, int[][] office) {
        int[][] originalOffice = copyOffice(office, n, m);
        checkDown(camera, n, office);
        checkUp(camera, office);
        checkRight(camera, m, office);
        checkLeft(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);
    }

    private static void checkSpotFour(Camera camera, int index, int n, int m, int[][] office) {
        int[][] originalOffice = copyOffice(office, n, m);
        // 상,하,우
        checkUp(camera, office);
        checkDown(camera, n, office);
        checkRight(camera, m, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 상,좌,우
        checkUp(camera, office);
        checkLeft(camera, office);
        checkRight(camera, m, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 하,좌,우
        checkDown(camera, n, office);
        checkLeft(camera, office);
        checkRight(camera, m, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 상,하,좌
        checkUp(camera, office);
        checkDown(camera, n, office);
        checkLeft(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);
    }

    private static void checkUp(Camera camera, int[][] office) {
        for (int row = camera.getRow() - 1; row >= 0; row--) {
            if (office[row][camera.getCol()] == 6) break;
            office[row][camera.getCol()] = 9;
        }
    }

    private static void checkSpotThree(Camera camera, int index, int n, int m, int[][] office) {
        int[][] originalOffice = copyOffice(office, n, m);
        // 상,우
        checkUp(camera, office);
        checkRight(camera, m, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 하, 우
        checkDown(camera, n, office);
        checkRight(camera, m, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 상, 좌
        checkUp(camera, office);
        checkLeft(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 하, 좌
        checkDown(camera, n, office);
        checkLeft(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);
    }

    private static void checkSpotTwo(Camera camera, int index, int n, int m, int[][] office) {
        int[][] originalOffice = copyOffice(office, n, m);
        // 상하
        checkDown(camera, n, office);
        checkUp(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 좌우
        checkRight(camera, m, office);
        checkLeft(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);
    }

    private static void checkSpotOne(Camera camera, int index, int n, int m, int[][] office) {
        int[][] originalOffice = copyOffice(office, n, m);
        // 하
        checkDown(camera, n, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 상
        checkUp(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 우
        checkRight(camera, m, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);

        // 좌
        checkLeft(camera, office);
        makeBlindSpot(index + 1, n, m, office);
        office = copyOffice(originalOffice, n, m);
    }

    private static void checkLeft(Camera camera, int[][] office) {
        for (int col = camera.getCol() - 1; col >= 0; col--) {
            if (office[camera.getRow()][col] == 6) break;
            office[camera.getRow()][col] = 9;
        }
    }

    private static void checkRight(Camera camera, int m, int[][] office) {
        for (int col = camera.getCol() + 1; col < m; col++) {
            if (office[camera.getRow()][col] == 6) break;
            office[camera.getRow()][col] = 9;
        }
    }

    private static void checkDown(Camera camera, int n, int[][] office) {
        for (int row = camera.getRow() + 1; row < n; row++) {
            if (office[row][camera.getCol()] == 6) break;
            office[row][camera.getCol()] = 9;
        }
    }

    private static int[][] copyOffice(int[][] office, int n, int m) {
        int[][] copyOffice = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                copyOffice[row][col] = office[row][col];
            }
        }

        return copyOffice;
    }
}
