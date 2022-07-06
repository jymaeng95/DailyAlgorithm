package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
            int driver = 0;
            for (int count = 0; count < N; count++) {
                st = new StringTokenizer(br.readLine());
                int location = Integer.parseInt(st.nextToken());
                String drunk = st.nextToken();

                // 운전할 수 있는 최대 인원을 구해놓는다.
                if(drunk.equals("S")) driver++;

                friends.add(new Friend(location, drunk));
            }

            List<Car> cars = new ArrayList<>();
            for (int count = 0; count < C; count++) {
                st = new StringTokenizer(br.readLine());
                int location = Integer.parseInt(st.nextToken());
                int available = Integer.parseInt(st.nextToken());

                cars.add(new Car(location, available));
            }

            int sleepPeople = sleepPeople(N, C, L, friends, cars, driver);
            System.out.println("Data Set "+cases +":");
            System.out.println(sleepPeople);
        }

        br.close();
    }

    private static int sleepPeople(int N, int C, int L, List<Friend> friends, List<Car> cars, int driver) {
        // 차가 없는 경우
        if(C == 0) return N;

        PriorityQueue<Car> carQueue = new PriorityQueue<>(cars);
        Collections.sort(friends);

        int[] available = new int[L+1];     // 차에 태울 수 있는 인원 배열
        boolean[] ride = new boolean[N];    // 친구들이 탔는지 안탔는지 판단하는 배열

        int rideDriver = 0;
        while(!carQueue.isEmpty()) {
            Car car = carQueue.poll();
            // 운전자만 먼저 태울수 있는 만큼 넣어주기
            for (int number = 0; number < driver; number++) {
                if(car.getLocation() == friends.get(number).getLocation() && !ride[number]) {
                    available[car.getLocation()] += car.getAvailable() - 1; // 위치별 운전자 제외하고 자동차에 탈 수 있는 인원수 추가
                    ride[number] = true;
                    rideDriver++;       // 운전하는 친구들 카운팅
                    break;
                }
            }
        }

        int count = 0;
        for (int number = 0; number < friends.size(); number++) {
            Friend friend = friends.get(number);
            if(available[friend.getLocation()] > 0 && !ride[number] ) available[friend.getLocation()]--;    // 자리가 남았고, 아직 차에 안탄 친구면 태우기
            else count++;
        }

        // 운전자 친구들은 이미 ride 배열이 true이므로 위에 친구들을 태울 때 운전자도 같이 카운팅된다.
        // 따라서, 운전자 친구들 만큼 빼준다.
        return count - rideDriver;
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
            if(o.drunk.equals(this.drunk))
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
            if(this.location == o.location)
                return Integer.compare(o.available, this.available);
            return Integer.compare(this.location, o.location);
        }
    }
}
