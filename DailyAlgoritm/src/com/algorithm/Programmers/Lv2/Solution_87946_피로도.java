package com.algorithm.Programmers.Lv2;

public class Solution_87946_피로도 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
//        int[][] dungeons = {{60,10},{30,5},{40,20},{70,40},{50,10}};
//        int[][] dungeons = {{70,50}, {70,50}};
        int solution = solution(k, dungeons);
        System.out.println("solution = " + solution);
    }
    private static int answer;
    private static boolean[] visited;
    private static int solution(int k, int[][] dungeons) {
        answer = 0;
        visited = new boolean[dungeons.length];
        dfs(0,dungeons, k);
        return answer;
    }

    private static void dfs(int count, int[][] dungeons, int k) {
        for(int loop = 0; loop < dungeons.length; loop++) {
            if(!visited[loop] && k >= dungeons[loop][0]) {
                visited[loop] = true;
                k -= dungeons[loop][1];
                dfs( count+1,dungeons, k);
                k += dungeons[loop][1];
                visited[loop] = false;
            }
        }
        answer = Math.max(count, answer);
    }
}
