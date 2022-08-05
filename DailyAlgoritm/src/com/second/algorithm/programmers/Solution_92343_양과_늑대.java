package com.second.algorithm.programmers;

import java.util.*;

public class Solution_92343_양과_늑대 {
    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};

//        int[] info = {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
//        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};

        int rst = solution(info, edges);
        System.out.println("rst = " + rst);
    }

    private static final int LAMB = 0, WOLF = 1;
    private static Map<Integer, List<Node>> roads;

    private static int solution(int[] info, int[][] edges) {
        // 길 초기화
        roads = new HashMap<>();
        for (int node = 0; node <= info.length; node++) {
            roads.put(node, new ArrayList<>());
        }

        // 길 추가
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            roads.get(start).add(new Node(end, info[end]));
            roads.get(end).add(new Node(start, info[start]));
        }

        int lambCount = collectLamb(0, info.length);
        return lambCount;
    }

    private static int collectLamb(int start, int nodeCount) {
        // 양이 최대가 아니라면 노드를 방문하도록 처리
        int[] lambCounts = new int[nodeCount];
        lambCounts[start] = 1;

        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(start, 1, 0, lambCounts));

        int maxLambCount = 1;
        while (!queue.isEmpty()) {
            Status nodeStatus = queue.poll();
            int node = nodeStatus.getNode();
            int lambCount = nodeStatus.getLambCount();
            int wolfCount = nodeStatus.getWolfCount();
            int[] curLambCounts = nodeStatus.getLambCounts();


            // 양과 늑대가 동일한 경우는 제외
            if (lambCount <= wolfCount) continue;
            maxLambCount = Math.max(maxLambCount, lambCount);

            for (Node next : roads.get(node)) {
                // 다음 노드가 늑대인 경우 
                if (next.getType() == WOLF) {
                    // 방문 안한 경우에만 늑대 늘려줌
                    int nextNode = next.getNode();
                    if (curLambCounts[nextNode] == 0) {

                        // 현재 양이 노드별 최대 양을 넘는 경우 큐에 넣어주고 현재 노드 양 개수 갱신
                        curLambCounts[nextNode] = lambCount;
                        queue.add(new Status(nextNode, lambCount, wolfCount + 1, curLambCounts));
                        curLambCounts[nextNode] = 0;
                    }
                    // 방문한 경우는 그냥 큐에만 데이터 넣기
                    else {
                        if (lambCount > curLambCounts[nextNode]) {
                            curLambCounts[nextNode] = lambCount;
                            queue.add(new Status(nextNode, lambCount, wolfCount, curLambCounts));
                        }
                    }
                }
                // 다음 노드가 양인 경우
                else {
                    int nextNode = next.getNode();
                    // 방문핝거 없는 경우라면 현재 양의 개수에 더해주고 방문 체크
                    if (curLambCounts[nextNode] == 0) {

                        // 백트래킹
                        curLambCounts[nextNode] = lambCount + 1;
                        queue.add(new Status(nextNode, lambCount + 1, wolfCount, curLambCounts));
                        curLambCounts[nextNode] = 0;

                    }
                    // 방문한 적이 있는 경우라면 현재 양의 개수가 최대인 경우에만 넣어준다.
                    else {
                        if (lambCount > curLambCounts[nextNode]) {
                            curLambCounts[nextNode] = lambCount;
                            queue.add(new Status(nextNode, lambCount, wolfCount, curLambCounts));
                        }
                    }
                }
            }
        }
        return maxLambCount;
    }

    static class Node {
        private int node;
        private int type;

        public Node(int node, int type) {
            this.node = node;
            this.type = type;
        }

        public int getNode() {
            return node;
        }

        public int getType() {
            return type;
        }
    }

    static class Status {
        private int node;
        private int lambCount;
        private int wolfCount;
        private int[] lambCounts;

        public Status(int node, int lambCount, int wolfCount, int[] lambCounts) {
            this.node = node;
            this.lambCount = lambCount;
            this.wolfCount = wolfCount;
            this.lambCounts = new int[lambCounts.length];
            System.arraycopy(lambCounts, 0, this.lambCounts, 0, lambCounts.length);
        }

        public int getNode() {
            return node;
        }

        public int getLambCount() {
            return lambCount;
        }

        public int getWolfCount() {
            return wolfCount;
        }

        public int[] getLambCounts() {
            return lambCounts;
        }
    }
}
