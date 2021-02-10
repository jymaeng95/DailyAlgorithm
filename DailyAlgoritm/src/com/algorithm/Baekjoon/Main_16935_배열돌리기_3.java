package com.algorithm.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16935_배열돌리기_3 {
    static int N,M,R;
    static int[][] map;
    static int[][] temp;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<R; i++) {
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    topReverse();
                    break;
                case 2:
                    leftReverse();
                    break;
                case 3:
                    rightRot();
                    break;
                case 4:
                    leftRot();
                    break;
                case 5:
                    group();
                    break;
                case 6:
                    groupReverse();
                    break;
            }
        }
        print();
        br.close();
        bw.close();
    }
    private static void groupReverse() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<4; i++) {
            list.add(i);
        }
        list.add(3, list.remove(0));
        grouping(list);
    }
    private static void group() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<4; i++) {
            list.add(i);
        }
        list.add(0, list.remove(3));
        grouping(list);
    }
    private static void grouping(ArrayList<Integer> list) {
        int[][][] g = new int[4][map.length/2][map[0].length/2];
        for(int j=0; j<map.length/2; j++) {
            for(int k=0; k<map[0].length/2; k++) {
                g[0][j][k] = map[j][k];
            }
        }
        for(int j=0; j<map.length/2; j++) {
            for(int k=map[0].length/2; k<map[0].length; k++) {
                g[1][j][k-map[0].length/2] = map[j][k];
            }
        }
        for(int j=map.length/2; j<map.length; j++) {
            for(int k=map[0].length/2; k<map[0].length; k++) {
                g[2][j-map.length/2][k-map[0].length/2] = map[j][k];
            }
        }
        for(int j=map.length/2; j<map.length; j++) {
            for(int k=0; k<map[0].length/2; k++) {
                g[3][j-map.length/2][k] = map[j][k];
            }
        }
        for(int j=0; j<map.length/2; j++) {
            for(int k=0; k<map[0].length/2; k++) {
                map[j][k] = g[list.get(0)][j][k];
            }
        }
        for(int j=0; j<map.length/2; j++) {
            for(int k=map[0].length/2; k<map[0].length; k++) {
                map[j][k] = g[list.get(1)][j][k-map[0].length/2];
            }
        }
        for(int j=map.length/2; j<map.length; j++) {
            for(int k=map[0].length/2; k<map[0].length; k++) {
                map[j][k] = g[list.get(2)][j-map.length/2][k-map[0].length/2];
            }
        }
        for(int j=map.length/2; j<map.length; j++) {
            for(int k=0; k<map[0].length/2; k++) {
                map[j][k] = g[list.get(3)][j-map.length/2][k];
            }
        }
    }
    private static void leftRot() {
        left();
    }
    private static void left() {
        int n = map.length;
        int m = map[0].length;
        temp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = map[j][m-1-i];
            }
        }
        map = temp;
    }
    private static void rightRot() {
        right();
    }
    private static void right() {
        int n = map.length;
        int m = map[0].length;
        temp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = map[n-1-j][i];
            }
        }
        map = temp;
    }
    private static void leftReverse() {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length/2; j++) {
                int temp = map[i][j];
                map[i][j] = map[i][map[0].length-1-j];
                map[i][map[0].length-1-j] = temp;
            }
        }
    }
    private static void topReverse() {
        for(int j=0; j<map[0].length; j++) {
            for(int i=0; i<map.length/2; i++) {
                int temp = map[i][j];
                map[i][j] = map[map.length-1-i][j];
                map[map.length-1-i][j] = temp;
            }
        }
    }
    private static void print() throws IOException {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                bw.write(map[i][j]+" ");
            }
            bw.newLine();
        }
    }
}