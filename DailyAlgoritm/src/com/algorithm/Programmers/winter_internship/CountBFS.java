package com.algorithm.Programmers.winter_internship;

import java.io.IOException;
import java.util.*;

public class CountBFS {
    static class Index{
        int xIndex;
        int yIndex;
        int cat;

        public Index(int xIndex,int yIndex,int cat) {
            this.xIndex = xIndex;
            this.yIndex = yIndex;
            this.cat = cat;
        }
    }
    public static List<LinkedList<Index>> node = new ArrayList<LinkedList<Index>>();
    public static Map<Integer,Integer> count = new HashMap();
    public static void main(String[] args) throws IOException {
        int[][] v = {{0, 0, 1, 1},{1, 1, 1, 1},{2, 2, 2, 1},{0, 0, 0, 2}};
        solution(v);
    }

    public static int[] solution(int[][] v)  {
        int[] answer = new int[3];
        boolean[][] visited = new boolean[v.length][v.length];
        for(int i=0;i<3;i++){
            count.put(i,0);
        }
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                node.add(new LinkedList<>());
            }
        }
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                connect(i,j,v);
            }
        }
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v.length;j++){
                if(!visited[i][j]){
                    dfs(i,j,visited,v);
                    count.put(v[i][j],(count.get(v[i][j])+1));
                }
            }
        }
        for(int i=0;i<count.size();i++){
            answer[i] = count.get(i);
            System.out.print(answer[i]);

        }
        return answer;
    }

    public static void dfs(int i, int j, boolean[][] visited,int[][] v) {
        dfsUtil(i, j, visited,v);
    }

    public static void dfsUtil(int i, int j, boolean[][] visited,int[][] v) {
        if (visited[i][j]) return;
        visited[i][j] = true;

        int nodeIndex = (i * v.length) + j;
        for (Index index : node.get(nodeIndex)) {
            dfsUtil(index.xIndex, index.yIndex, visited,v);
        }
    }

    private static void connect(int i, int j, int[][] v) {
        int orgIndex = (i * v.length) + j;
        if (i > 0) {
            //위 탐색
            if (v[i - 1][j] == v[i][j])
                linkNode(i - 1, j, orgIndex,v[i][j]);
        }
        if (i < v.length - 1) {
            //아래 탐색
            if (v[i + 1][j] == v[i][j])
                linkNode(i + 1, j, orgIndex,v[i][j]);
        }
        if (j > 0) {
            //왼쪽 탐색
            if (v[i][j - 1] == v[i][j])
                linkNode(i, j - 1, orgIndex,v[i][j]);
        }
        if (j < v.length - 1) {
            //오른쪽 탐색
            if (v[i][j + 1] == v[i][j])
                linkNode(i, j + 1, orgIndex,v[i][j]);
        }
    }

    private static void linkNode(int i, int j, int orgIndex,int cat) {
        node.get(orgIndex).add(new Index(i, j,cat));
    }
}
