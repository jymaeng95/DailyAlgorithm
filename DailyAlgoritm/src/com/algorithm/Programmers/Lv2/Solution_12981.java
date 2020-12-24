package com.algorithm.Programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class Solution_12981 {
    public static void main(String[] args) {
        int n = 5;
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        int[] answer = solution(n,words);
    }

    public static int[] solution(int n, String[] words) {
        // 1. 중복 단어 시
        // 2. 끝까지 성공 시
        // 3. 마지막 글자 아닌 경우
        String start = words[0];
        Set<String> set = new HashSet<>();
        set.add(start);
        int index = 0;
        for(int i=1;i<words.length;i++){
            //마지막 글자로 한 경우
            if(start.charAt(start.length()-1) == words[i].charAt(0)){
                if(set.contains(words[i])) {
                    index = i;
                    break;
                }
                set.add(words[i]);
                start = words[i];
                continue;
            }
            index = i;
            break;
        }
        if(index == 0)
            return new int[]{0,0};

        int loop = index / n + 1;
        int order = index % n + 1;
        int [] personIndex = {order, loop};
        System.out.println(personIndex[0]+" "+personIndex[1] );
        return personIndex;
    }
}
