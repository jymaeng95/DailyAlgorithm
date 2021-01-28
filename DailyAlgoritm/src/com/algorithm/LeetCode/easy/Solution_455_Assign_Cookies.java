package com.algorithm.LeetCode.easy;

import java.util.Arrays;

public class Solution_455_Assign_Cookies {
    public static void main(String[] args) {
        int[] g = {5,4,1,2,6};
        int[] s = {4,6,1};
        System.out.println(findContentChildren(g,s));
    }
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.parallelSort(g);
        Arrays.parallelSort(s);
        int child = 0;
        int i = 0;
        for(int cookieSize : s){
            if(cookieSize >= g[i]){
                child++;
                i++;
            }
            if(i >= g.length)
                break;
        }
        return child;
    }
}
