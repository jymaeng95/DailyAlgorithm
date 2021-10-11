package com.algorithm.LeetCode.greedy;

public class Solution_605_Can_Place_Flowers {
    public static void main(String[] args) {
        int[] flowerbed = {0,0,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,1};
        int n = 4;
        boolean ans = canPlaceFlowers(flowerbed, n);
        System.out.println(ans);
    }

    private static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 1;
        int rst = 0;
        for(int x : flowerbed) {
            if(x == 0) {
                count++;
            }else {
                rst += (count - 1) / 2;
                count = 0;
            }
        }

        if(count > 0) {
            rst += count / 2;
        }
        return rst >= n;
    }
}
