package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16928_뱀과_사다리_게임 {
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 사다리 개수
        int M = Integer.parseInt(st.nextToken());   // 뱀 개수

        // 사다리와 뱀 Map으로 관리
        Map<Integer, Integer> snakeAndLadder = new HashMap<>();
        for (int loop = 0; loop < N + M; loop++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snakeAndLadder.put(start, end);
        }

        int rst = playGame(N, M, snakeAndLadder);
        System.out.println(rst);
        br.close();
    }

    private static int playGame(int n, int m, Map<Integer, Integer> snakeAndLadder) {
        boolean[] visited = new boolean[101];
        int[] distance = new int[101];
        PriorityQueue<Node> queue = new PriorityQueue<>();

        Arrays.fill(distance, INF);
        queue.add(new Node(1, 0));

        // 현재 칸이 100번째 칸이 아니면서 큐가 남아있다면
        while (!queue.isEmpty() && queue.peek().getNode() != 100) {
            Node position = queue.poll();

            if (visited[position.getNode()]) continue;
            visited[position.getNode()] = true;

            // 주사위 굴리기
            for (int dice = 1; dice <= 6; dice++) {
                int nextPosition = position.getNode() + dice;
                // 뱀과 사다리에 해당하는 칸인지 확인
                if (snakeAndLadder.containsKey(nextPosition)) {
                    if (!visited[snakeAndLadder.get(nextPosition)] && distance[snakeAndLadder.get(nextPosition)] > position.getDistance() + 1) {
                        // 뱀과 사다리를 탄 후 칸 거리를 갱신
                        distance[snakeAndLadder.get(nextPosition)] = position.getDistance() + 1;
                        queue.add(new Node(snakeAndLadder.get(nextPosition), position.getDistance() + 1));
                    }
                }
                // 뱀과 사다리에 해당하는 칸이 아님
                // 다음 칸이 100번재 칸 이하이면서 방문하지 않고, 현재 거리 + 1 보다 해당 칸 개수가 작은 경우
                else if (nextPosition <= 100 && !visited[nextPosition] && distance[nextPosition] > position.getDistance() + 1) {
                    distance[nextPosition] = position.getDistance() + 1;
                    queue.add(new Node(nextPosition, position.getDistance() + 1));
                }
            }
        }

        return distance[100];
    }

    static class Node implements Comparable<Node> {
        private int node;
        private int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public int getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
