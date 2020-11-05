package com.algorithm.Programmers.wooah_tech_course_2;

public class Solution_4 {
    public static void main(String[] args) {
        int[] pobi ={100,101};
        int[] crong = {211,212};
        System.out.println(solution(pobi,crong));
    }
    public static int solution(int[] pobi, int[] crong){
        if(!isCorrect(pobi,crong))
            return -1;
        int maxPobi = Math.max(sum(pobi),multiple(pobi));
        int maxCrong = Math.max(sum(crong),multiple(crong));
        if(maxPobi >  maxCrong)
            return 1;
        if(maxPobi < maxCrong)
            return 2;
        return 0;
    }

    private static int sum(int[] arr) {
        String[] left = Integer.toString(arr[0]).split("");
        String[] right = Integer.toString(arr[1]).split("");
        int leftMul= 0;
        int rightMul = 0;
        for(String l : left){
            leftMul += Integer.parseInt(l);
        }
        for(String r: right){
            rightMul += Integer.parseInt(r);
        }
        return Math.max(leftMul,rightMul);
    }

    private static int multiple(int[] arr) {
        String[] left = Integer.toString(arr[0]).split("");
        String[] right = Integer.toString(arr[1]).split("");
        int leftMul= 1;
        int rightMul = 1;
        for(String l : left){
            leftMul *= Integer.parseInt(l);
        }
        for(String r: right){
            rightMul *= Integer.parseInt(r);
        }
        return Math.max(leftMul,rightMul);
    }

    public static boolean isCorrect(int[] pobi, int[] crong){
        if(pobi[0]+1 == pobi[1] && crong[0]+1 ==  crong[1]){
            if(pobi[0] % 2 != 0 && crong[0] % 2 != 0)
                return true;
            return false;
        }
        return false;
    }
}
