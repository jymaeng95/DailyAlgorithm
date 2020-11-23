package com.algorithm.Programmers.wooah_tech_course_3rd;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {
    public static void main(String[] args) {
        String[] grade = {"B+","A0","C+"};
        int[] weights = {6,7,8};
        int threshold = 200;
        System.out.println(solution(grade,weights,threshold));
    }

    public static int solution(String[] grades, int[] weights, int threshold) {
        int sum = 0;
        Map<String,Integer> grade = makeGrade();

        for(int i=0;i< grades.length;i++){
            sum += grade.get(grades[i]) * weights[i];
        }
        int answer = sum - threshold;
        return answer;
    }
    public static Map<String,Integer> makeGrade(){
        Map<String,Integer> grade = new HashMap<>();
        grade.put("F",0);
        grade.put("D0",3);
        grade.put("D+",4);
        grade.put("C0",5);
        grade.put("C+",6);
        grade.put("B0",7);
        grade.put("B+",8);
        grade.put("A0",9);
        grade.put("A+",10);
        return grade;
    }
}
