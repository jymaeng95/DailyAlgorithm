package com.second.algorithm.programmers;

import java.util.*;

public class Solution_47234_나만의_L4_만들기 {
    public static void main(String[] args) {
        int servers = 2;
        boolean sticky = true;
        int[] requests = new int[]{1,1,1,1,1,1};

        int[][] result = solution(servers, sticky, requests);
        for (int[] ints : result) {
            Arrays.stream(ints).forEach(System.out::print);
        }
    }

    private static int[][] solution(int servers, boolean sticky, int[] requests) {
        Map<Integer, Integer> allocation = new HashMap<>();
        List<List<Integer>> status = new ArrayList<>();
        for (int server = 0; server < servers; server++) {
            status.add(new ArrayList<>());
        }
        int server = 0;
        for (int request : requests) {

            if (!allocation.containsKey(request)) {
                allocation.put(request, server);
                status.get(server).add(request);
                server++;
            }
            else {
                if (sticky && allocation.containsKey(request)) {
                    status.get(allocation.get(request)).add(request);
                } else {
                    status.get(server).add(request);
                    server++;
                }
            }

            // 서버할당 기록이 없는 경우

            if (server >= servers) server = 0;

        }

        // 가변 길이 배열
        int[][] result = new int[servers][];
        for (int serverIndex = 0; serverIndex < servers; serverIndex++) {
            result[serverIndex] = new int[status.get(serverIndex).size()];
            for (int requestIndex = 0; requestIndex < status.get(serverIndex).size(); requestIndex++) {
                result[serverIndex][requestIndex] = status.get(serverIndex).get(requestIndex);
            }
        }

        return result;
    }
}
