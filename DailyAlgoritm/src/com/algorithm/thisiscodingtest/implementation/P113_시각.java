package com.algorithm.thisiscodingtest.implementation;

public class P113_시각 {
    public static void main(String[] args) {
        int hour = 5;
        int countThree = getCountThree(hour);
        System.out.println("countThree = " + countThree);
    }

    private static int getCountThree(int hour) {
        int count = 0;
        for(int i=0; i<=hour; i++) {
            for(int j=0; j<60; j++) {
                for(int k=0; k<60; k++) {
                    StringBuilder time = new StringBuilder();
                    time.append(i);
                    time.append(j);
                    time.append(k);
                    if(time.toString().contains("3"))
                        count++;
                }
            }
        }
        return count;
    }
}
