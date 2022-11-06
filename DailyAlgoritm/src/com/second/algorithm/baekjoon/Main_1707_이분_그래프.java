package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1707_이분_그래프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int loop = 0; loop < T; loop++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<List<Integer>> graph = new ArrayList<>();
            for (int vertex = 0; vertex <= V; vertex++) {
                graph.add(new ArrayList<>());
            }

            for (int edge = 0; edge < E; edge++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            boolean checkGraph = true;
            int[] check = new int[V + 1];
            for (int vertex = 1; vertex <= V; vertex++) {
                if(!checkGraph) break;

                if (check[vertex] == NOT_COLOR) {
                    checkGraph = isBipartiteGraph(vertex, V, graph, check);
                }
            }

            if (checkGraph) System.out.println("YES");
            else System.out.println("NO");
        }


        br.close();
    }

    private static final int RED = 1, BLUE = -1, NOT_COLOR = 0;

    private static boolean isBipartiteGraph(int startVertex, int v, List<List<Integer>> graph, int[] check) {

        check[startVertex] = RED;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int loop = 0; loop < size; loop++) {
                int vertex = queue.poll();

                for (Integer nextVertex : graph.get(vertex)) {
                    int color = check[vertex];
                    if (check[nextVertex] == color) return false;
                    else if (check[nextVertex] == NOT_COLOR) {
                        if (color == RED) check[nextVertex] = BLUE;
                        else check[nextVertex] = RED;

                        queue.add(nextVertex);
                    }
                }
            }
        }

        return true;
    }
}
