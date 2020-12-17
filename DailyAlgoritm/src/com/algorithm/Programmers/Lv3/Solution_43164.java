package com.algorithm.Programmers.Lv3;
import java.util.Collections;
import java.util.LinkedList;

public class Solution_43164 {

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        solution(tickets);
    }

    private static LinkedList<String> spot;
    private static boolean[] visited;
    public static String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        spot = new LinkedList<>();

        //항상 인천에서 시작
        dfs(tickets,"ICN","ICN",0);

        Collections.sort(spot);
        String[] answer = spot.getFirst().split(" ");
        return answer;
    }

    private static void dfs(String[][] tickets, String start, String route, int count) {
        //모든 티켓을 다 사용할 수 있는 경우
        if (count == visited.length) {
            spot.offer(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            // 티켓을 사용하지 않았으며, 도착한 공항과 다음 출발 공항이 동일 한 경우
            if(!visited[i] && tickets[i][0].equals(start)) {
                visited[i] = true;
                dfs(tickets, tickets[i][1], route+" "+tickets[i][1], count + 1);
                visited[i] = false;
            }
        }
    }
}
