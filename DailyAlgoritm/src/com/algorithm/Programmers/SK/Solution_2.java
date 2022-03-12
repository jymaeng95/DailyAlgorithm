package com.algorithm.Programmers.SK;

public class Solution_2 {
    public static void main(String[] args) {
        int n = 9;
        boolean clockwise = false;
        int[][] rst =       {{1,1,1,1,1,1,1,1,2},
                            {4,4,4,4,4,4,4,1,2},
                            {4,3,3,3,3,3,4,1,2},
                            {4,3,2,2,2,3,4,1,2},
                            {4,3,2,1,5,3,4,1,2},
                            {4,3,2,1,4,4,4,1,2},
                            {4,3,2,1,1,1,1,1,2},
                            {4,3,2,2,2,2,2,2,2},
                            {4,3,3,3,3,3,3,3,3}
                            };
        int[][] solution = solution(n,clockwise);
        System.out.println("solution[0][0] = " + solution[0][0]);
    }

    private static int start, end, length,yeoback;
    private static int[][] solution(int n, boolean clockwise) {
        int[] startRow = {0,0,n-1,n-1};
        int[] startCol = {0,n-1,n-1,0};
        int[][] arr = new int[n][n];
        end = n * n / 4;
        if(clockwise) {
            clockwisePaint(startRow,startCol, arr,n);
        }
        else {
            reverseClockwisePaint(startRow,startCol, arr, n);
        }
        if(n%2!=0) {
            arr[n/2][n/2] = end +1;
        }
        return arr;
    }

    private static void reverseClockwisePaint(int[] startRow, int[] startCol, int[][] arr, int n) {
        int[] startDir = {0,3,2,1};
        for(int loop =0; loop < 4; loop++) {
            int row = startRow[loop];
            int col = startCol[loop];
            int dir = startDir[loop];
            start = 1;
            yeoback = 1;
            length = 0;
            while(start <= end) {
                arr[row][col] = start;
                // 하
                if(dir == 0) {
                    if(n -1- length == yeoback) {
                        dir = rotate(dir);
                        col++;
                        continue;
                    }
                    start++; row++; length++;
                }
                // 우
                else if(dir == 1){
                    if(n-1-length == yeoback) {
                        dir = rotate(dir);
                        row--;
                        continue;
                    }
                    start++; col++; length++;
                }
                // 상
                else if(dir ==2) {
                    if(n-1-length == yeoback) {
                        dir = rotate(dir);
                        col--;
                        continue;
                    }
                    start++; row--;length++;
                }
                // 좌
                else  {
                    if(n-1-length == yeoback) {
                        dir = rotate(dir);
                        row++;
                        continue;
                    }
                    start++; col--; length++;
                }
            }
        }
    }

    private static void clockwisePaint(int[] startRow, int[] startCol, int[][] arr, int n) {
        int[] startDir = {0,1,2,3};
        for(int loop =0; loop < 4; loop++) {
            int row = startRow[loop];
            int col = startCol[loop];
            int dir = startDir[loop];
            start = 1;
            yeoback = 1;
            length = 0;
            while(start <= end) {
                arr[row][col] = start;
                // 우
                if(dir == 0) {
                    if(n -1- length == yeoback) {
                        dir = rotate(dir);
                        row++;
                        continue;
                    }
                    start++; col++; length++;
                }
                // 하
                else if(dir == 1){
                    if(n-1-length == yeoback) {
                        dir = rotate(dir);
                        col--;
                        continue;
                    }
                    start++; row++; length++;
                }
                // 좌
                else if(dir ==2) {
                    if(n-1-length == yeoback) {
                        dir = rotate(dir);
                        row--;
                        continue;
                    }
                    start++; col--;length++;
                }
                // 상
                else  {
                    if(n-1-length == yeoback) {
                        dir = rotate(dir);
                        col++;
                        continue;
                    }
                    start++; row --;length++;
                }
            }
        }
    }

    private static int rotate(int dir) {
        length = yeoback;
        yeoback++;
        start++;
        return dir == 3 ? 0 : dir+1;
    }
}
