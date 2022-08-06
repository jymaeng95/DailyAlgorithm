package com.second.algorithm.exam.challenge.third;

import java.util.*;

public class Solution_4 {
    public static void main(String[] args) {
        long[][] invitationPairs = {{1, 2}, {2, 3}, {2, 4}, {2, 5}, {5, 6}, {5, 7}, {6, 8}, {2, 9}};

        long[] rst = solution(invitationPairs);
        Arrays.stream(rst).forEach(System.out::println);

    }

    private static long[] solution(long[][] invitationPairs) {
        Map<Long, Long> orders = new HashMap<>();
        Map<Long, List<Long>> logs = new HashMap<>();
        long order = 1;
        for (long[] pairs : invitationPairs) {
            long userA = pairs[0];
            long userB = pairs[1];

            // 데이터 넣어주기
            if(!logs.containsKey(userA)) orders.put(userA, order++);
            logs.put(userA, new ArrayList<>());
            logs.get(userA).add(userB);
        }

        List<Person> list = new ArrayList<>();
        for (Long id : orders.keySet()) {
            long reward = 0;
            reward += logs.get(id).size() * 10;
            for (Long nextId : logs.get(id)) {
                reward += logs.get(nextId).size() *3;
                for (Long nextNextId : logs.get(nextId)) {
                    reward += logs.get(nextNextId).size();
                }
            }
            list.add(new Person(id, orders.get(id), reward));
            Collections.sort(list);

        }
        return new long[]{list.get(0).id, list.get(1).id, list.get(2).id};

    }


    static class Person implements Comparable<Person> {
        private long id;
        private long order;
        private long reward;

        public Person(long id, long order, long reward) {
            this.id = id;
            this.order = order;
            this.reward = reward;
        }


        @Override
        public int compareTo(Person o) {
            if(o.reward == this.reward)
                return Long.compare(this.order, o.order);
            return Long.compare(o.reward, this.reward);
        }
    }
}
