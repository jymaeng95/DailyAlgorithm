package com.algorithm.thisiscodingtest.sort;

import java.util.*;

public class P361_Q25_실패율 {
    public static void main(String[] args) {
        int N = 4;
        int[] stages = {4,4,4,4,4};
        int[] rst = solution(N,stages);
    }

    private static int[] solution(int N, int[] stages) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] rst = new int[N];
        int challenger = stages.length;

        for(int s : stages) {
            if(map.containsKey(s)) map.replace(s,map.get(s)+1);
            else map.put(s,1);
        }

        Map<Integer, Double> fail = new HashMap<>();
        for(int i=1;i<=N;i++) {
            if(challenger == 0 || !map.containsKey(i)) {
                fail.put(i,0.0);
                continue;
            }
            double rate = (double) map.get(i)/ challenger;
            fail.put(i,rate);
            challenger -= map.get(i);
        }

        List<Map.Entry<Integer, Double>> list = new ArrayList<>(fail.entrySet());


        list.sort(((o1, o2) -> Double.compare(o2.getValue(), o1.getValue())));
        for(int i=0;i<N;i++) {
            rst[i] = list.get(i).getKey();
        }
        return rst;
    }
}
