package com.algorithm.SWEA.D3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solutio_5215_햄버거_다이어트_조합 {
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

    private static int totalScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int countIngredient = Integer.parseInt(st.nextToken());
            int limitKcal = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[countIngredient];
            List<Ingredient> ingredientList = new ArrayList<>();
            for (int j = 0; j < countIngredient; j++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int kcal = Integer.parseInt(st.nextToken());
                ingredientList.add(new Ingredient(score, kcal));
            }
            totalScore = 0;
            getScore(ingredientList, limitKcal, 0,0, 0);
            bw.write("#" + i + " " + totalScore);
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void getScore(List<Ingredient> ingredientList, int limitKcal,int sum, int kcal, int index) {
        if (limitKcal < kcal) {
            return;
        }
        if (index == ingredientList.size()) {
            totalScore = Math.max(totalScore, sum);
            return;
        }
        Ingredient ingredient = ingredientList.get(index);
        getScore(ingredientList,limitKcal,sum+ingredient.score,kcal +ingredient.kcal,index+1);
        getScore(ingredientList,limitKcal,sum,kcal,index+1);
    }
}
