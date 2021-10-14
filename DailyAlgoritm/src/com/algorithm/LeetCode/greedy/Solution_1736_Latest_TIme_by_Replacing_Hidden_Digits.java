package com.algorithm.LeetCode.greedy;

public class Solution_1736_Latest_TIme_by_Replacing_Hidden_Digits {
    public static void main(String[] args) {
        String time = "17:32";
        String rst = maximumTime(time);
        System.out.println(rst);
    }

    private static String maximumTime(String time) {
        String[] split = time.split(":");
        String hh = split[0];
        String mm = split[1];

        if(hh.charAt(0) == '?' && hh.charAt(1) == '?') {
            hh = "23";
        }else if(hh.charAt(1) == '?') {
            if(hh.charAt(0) > '1') hh = "23";
            else hh = String.valueOf(hh.charAt(0)) + 9;
        }else if(hh.charAt(0) == '?') {
            if(hh.charAt(1) > '3') hh = 1 + String.valueOf(hh.charAt(1));
            else hh = 2 + String.valueOf(hh.charAt(1));
        }

        if(mm.charAt(0) == '?' && mm.charAt(1) == '?') {
            mm = "59";
        }else if(mm.charAt(1) == '?')  {
            mm = String.valueOf(mm.charAt(0)) + 9;
        }else if(mm.charAt(0) == '?')
            mm = 5 + String.valueOf(mm.charAt(1));
        return hh + ":" + mm;
    }
}
