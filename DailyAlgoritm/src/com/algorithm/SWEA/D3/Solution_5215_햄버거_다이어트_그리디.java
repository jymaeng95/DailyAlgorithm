package com.algorithm.SWEA.D3;

import java.io.*;
import java.util.*;

public class Solution_5215_햄버거_다이어트_그리디 {

    static class Ingredient implements Comparable<Ingredient> {
        private int score;
        private int kcal;

        public Ingredient(int score, int kcal) {
            this.score = score;
            this.kcal = kcal;
        }

        @Override
        public int compareTo(Ingredient o) {

            return Double.compare(((double) o.score / o.kcal), ((double) this.score / this.kcal));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int countIngredient = Integer.parseInt(st.nextToken());
            int limitKcal = Integer.parseInt(st.nextToken());

            PriorityQueue<Ingredient> pq = new PriorityQueue<>();
            for(int j  =0;j<countIngredient;j++){
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int kcal = Integer.parseInt(st.nextToken());
                pq.offer(new Ingredient(score,kcal));
            }
            int totalScore = getScore(pq,limitKcal);

            bw.write("#" + i + " "+totalScore);
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static int getScore(PriorityQueue<Ingredient> pq, int limitKcal) {
        int totalScore = 0;
        while(!pq.isEmpty()) {
            Ingredient ingredient = pq.poll();
            if(limitKcal - ingredient.kcal < 0)
                continue;
            limitKcal -= ingredient.kcal;
            totalScore += ingredient.score;
        }
        return totalScore;
    }
}
