package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_42892_길_찾기_게임 {
    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};

        int[][] rst = solution(nodeinfo);
        for (int index = 0; index < 2; index++) {
            System.out.println("Arrays.toString(rst[index]) = " + Arrays.toString(rst[index]));
        }
    }
    private static int preOrderIndex, postOrderIndex;
    private static int[][] solution(int[][] nodeinfo) {
        List<Node> list = new ArrayList<>();
        for (int index = 1; index <= nodeinfo.length; index++) {
            int x = nodeinfo[index - 1][0];
            int y = nodeinfo[index - 1][1];
            list.add(new Node(x, y, index));
        }

        Collections.sort(list);

        // 루트 설정하고
        Node root = list.get(0);

        // 배열 인덱스 갈때마다 하나씩 탐색하면서 연결
        for (int index = 1; index < list.size(); index++) {
            Node node = list.get(index);

            connect(root, node);
        }

        // Root부터 preorder
        preOrderIndex = postOrderIndex = 0;
        int[][] orders = new int[2][nodeinfo.length];
        
        preOrder(root, orders[0]);
        postOrder(root, orders[1]);
                
        return orders;
    }

    private static void preOrder(Node node, int[] order) {
        if(node != null) {
            order[preOrderIndex++] = node.getNumber();
            preOrder(node.left, order);
            preOrder(node.right, order);
        }
    }

    private static void postOrder(Node node, int[] order) {
        if (node != null) {
            postOrder(node.left, order);
            postOrder(node.right, order);
            order[postOrderIndex++] = node.getNumber();
        }
    }

    private static void connect(Node root, Node node) {
        Node move = root;
        Node parent;
        while (true) {
            parent = move;
            if (move.getX() > node.getX()) {
                if(move.left == null) {
                    parent.setLeft(node);
                    break;
                }
                move = move.left;
            }
            else {
                if(move.right == null)  {
                    parent.setRight(node);
                    break;
                }
                move = move.right;
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private final int x;
        private final int y;
        private final int number;
        private Node left;
        private Node right;


        public Node(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y == o.y) return Integer.compare(this.x, o.x);
            return Integer.compare(o.y, this.y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getNumber() {
            return number;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
