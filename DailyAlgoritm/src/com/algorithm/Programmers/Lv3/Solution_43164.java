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

    //    private static String route;
    public static String[] solution(String[][] tickets) {

        visited = new boolean[tickets.length];
        spot = new LinkedList<>();
        for (int i = 0; i < tickets.length; i++) {

            dfs(tickets, tickets[i][1], tickets[i][0], 0);
        }
        for (String s : spot)
            System.out.println(s);
        Collections.sort(spot);
        String[] answer = spot.getFirst().split(" ");
        return answer;
    }

    private static void dfs(String[][] tickets, String end, String route, int count) {
        System.out.println(route);
        if (count == visited.length) {
            spot.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(end)) {
                visited[i] = true;
                dfs(tickets, tickets[i][1], route+" "+tickets[i][1], count + 1);
                visited[i] = false;
            }
        }
    }
}
