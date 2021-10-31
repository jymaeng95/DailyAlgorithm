package com.algorithm.thisiscodingtest.implementation;

import java.io.*;
import java.util.StringTokenizer;

public class P118_게임_개발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int rowChar = Integer.parseInt(st.nextToken());
        int colChar = Integer.parseInt(st.nextToken());
        int position = Integer.parseInt(st.nextToken());

        int[][] map = new int[row][col];
        boolean[][] visited = new boolean[row][col];
        for(int i=0;i<map.length;i++){
            st = new StringTokenizer(br.readLine());
            for (int j =0; j< map[i].length;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getPossibleMoveCount(map, rowChar, colChar, position,visited);
        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getPossibleMoveCount(int[][] map, int rowChar, int colChar, int position,boolean[][] visited) {
        int posiCnt = 0;
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        int possible = 1;
        visited[rowChar][colChar] = true;

        while(true) {
            //왼쪽 돌기
            position = turnLeft(position);
            int nx = rowChar + dx[position];
            int ny = colChar + dy[position];

            if(nx >= 0 && nx < map.length && ny >= 0 && ny < map.length && !visited[nx][ny] && map[nx][ny] == 0) {
                visited[nx][ny] = true;
                rowChar = nx;
                colChar = ny;
                posiCnt ++;
                possible = 0;
                continue;
            } else {
                possible++;
            }

            if(possible == 4) {
                nx = rowChar - dx[position];
                ny = colChar - dy[position];
                if(map[nx][ny] == 0) {
                    rowChar = nx;
                    colChar = ny;
                } else {
                    break;
                }
                possible = 0;
            }
        }
        return posiCnt;
    }

    private static int turnLeft(int position) {
        position -= 1;
        if(position == -1){
            return 3;
        }
        return position;
    }


}
