package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//백준 빵집 Gold II
public class Main_3109 {
    private static int R,C;
    private static final int[] DX = {-1,0,1};
    private static boolean end = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        String[][] gas = new String[R][C];

        int count = 0;
        for(int i=0;i<R;i++){
            String[] s = br.readLine().split("");
            for(int j=0;j<C;j++){
                gas[i][j] = s[j];
            }
        }

        for(int i=0;i<R;i++){
            count += dfs(gas,i);
        }

        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }

    private static int dfs(String[][] gas, int i) {
        end = false;
        boolean result = dfsUtil(gas,i,0);
        return result ? 1 : 0;
    }
    //대각선 위부터 차례로 아래 탐색
    private static boolean dfsUtil(String[][] gas, int x, int y) {
        //끝부분까지 제대로 도착한 경우
        if(y==C-1 && gas[x][y].equals("."))
            end = true;

        //파이프 설치 후 gas 배열에 방문 처리
        gas[x][y] = "x";
        for (int j : DX) {
            int nx = x + j;
            int ny = y + 1;
            if (ny < C && nx >= 0 && nx < R && !gas[nx][ny].equals("x")) {
                //끝부분 도착 경우에만 for문 탈출
                if (dfsUtil(gas, nx, ny))
                    break;
            }
        }
        return end;
    }
}
