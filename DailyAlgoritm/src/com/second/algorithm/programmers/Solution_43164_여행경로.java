package com.second.algorithm.programmers;

import java.util.*;

public class Solution_43164_여행경로 {
    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
//        String[][] tickets = {{"ICN", "A"}, {"A", "ICN"}, {"ICN", "B"}, {"B", "ICN"}, {"A", "B"}, {"B", "A"}, {"ICN", "A"}, {"A", "ICN"}};

        String[] rst = solution(tickets);

        Arrays.stream(rst).forEach(System.out::println);
    }
    
    private static Stack<String> path;
    private static boolean arrive;
    private static String[] solution(String[][] tickets) {
        boolean[] use = new boolean[tickets.length];

        // 티켓정보 리스트로 변경
        List<Ticket> ticketList = new ArrayList<>();
        for (String[] ticket : tickets) {
            ticketList.add(new Ticket(ticket[0], ticket[1]));
        }

        // 목적지 기준 정렬
        Collections.sort(ticketList);

        // 스택으로 경로 저장
        path = new Stack<>();
        path.add("ICN");
        arrive = false;

        startTravel("ICN", 0, use.length, use, ticketList);
        return path.toArray(String[]::new);
    }


    private static void startTravel(String airport, int useCount, int ticketCount, boolean[] use, List<Ticket> ticketList) {
        // 티켓을 모두 사용한 경우 + 최초로 모두 사용해서 목적지 도착한 경우 체크
        if(ticketCount == useCount) {
            arrive = true;
            return;
        }

        for (int index = 0; index < ticketCount; index++) {
            Ticket ticket = ticketList.get(index);

            // 티켓을 사용하지 않았고 현재 공항과 출발지가 동일한 티켓
            if (airport.equals(ticket.getDeparture()) && !use[index]) {
                // 백트래킹 사용 체크 및 스택 넣기
                use[index] = true;
                path.add(ticket.getDestination());

                // DFS 완탐 (만약 최초로 경로를 체크한 경우 리턴해서 시간 줄이기)
                startTravel(ticket.getDestination(), useCount + 1, ticketCount, use, ticketList);
                if(arrive) return;

                // 백트래킹
                path.pop();
                use[index] = false;
            }
        }
    }

    static class Ticket implements Comparable<Ticket> {

        private String departure;
        private String destination;

        public Ticket(String departure, String destination) {
            this.departure = departure;
            this.destination = destination;
        }

        @Override
        public int compareTo(Ticket o) {
            return destination.compareTo(o.destination);
        }

        public String getDeparture() {
            return departure;
        }

        public String getDestination() {
            return destination;
        }
    }
}
