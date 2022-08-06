package com.second.algorithm.exam.challenge.third;

import java.util.*;

public class Solution_6 {
    public static void main(String[] args) {
        int[] steps_one = {0, 5, 1};
        String[] names_one = {"evan", "ed", "evan"};
        int[] steps_two = {9999};
        String[] names_two = {"rose"};
        int[] steps_three = {1};
        String[] names_three = {"richard"};

        String[] rst = solution(steps_one, names_one, steps_two, names_two, steps_three, names_three);
        Arrays.stream(rst).forEach(System.out::println);

    }

    private static String[] solution(int[] steps_one, String[] names_one, int[] steps_two, String[] names_two, int[] steps_three, String[] names_three) {
        // 이름모두 넣어 중복 제거
        Set<String> names = new HashSet<>();
        names.addAll(Arrays.asList(names_one));
        names.addAll(Arrays.asList(names_two));
        names.addAll(Arrays.asList(names_three));

        // 첫날 로그
        Map<String, Integer> first = new HashMap<>();
        makeRecords(steps_one, names_one, first);

        // 둘째날 로그
        Map<String, Integer> second = new HashMap<>();
        makeRecords(steps_two, names_two, second);

        // 세째날 로그
        Map<String, Integer> third = new HashMap<>();
        makeRecords(steps_three, names_three, third);

        List<Person> answer = new ArrayList<>();
        for (String name : names) {
            int sum = 0;
            if(first.containsKey(name)) sum += first.get(name);
            if(second.containsKey(name)) sum += second.get(name);
            if(third.containsKey(name)) sum += third.get(name);

            answer.add(new Person(name, sum));
        }

        // 정렬
        Collections.sort(answer);
        return answer.stream().map(Person::getName).toArray(String[]::new);
    }
    private static void makeRecords(int[] steps_one, String[] names_one, Map<String, Integer> first) {
        for (int index = 0; index < names_one.length; index++) {
            String name = names_one[index];
            if(first.containsKey(name)) {
                int score = first.get(name) < steps_one[index] ? steps_one[index] : first.get(name);
                first.replace(name, score);
            } else
                first.put(name, steps_one[index]);
        }
    }

    static class Person implements Comparable<Person> {
        private String name;
        private int score;

        public Person(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Person o) {
            if(o.score == this.score)
                return this.name.compareTo(o.name);
            return Integer.compare(o.score, this.score);
        }
    }

}
