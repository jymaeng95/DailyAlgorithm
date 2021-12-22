package com.algorithm.Programmers.Lv1;

public class Solution_67256_키패드_누르기 {
    public static void main(String[] args) {
        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";
        String rst = solution(numbers, hand);
        System.out.println("rst = " + rst);
    }

    private static String solution(int[] numbers, String hand) {
        int left = 10;
        int right = 12;
        //0 = 11번째 위치
        StringBuilder sb = new StringBuilder();
        for (int x : numbers) {
            if (x == 1 || x == 4 || x == 7) {
                left = x;
                sb.append("L");
            } else if (x == 3 || x == 6 || x == 9) {
                right = x;
                sb.append("R");
            } else {
                if(x==0) x = 11;
                int leftDist = Math.abs(x - left) / 3 + Math.abs(x - left) % 3;
                int rightDist = Math.abs(x - right) / 3 + Math.abs(x - right) % 3;
                if(leftDist < rightDist) {
                    left = x;
                    sb.append("L");
                }
                else if(rightDist < leftDist) {
                    right = x;
                    sb.append("R");
                }
                else {
                    if(hand.equals("right")) {
                        right = x;
                        sb.append("R");
                    }
                    else {
                        left = x;
                        sb.append("L");
                    }
                }
            }
        }
        return sb.toString();
    }
}
