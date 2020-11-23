package com.algorithm.Programmers.wooah_tech_course_2nd;

public class Solution_1 {
    public static void main(String[] args) {
        for(int count : solution(50237))
            System.out.print(count+" ");
    }

    public static int[] solution(int money) {
        int[] count = new int[9];
        int[] change = {50000,10000,5000,1000,500,100,50,10,1};
        for(int i=0;i< change.length;i++){
            count[i] = money / change[i];
            money %= change[i];
        }
        return count;
    }
}
