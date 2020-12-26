package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class Solution_17677 {
    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        System.out.println(solution(str1,str2));
    }

    public static int solution(String str1, String str2) {
        String[] splitFirst = str1.split("");
        String[] splitSecond = str2.split("");
        List<String> first  = new ArrayList<>();
        List<String> second = new ArrayList<>();

        for(int i=0;i<splitFirst.length-1;i++){
            if(isAlpha(splitFirst[i]) && isAlpha(splitFirst[i+1]))
                first.add((splitFirst[i]+splitFirst[i+1]).toUpperCase());
        }

        for(int i=0;i<splitSecond.length-1;i++){
            if(isAlpha(splitSecond[i]) && isAlpha(splitSecond[i+1]))
                second.add((splitSecond[i]+splitSecond[i+1]).toUpperCase());
        }

        if(first.isEmpty() && second.isEmpty())
            return 65536;

        int setSize = first.size() + second.size();
        int count = 0;

        for(String s : first) {
            if(second.contains(s)){
                second.remove(s);
                count++;
            }
        }
        return (int) Math.floor((double)count / (double)(setSize-count) * 65536);
    }

    private static boolean isAlpha(String c) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return alpha.contains(c);
    }
}
