package com.algorithm.thisiscodingtest.implementation;

public class P110_상하좌우 {
    public static void main(String[] args) {
        int n = 5;
        String[] cmd = {"R", "R","R","U","D","D"};
        int[] position = getPosition(n, cmd);
        System.out.println(position[0]+" "+position[1]);
    }

    private static int[] getPosition(int n, String[] cmd) {
        int[] point = new int[2];

        int x = 1;
        int y = 1;

        int i = 0;
        while(i < cmd.length) {
            if(cmd[i].equals("R") && x != n) x++;
            else if(cmd[i].equals("U") && y != 1) y--;
            else if(cmd[i].equals("L") && x != 1) x--;
            else if(cmd[i].equals("D") && y != n) y++;
            i++;
        }
        point[0] = y;
        point[1] = x;
        return point;
    }
}
