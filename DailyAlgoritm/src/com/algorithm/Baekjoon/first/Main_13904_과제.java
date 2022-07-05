package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_13904_과제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Homework> homeworkList = new ArrayList<>();
        int finishDay = 0;
        for (int homework = 0; homework < N; homework++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int remainDay = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            finishDay = Math.max(finishDay, remainDay);
            homeworkList.add(new Homework(remainDay, score));
        }
        int rst = getMaxScore(homeworkList, finishDay);
        System.out.println(rst);

        br.close();
    }

    private static int getMaxScore(List<Homework> homeworkList, int finishDay) {
        int score = 0;
        while(finishDay >= 1 ) {
            int removeIndex = 0;
            int maxScore = 0;
            boolean remove = false;
            for(int index = 0; index < homeworkList.size(); index++) {
                if (finishDay <= homeworkList.get(index).getRemainDay() && maxScore < homeworkList.get(index).getScore()) {
                    removeIndex = index;
                    maxScore = homeworkList.get(index).getScore();
                    remove = true;
                }
            }

            if(remove)  homeworkList.remove(removeIndex);
            score += maxScore;

            finishDay--;
        }

        return score;
    }

    static class Homework implements Comparable<Homework> {
        private int remainDay;
        private int score;

        public Homework(int remainDay, int score) {
            this.remainDay = remainDay;
            this.score = score;
        }

        public int getRemainDay() {
            return remainDay;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Homework o) {
            return Integer.compare(this.remainDay, o.remainDay);
        }
    }
}
