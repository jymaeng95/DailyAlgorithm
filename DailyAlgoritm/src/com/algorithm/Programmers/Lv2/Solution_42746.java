package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_42746 {
    public static void main(String[] args) {
        int[] numbers = {0,0,0,0,0};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {
        String answer = "";
        List<String> list = new ArrayList<>();
        for(int num : numbers)
            list.add(String.valueOf(num));

        Collections.sort(list,(o1,o2)-> (o2+o1).compareTo(o1+o2));

        for(String str : list)
            answer += str;

        if(answer.startsWith("0"))
            return "0";
        return answer;
    }

}
