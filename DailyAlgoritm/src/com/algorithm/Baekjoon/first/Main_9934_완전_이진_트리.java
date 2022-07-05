package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9934_완전_이진_트리 {
    static class Node {
        private int node;
        private int left;
        private int right;

        public Node(int node, int left, int right) {
            this.node = node;
            this.left = left;
            this.right = right;
        }

        public int getNode() {
            return node;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public void setNode(int node) {
            this.node = node;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }

    static class City implements Comparable<City> {
        private int inputPath;
        private int makePath;

        public City(int inputPath, int makePath) {
            this.inputPath = inputPath;
            this.makePath = makePath;
        }

        public int getInputPath() {
            return inputPath;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.makePath, o.makePath);
        }
    }

    private static Stack<Integer> path, makePath;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        path = new Stack<>();
        makePath = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        // 이진 트리 초기화
        Map<Integer, Node> tree = new HashMap<>();
        for (int node = 1; node < (int) Math.pow(2, N); node++) {
            tree.put(node, new Node(node, 0, 0));
        }

        // 상근이가 방문한 도시 번호 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int loop = 1; loop < (int) Math.pow(2, N); loop++) {
            path.push(Integer.parseInt(st.nextToken()));
        }

        // 루트를 1 2 3 순으로 시작하는 완전 이진트리 만들기
        for(int level = 1; level < N; level++) {
            for(int node = (int) Math.pow(2, level); node < (int) Math.pow(2, level+1); node++) {
                if(node % 2 == 0) tree.get(node / 2).setLeft(node);
                else tree.get(node / 2).setRight(node);
            }
        }

        // 새로 만든 이진트리를 이용해 방문 조건(inorder)에 따라 길 만들기
        inorder(1, tree);

        // 상근이의 도시 출력
        makeCity(N);
        br.close();
    }

    private static void makeCity(int n) {
        List<City> city = new ArrayList<>();
        // 1 2 3 순서의 이진 트리로 만든 경로와 입력으로 주어진 도시 경로를 하나의 클래스로 저장
        while(!path.isEmpty() && !makePath.isEmpty()) {
            city.add(new City(path.pop(), makePath.pop()));
        }

        // 새로 만든 트리가 1 2 3 순서로 되도록 정렬 후 출력
        Collections.sort(city);
        int index = 1;
        int level = 1;
        for (City c : city) {

            System.out.print(c.getInputPath()+" ");
            if(index == (int) Math.pow(2, level)-1) {
                System.out.println();
                level++;
            }
            index++;
        }
    }

    private static void inorder(int node, Map<Integer, Node> tree) {
        if(node == 0) return;

        inorder(tree.get(node).getLeft(), tree);
        makePath.push(node);
        inorder(tree.get(node).getRight(), tree);
    }
}
