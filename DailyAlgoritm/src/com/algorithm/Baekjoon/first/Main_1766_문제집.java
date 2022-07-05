package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1766_문제집 {
    private static List<List<Integer>> questionList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N+1];
        questionList = new ArrayList<>();
        for(int question = 0; question <= N; question++) {
            questionList.add(new ArrayList<>());
        }

        for(int order = 0; order < M; order++) {
            st = new StringTokenizer(br.readLine());
            int prevQuestion = Integer.parseInt(st.nextToken());
            int postQuestion = Integer.parseInt(st.nextToken());

            indegree[postQuestion]++;
            questionList.get(prevQuestion).add(postQuestion);
        }

        String rst = solveQuestion(indegree, N);
        System.out.println(rst);
        br.close();
    }

    private static String solveQuestion(int[] indegree, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();
        for(int question = 1; question <= n; question++) {
            if(indegree[question] == 0) pq.add(question);
        }

        while(!pq.isEmpty()) {
            int question = pq.poll();
            answer.append(question).append(" ");

            for(int nextQuestion : questionList.get(question)) {
                indegree[nextQuestion]--;
                if(indegree[nextQuestion] == 0) pq.add(nextQuestion);
            }
        }

        return answer.toString();
    }
}
