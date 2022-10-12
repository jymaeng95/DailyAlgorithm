package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_19585_전설 {
    private static int C, N, Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        String[] colors = new String[C];
        for (int index = 0; index < C; index++) {
            colors[index] = br.readLine();
        }

        Set<String> set = new HashSet<>();
        for (int index = 0; index < N; index++) {
            set.add(br.readLine());
        }

        Q = Integer.parseInt(br.readLine());
        String[] teams = new String[Q];
        for (int index = 0; index < Q; index++) {
            teams[index] = br.readLine();
        }

        matchTeams(colors, set, teams);

        br.close();
    }

    private static Trie colorTrie;
    private static void matchTeams(String[] colors, Set<String> nicknames, String[] teams) {
        colorTrie = new Trie();

        // 색깔 문자 등록
        for (String color : colors) {
            colorTrie.insert(color);
        }

        for (String team : teams) {
            int index = colorTrie.search(team);

            if(index < 0) {
                System.out.println("No");
                continue;
            }
            team = team.substring(index);

            if(nicknames.contains(team)) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    static class Node {
        private Map<Character, Node> child;
        private boolean isLast;

        public Node() {
            this.child = new HashMap<>();
        }

        public Map<Character, Node> getChild() {
            return child;
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean last) {
            isLast = last;
        }
    }

    static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String str) {
            Node node = root;

            for (int index = 0; index < str.length(); index++) {
                char character = str.charAt(index);

                node = node.getChild().computeIfAbsent(character, c -> new Node());
            }

            // 문자의 마지막 확인
            node.setLast(true);
        }

        public int search(String str) {
            Node node = root;

            for (int index = 0; index < str.length(); index++) {
                char character = str.charAt(index);
                node = node.getChild().get(character);

                // 이미 노드를 다 탐색한 경우
                if(node == null) break;

                // 노드의 자식이 문자를 포함한 경우
                if(node.isLast()) return index + 1;
            }
            return -1;
        }
    }
}
