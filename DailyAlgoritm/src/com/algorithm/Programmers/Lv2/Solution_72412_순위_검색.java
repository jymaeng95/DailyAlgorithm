package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class Solution_72412_순위_검색 {
    static class Developer {
        private String lang;
        private String position;
        private String career;
        private String soulFood;
        int score;

        public Developer(String lang, String position, String career, String soulFood, int score) {
            this.lang = lang;
            this.position = position;
            this.career = career;
            this.soulFood = soulFood;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};

        int[] solution = solution(info, query);
        for (int x : solution) {
            System.out.println("x = " + x);
        }

    }

    public static int[] solution(String[] info, String[] query) {
//        int[] answer = new int[query.length];
        List<Integer> answer = new ArrayList<>();
        List<Developer> developerList = new ArrayList<>();
        for (String information : info) {
            String[] split = information.split(" ");
            String lang = split[0];
            String position = split[1];
            String career = split[2];
            String soulFood = split[3];
            int score = Integer.parseInt(split[4]);

            developerList.add(new Developer(lang, position, career, soulFood, score));
        }

        for (int i=0;i<query.length;i++) {
            int count = 0;
            String[] split = query[i].split(" ");
            String reqLang = split[0];
            String reqPos = split[2];
            String reqCareer = split[4];
            String reqSoulFood = split[6];
            int reqScore = Integer.parseInt(split[7]);

            for(Developer dev : developerList) {
                if(!reqLang.equals("-") && !reqLang.equals(dev.lang)) continue;
                if(!reqPos.equals("-") && !reqPos.equals(dev.position))  continue;
                if(!reqCareer.equals("-") && !reqCareer.equals(dev.career)) continue;
                if(!reqSoulFood.equals("-") && !reqSoulFood.equals(dev.soulFood)) continue;
                if(reqScore <= dev.score)
                    count++;
            }

            answer.add(count);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
