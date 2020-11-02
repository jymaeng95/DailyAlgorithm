package com.algorithm.Programmers.Lv1;

import java.util.HashSet;
import java.util.Set;

public class Solution_68644 {
    public static void main(String[] args) {
        int[] numbers = {2,1,3,4,1};
        solution(numbers);
    }
    public static int[] solution(int[] numbers) {
        int[] answer = {};
        int sum = 0;
        Set<Integer> numSet = new HashSet<>();
        Set<Integer> sumSet = new HashSet<>();
        for(int num : numbers){
            numSet.add(num);
        }
        for(int i=0;i< numSet.size();i++){
            for(int j=i;j< numSet.size();j++){
                System.out.println(sum);
                sumSet.add(sum);
            }
        }
        System.out.println(sumSet.toString());
        return answer;
    }
}
