package com.algorithm.LeetCode.greedy;

public class Solution_1323_Maximum_69_Number {
    public static void main(String[] args) {
        int num = 9669;
        int answer = maximum69Number(num);
        System.out.println(answer);
    }

    private static int maximum69Number (int num) {
        String number = Integer.toString(num);
        boolean change = false;
        StringBuilder sb = new StringBuilder();
        for(char c : number.toCharArray()) {
            if(c == '6' && !change){
                sb.append('9');
                change = true;
                continue;
            }
            sb.append(c);
        }

        return Integer.parseInt(sb.toString());
    }
}

