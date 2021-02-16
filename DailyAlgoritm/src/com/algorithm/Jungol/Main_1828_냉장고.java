package com.algorithm.Jungol;

import java.io.*;
import java.util.*;

public class Main_1828_냉장고 {
    private static int N;

    static class Fridge {
        private int minTemp;
        private int maxTemp;

        public Fridge(int minTemp, int maxTemp) {
            this.minTemp = minTemp;
            this.maxTemp = maxTemp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Fridge> list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int minTemp = Integer.parseInt(st.nextToken());
            int maxTemp = Integer.parseInt(st.nextToken());

            list.add(new Fridge(minTemp, maxTemp));
        }

        list.sort((Comparator.comparingInt(o -> o.maxTemp)));
        int count = 1;
        int i =1;
        Fridge fridge = list.get(0);
        while(i<N){
            Fridge remain = list.get(i);
            if(remain.minTemp > fridge.maxTemp){
                fridge = remain;
                count++;
            }
            i++;
        }

        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }
}
