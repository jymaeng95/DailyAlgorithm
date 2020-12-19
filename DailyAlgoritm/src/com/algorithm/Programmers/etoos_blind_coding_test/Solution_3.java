//package com.algorithm.Programmers.etoos_blind_coding_test;
//public class Solution_3 {
//    public static void main(String[] args) {
//        int[] s1 = {1,2,3,0,6,5,4};
//        int[] s2 = {1,2,3,4,5,6,0};
//        solution(s1,s2);
//    }
//    private static int[][] attatch = {{1,5,6},{0,2,6},{1,3,6},{2,4,6},{3,5,6},{0,4,6}};
//    public static int solution(int[] s1, int[] s2) {
//        int count = 0;
//        int index = 0;
//        for(int i=0;i<s1.length;i++){
//            if(s1[i]==0) {
//                index = i;
//                break;
//            }
//        }
//
//        count = dfs(s1,s2,index,0);
//        return count;
//
//    }
//
//    private static int dfs(int[] s1, int[] s2, int index, int count) {
//        int same = 0;
//        boolean[] visited = new boolean[7];
//        for(int i=0;i<s1.length;i++) {
//            if(s1[i]==s2[i])
//                visited[i] = true;
//        }
//
//        return 0;
//    }
//}
