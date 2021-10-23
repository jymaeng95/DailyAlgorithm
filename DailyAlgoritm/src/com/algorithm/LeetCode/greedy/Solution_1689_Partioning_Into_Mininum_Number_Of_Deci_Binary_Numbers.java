package com.algorithm.LeetCode.greedy;

public class Solution_1689_Partioning_Into_Mininum_Number_Of_Deci_Binary_Numbers {
    public static void main(String[] args) {
        String n = "27346209830709182346";
        int rst = minPartitions(n);
        System.out.println("rst = " + rst);
    }

    private static int minPartitions(String n) {
        /*
        String[] split = n.split("");
        Arrays.parallelSort(split);
        return Integer.parseInt(split[n.length()-1]);
        */
        int max = 0;
        for(char c : n.toCharArray()) {
            max = Math.max(max, c-'0');
            if(max == 9)
                break;
        }
        return max;
    }
}
