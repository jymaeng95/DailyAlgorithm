package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466_텀_프로젝트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int N = Integer.parseInt(br.readLine());
            int[] project = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int student = 1; student <= N; student++) {
                int studentNumber = Integer.parseInt(st.nextToken());
//                if(student == studentNumber) visited[student] = true;
                project[student] = studentNumber;
            }

            int rst = termProject(N, project);
            System.out.println(rst);
        }

        br.close();
    }

    //    private static Set<Integer> set;
//    private static Stack<Integer> stack;
    private static int[] visitedOrder;
    private static boolean[] finished;
    private static int count;

    private static int termProject(int n, int[] project) {
        count = 0;
        visitedOrder = new int[n + 1];
        finished = new boolean[n + 1];
        for (int student = 1; student <= n; student++) {
            if (!finished[student])
                makeTeam(student, project, 1);
        }
        return n - count;
    }

    private static void makeTeam(int student, int[] project, int order) {
        visitedOrder[student] = order;

        // 다음 학생이 아직 팀을 안꾸린 경우
        if (visitedOrder[project[student]] < 1) {
            makeTeam(project[student], project, order + 1);
        }
        // 이미 팀을 꾸렸지만 DFS 안끝난 경우
        else if (!finished[project[student]]) {
            // 팀을 꾸린 학생 인원수를 추가해줌
            count += visitedOrder[student] - visitedOrder[project[student]] + 1;
        }
        finished[student] = true;
    }
}
