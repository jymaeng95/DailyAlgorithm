package com.algorithm.Programmers.Lv1;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Solution_68644 {
    public static void main(String[] args) {
        int[] numbers = {2,1,3,4,1};
        solution(numbers);
    }
    public static int[] solution(int[] numbers) {
        Set<Integer> sumSet = new TreeSet<>();
        for(int i=0;i<numbers.length;i++){
            for(int j=i;j<numbers.length;j++){
                sumSet.add(numbers[i] + numbers[j]);
            }
        }
        int[] answer = new int[sumSet.size()];
        Iterator<Integer> it = sumSet.iterator();
        int i=0;
        while(it.hasNext()){
            answer[i] = it.next();
            it.remove();
            i++;
        }
        return answer;
    }
}
