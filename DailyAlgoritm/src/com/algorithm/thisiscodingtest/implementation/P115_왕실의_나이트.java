package com.algorithm.thisiscodingtest.implementation;

public class P115_왕실의_나이트 {
    public static void main(String[] args) {
        String position = "a1";
        int count = getPossiblePosition(position);
        System.out.println("count = " + count);
    }

    private static int getPossiblePosition(String position) {
        int rst = 0;
        final int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
        final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        String alpha = "abcdefgh";
        int row = Integer.parseInt(String.valueOf(position.charAt(1)));
        int col = alpha.indexOf(position.charAt(0))+1;
        for (int i = 1; i <= 8; i++) {
            int nx = col + dx[i-1];
            int ny = row + dy[i-1];
            if (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8)
                rst++;
        }
        return rst;
    }
}
