package com.algorithm.Programmers.Lv2;

public class Solution_1835_단체사진_찍기 {
    public static void main(String[] args) {
        String[] data = {"N~F=0", "R~T>2"};
        int n = 2;
        int rst = solution(n,data);
        System.out.println("rst = " + rst);
    }

    private static final char[] NAME = {'A','C','F','J','M','N','R','T'};
    private static boolean[] visited;
    private static int count;
    private static int solution(int n, String[] data) {
        visited = new boolean[8];
        count = 0;
        char[] position = new char[8];
        setPosition(0,position,n,data);
        return count;
    }

    private static void setPosition(int depth, char[] position, int n, String[] data) {
        if(depth == 8) {
            // data 조건 판단 맞으면 count++
            if(checkPosition(n,data,position)) count++;
            return;
        }
        for(int index = 0; index < 8; index++) {
            if(!visited[index]) {
                visited[index] = true;
                position[depth] = NAME[index];
                setPosition(depth + 1, position, n, data);
                visited[index] = false;
            }
        }
    }

    private static boolean checkPosition(int n, String[] datas, char[] position) {
        for(String data : datas) {
            char[] dataArr = data.toCharArray();

            char friendA = dataArr[0];
            char friendB = dataArr[2];
            char opr = dataArr[3];
            char midFriend = dataArr[4];
            int friendAIndex = 0;
            int friendBIndex = 0;

            for(int index = 0; index < 8; index++) {
                if(friendA == position[index]) friendAIndex = index;
                else if(friendB == position[index]) friendBIndex = index;
            }

            if(opr == '=') {
                if(Math.abs(friendAIndex - friendBIndex) - 1 != Integer.parseInt(String.valueOf(midFriend))) return false;
            }
            else if(opr == '>') {
                if(Math.abs(friendAIndex - friendBIndex) - 1 <= Integer.parseInt(String.valueOf(midFriend))) return false;
            }
            else {
                if(Math.abs(friendAIndex - friendBIndex) - 1 >= Integer.parseInt(String.valueOf(midFriend))) return false;
            }
        }

        return true;
    }
}
