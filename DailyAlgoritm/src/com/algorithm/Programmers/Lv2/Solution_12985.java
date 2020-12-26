package com.algorithm.Programmers.Lv2;

public class Solution_12985 {
    public static void main(String[] args) {
        int n = 8;
        int a = 10;
        int b = 16;
        System.out.println(solution(n,a,b));
    }

    public static int solution(int n, int a, int b) {
//        List<Integer> list = new ArrayList<>();
//        for(int i=1;i<=n;i++)
//            list.add(i);
//
//        boolean flag = false;
//

        int round = 1;
        if(a > b){
            int temp = a;
            a = b;
            b = temp;
        }
        int indexA = (a-1) / 2;
        int indexB = (b-1) / 2;

        while (indexA != indexB) {
            round++;
            indexA /= 2;
            indexB /= 2;
        }
        return round;
    }
}
