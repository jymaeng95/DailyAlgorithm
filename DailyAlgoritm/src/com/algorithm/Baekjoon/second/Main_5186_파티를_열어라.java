package com.algorithm.Baekjoon.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5186_파티를_열어라 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine()); // 테스트케이스

        for (int cases = 1; cases <= K; cases++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 사람수
            int C = Integer.parseInt(st.nextToken());   // 자동차 수
            int L = Integer.parseInt(st.nextToken());   // 지역 수

            List<Friend> friends = new ArrayList<>();
            for (int count = 0; count < N; count++) {
                st = new StringTokenizer(br.readLine());
                int location = Integer.parseInt(st.nextToken());
                String drunk = st.nextToken();

                friends.add(new Friend(location, drunk));
            }

            List<Car> cars = new ArrayList<>();
            for (int count = 0; count < C; count++) {
                st = new StringTokenizer(br.readLine());
                int location = Integer.parseInt(st.nextToken());
                int available = Integer.parseInt(st.nextToken());

                cars.add(new Car(location, available));
            }

            int sleepPeople = sleepPeople(N, C, L, friends, cars);
            System.out.println("Data Set "+cases +":");
            System.out.println(sleepPeople);
        }

        br.close();
    }

    private static int sleepPeople(int N, int C, int L, List<Friend> friends, List<Car> cars) {
        // 차가 없는 경우
        if(C == 0) return N;

        PriorityQueue<Friend> friendQueue = new PriorityQueue<>(friends);
        PriorityQueue<Car> carQueue = new PriorityQueue<>(cars);

        // 차에 태울 수 있는 인원 배열
        int[] available = new int[L+1];

        // 운전자 먼저 배치하기
        while (!carQueue.isEmpty() && !friendQueue.isEmpty() && friendQueue.peek().getDrunk().equals("S")) {
            while(!carQueue.isEmpty()) {
                Car car = carQueue.poll();

                // 정렬된 상태이므로 목적지 위치가 운전자가 갈 위치보다 큰 경우 운전할 차가 없다.
                // 남아있는 차가 있으면 먼저 태운다.
                if(car.getLocation() > friendQueue.peek().getLocation()) {
                    Friend friend = friendQueue.poll();
                    if(available[friend.getLocation()] > 0) available[friend.getLocation()]--;
                    break;
                }
                // 자동차 목적지 위치랑 집 위치가 동일하면 운전자 배치
                else if (car.getLocation() == friendQueue.peek().getLocation()) {
                    friendQueue.poll();
                    available[car.getLocation()] += car.getAvailable() - 1;
                    break;
                }
            }
        }

        // 남은 인원 각각 배치하기
        int count = 0;
        while (!friendQueue.isEmpty()) {
            Friend friend = friendQueue.poll();
            if(available[friend.getLocation()] > 0) available[friend.getLocation()]--;
            else count++;
        }

        return count;
    }

    static class Friend implements Comparable<Friend> {
        private int location;
        private String drunk;

        public Friend(int location, String drunk) {
            this.location = location;
            this.drunk = drunk;
        }

        public int getLocation() {
            return location;
        }

        public String getDrunk() {
            return drunk;
        }

        @Override
        public int compareTo(Friend o) {
            if(o.drunk.compareTo(this.drunk) == 0)
                return Integer.compare(this.location, o.location);
            return o.drunk.compareTo(this.drunk);
        }
    }

    static class Car implements Comparable<Car> {
        private int location;
        private int available;

        public Car(int location, int available) {
            this.location = location;
            this.available = available;
        }

        public int getLocation() {
            return location;
        }

        public int getAvailable() {
            return available;
        }


        @Override
        public int compareTo(Car o) {
            if(Integer.compare(this.location, o.location) == 0 )
                return Integer.compare(o.available, this.available);
            return Integer.compare(this.location, o.location);
        }
    }
}
