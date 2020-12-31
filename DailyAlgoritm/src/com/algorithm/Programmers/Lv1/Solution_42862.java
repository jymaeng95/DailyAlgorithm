package com.algorithm.Programmers.Lv1;

import java.util.*;

public class Solution_42862 {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2,4};
        int[] reserve = {2,4};
        System.out.println(solution(n,lost,reserve));
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        Arrays.parallelSort(reserve);
        List<Integer> lost_list = new ArrayList<>();
        for(int x : lost)
            lost_list.add(x);


        Set<Integer> set = new TreeSet<>(lost_list);
        for(int i=0;i<reserve.length;i++){
            if(set.contains(reserve[i])) {
                set.remove(reserve[i]);
                reserve[i] = -1;
            }
        }

        int count = n-set.size();
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            int lt = it.next();
            for(int i=0;i<reserve.length;i++){
                if(lt == reserve[i]+1 || lt == reserve[i]-1){
                    count ++;
                    reserve[i] = -1;
                    break;
                }
            }
            it.remove();
        }
        return count;
    }
}
