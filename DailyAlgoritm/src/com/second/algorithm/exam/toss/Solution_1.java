package com.second.algorithm.exam.toss;

public class Solution_1 {
    public static void main(String[] args) {
        String s = "000";
        int rst = solution(s);
        System.out.println("rst = " + rst);
    }

    private static int solution(String s) {
        int rst = -1;
        for (int index = 0; index <= s.length() - 3; index++) {
            String subString = s.substring(index, index + 3);

            boolean awesome = true;
            char first = subString.charAt(0);
            for (int subIndex = 1; subIndex < 3; subIndex++) {
                 if(subString.charAt(subIndex) != first) {
                     awesome = false;
                     break;
                 }
            }

            if(awesome) rst = Math.max(rst, Integer.parseInt(subString));
        }

        return rst ;
    }
}
