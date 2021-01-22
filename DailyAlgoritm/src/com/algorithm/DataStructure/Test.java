package com.algorithm.DataStructure;

public class Test {
    public static void main(String[] args) {
        String[]  arr = {"1","X","X","10","X","7","9","11","7"};
        int sum =0;
        int max=Integer.parseInt(arr[0]),min=Integer.parseInt(arr[0]);
        for (String s : arr) {
            if (s.contains("X"))
                continue;
            max = Math.max(max, Integer.parseInt(s));
            min = Math.min(min, Integer.parseInt(s));
        }
        System.out.println(max);
        System.out.println(min);
        for(int i=0;i<20;i++){
            System.out.println((Math.random() * (max - min))+min);
        }
    }
}
