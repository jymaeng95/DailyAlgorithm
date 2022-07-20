package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main_1039_교환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        int rst = changeNumber(N, K);
        System.out.println(rst);

        br.close();
    }

    private static int changeNumber(String n, int k) {
        if(n.length() == 1) return -1;
        if(n.length() == 2 && n.contains("0")) return -1;

        // 순서쌍 구하기
        List<Pair> pairs = getPairs(n.length());

        // 가능한 경우의 수로 큐로 변환
        Queue<Possible> queue = pairs.stream()
                .map(pair -> new Possible(n, pair.getFirst(), pair.getSecond(), 1))
                .collect(Collectors.toCollection(LinkedList::new));

        // 셋을 이용해 중복 제거
        Set<String> available = new HashSet<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean[] check = new boolean[1000001];

            // 큐 사이즈만큼 돌리기
            for (int loop = 0; loop < size; loop++) {
                Possible possibleNumber = queue.poll();

                String number = possibleNumber.getNumber();
                int firstIndex = possibleNumber.getFirst();
                int secondIndex = possibleNumber.getSecond();
                int turn = possibleNumber.getTurn();

                String afterSwap = swapNumber(firstIndex, secondIndex, number);

                // 이미 해당 턴에 체크했거나, 0으로 시작하는 경우 제외
                if (afterSwap.startsWith("0") || check[Integer.parseInt(afterSwap)]) continue;
                check[Integer.parseInt(afterSwap)] = true;

                // 턴을 다 사용한 경우
                if (turn == k) {
                    available.add(afterSwap);
                    continue;
                }

                // 변경된 값 큐에 넣기
                for (Pair pair : pairs) {
                    queue.add(new Possible(afterSwap, pair.getFirst(), pair.getSecond(), turn + 1));
                }
            }
        }

        // 최대값 구하기
        return available.stream().mapToInt(Integer::parseInt).max().getAsInt();
    }

    // 숫자 교환
    private static String swapNumber(int firstIndex, int secondIndex, String n) {
        return n.substring(0, firstIndex) + n.charAt(secondIndex) +
                n.substring(firstIndex + 1, secondIndex) + n.charAt(firstIndex) + n.substring(secondIndex + 1);
    }

    // 자릿수 교환 가능 경우의 수 담기
    private static List<Pair> getPairs(int m) {
        List<Pair> pairs = new ArrayList<>();
        for (int first = 0; first < m; first++) {
            for (int second = first + 1; second < m; second++) {
                pairs.add(new Pair(first, second));
            }
        }

        return pairs;
    }

    static class Pair {
        private int first;
        private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }

    static class Possible {
        private String number;
        private int first;
        private int second;
        private int turn;

        public Possible(String number, int first, int second, int turn) {
            this.number = number;
            this.first = first;
            this.second = second;
            this.turn = turn;
        }

        public String getNumber() {
            return number;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        public int getTurn() {
            return turn;
        }
    }
}
