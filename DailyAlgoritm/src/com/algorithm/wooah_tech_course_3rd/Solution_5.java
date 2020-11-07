package com.algorithm.wooah_tech_course_3rd;

public class Solution_5 {
    public static void main(String[] args) {
        String penter= "10";
        String pexit = "11";
        String pescape = "00";
        String data = "00011011";
        System.out.println(solution(penter,pexit,pescape,data));
        System.out.println("100000010010001111");
    }
    public static String solution(String penter, String pexit, String pescape, String data) {
        StringBuilder answer = new StringBuilder(penter);
        int i=0;
        while(i<data.length()){
            String subData = data.substring(i,i+penter.length());
            if(subData.equals(penter) || subData.equals(pexit) || subData.equals(pescape)){
                answer.append(pescape);
            }
            answer.append(subData);
            i+=penter.length();
        }
        answer.append(pexit);
        return answer.toString();
    }
}
