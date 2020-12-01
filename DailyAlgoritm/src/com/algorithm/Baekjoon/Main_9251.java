package com.algorithm.Baekjoon;

import java.io.*;

public class Main_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String firstStr = br.readLine();
        String secondStr = br.readLine();

        int count = LCS(firstStr,secondStr);

        bw.write(String.valueOf(count));

        br.close();
        bw.close();
    }

    private static int LCS(String firstStr, String secondStr) {
        int[][] countBoard = new int[firstStr.length()+1][secondStr.length()+1];
        for(int i=1;i<countBoard.length;i++){
            for(int j=1;j< countBoard[i].length;j++){
                if(firstStr.charAt(i-1) == secondStr.charAt(j-1)) {
                    countBoard[i][j] = countBoard[i - 1][j - 1] + 1;
                    continue;
                }
                countBoard[i][j] = Math.max(countBoard[i-1][j],countBoard[i][j-1]);
            }
        }

        return countBoard[firstStr.length()][secondStr.length()];
    }

}
