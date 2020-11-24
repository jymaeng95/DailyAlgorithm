package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_42747 {
    public static void main(String[] args) {
        int[] citations = {0,1,1}; // 4
        System.out.println(solution(citations));
    }

    public static int solution(int[] citations) {
        int answer = 1;

        List<Integer> cit = new ArrayList<>();
        for(int ct : citations)
            cit.add(ct);
        Collections.sort(cit,Collections.reverseOrder());

        if(cit.get(0).equals(0))
            return 0;

        for(int i = 0; i < cit.size(); i++){
            if(answer >= cit.get(i))
                break;
            answer = i+1;
        }

        return answer;
    }
}
