package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_5052_전화번호_목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());

        for (int loop = 0; loop < test; loop++) {
            int N = Integer.parseInt(br.readLine());
            String[] list = new String[N];

            for (int index = 0; index < N; index++) {
                list[index] = br.readLine();
            }

            if(findNumber(N, list)) System.out.println("YES");
            else System.out.println("NO");
        }

        br.close();
    }

    private static boolean findNumber(int n, String[] list) {
        Trie book = new Trie();
        for (String number : list) {
            book.insert(number);
        }

        for (String number : list) {
            if(!book.isLast(number)) return false;
        }
        return true;
    }

    // 트라이 자료구조
    static class Node {
        private Map<Character, Node> child;
        private boolean isLast;

        public Node() {
            child = new HashMap<>();
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
        // 루트 노드를 갖는다.
        private Node root;

        public Trie() {
            root = new Node();
        }

        // insert 구현
        public void insert(String str) {
            Node node = root;

            for (char character : str.toCharArray()) {
                // 탐색을 진행하다가 현재 문자열 노드가 true인 경우 문자 추가되므로 true 바꿔줌
                if(node.getChild().containsKey(character) && node.getChild().get(character).isLast())
                    node.getChild().get(character).setLast(false);

                // 현재 문자가 트라이에 존재하지 않는 경우 노드를 새로 만듬
                node = node.getChild().computeIfAbsent(character, c -> new Node());
            }

            // 마지막 문자인 경우 node의마지막 true
            if(node.getChild().isEmpty()) node.setLast(true);
        }

        // search
        public boolean isLast(String str) {
            Node node = root;

            for (char character : str.toCharArray()) {
                node = node.getChild().get(character);

                if (node == null) return false;
            }

            return node.isLast();
        }
    }
}
