package com.algorithm.Programmers.Lv1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_42748 {
    public static void main(String[] args) {
        int[] array = {1,5,2,6,3,7,4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        solution(array,commands);
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0;i< commands.length;i++){
            int start = commands[i][0];
            int end = commands[i][1];
            int index = commands[i][2];

            List<Integer> list = new ArrayList<>();
            for(int j=start-1;j<end;j++){
                list.add(array[j]);
            }
            Collections.sort(list);
            answer[i] = list.get(index-1);
        }

        return answer;
    }
}
