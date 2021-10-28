package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;

public class Main_15235_Olympiad_Pizza {
    static class Constants {
        int index;
        int pizza;

        public Constants(int index, int pizza) {
            this.index = index;
            this.pizza = pizza;
        }
    }
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {

        int person = Integer.parseInt(br.readLine());
        int[] whenConstantEat = getWhenConstantEat(person);

        for(int x : whenConstantEat) {
            bw.write(x +" ");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int[] getWhenConstantEat(int person) throws IOException {
        int[] seconds = new int[person];
        Queue<Constants> queue = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<person; i++) {
            queue.add(new Constants(i, Integer.parseInt(st.nextToken())));
        }

        int second = 0;
        while(!queue.isEmpty()) {
            second++;

            Constants ct = queue.poll();
            ct.pizza--;

            if(ct.pizza != 0 ) {
                queue.add(ct);
                continue;
            }
            seconds[ct.index] = second;
        }

        return seconds;
    }
}
