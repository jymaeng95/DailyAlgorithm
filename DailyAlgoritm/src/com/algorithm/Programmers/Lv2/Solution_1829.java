package com.algorithm.Programmers.Lv2;

import java.util.*;

public class Solution_1829 {
    static class Book{
        int xIndex;
        int yIndex;

        public Book(int xIndex, int yIndex) {
            this.xIndex = xIndex;
            this.yIndex = yIndex;
        }
    }
    public static void main(String[] args) {
        int[][] picture = {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        solution(6,4,picture);
    }
    public static List<Integer> size;
    public static int count;
    public static int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        size = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]&&picture[i][j] != 0){
                    bfs(i,j,picture,visited,m,n);
                }
            }
        }
        answer[0] = size.size();
        Iterator<Integer> it = size.iterator();
        int max = 0;
        while(it.hasNext()){
            max = Math.max(max,it.next());
            it.remove();
        }
        answer[1] = max;
        System.out.println(answer[0]);
        System.out.println(answer[1]);
        return answer;
    }

    private static void bfs(int i, int j, int[][] picture, boolean[][] visited,int m,int n) {
        Queue<Book> que = new LinkedList<>();
        que.offer(new Book(i,j));
        count=0;
        while(!que.isEmpty()){
            Book book = que.poll();
            if(visited[book.xIndex][book.yIndex])
                continue;
            visited[book.xIndex][book.yIndex] = true;
            connect(book,que,m,n,picture,visited);
            count++;
        }
        size.add(count);
    }

    private static void connect(Book book, Queue<Book> que,int m, int n,int[][] picture,boolean[][] visited) {
        int i = book.xIndex;
        int j = book.yIndex;
        int color = picture[i][j];
        if(i>0){
            if(picture[i-1][j] == color&&!visited[i-1][j]){
                que.offer(new Book(i-1,j));
            }
        }
        if(i<m-1){
            if(picture[i+1][j] == color&&!visited[i+1][j]){
                que.offer(new Book(i+1,j));
            }
        }
        if(j>0){
            if(picture[i][j-1] == color&&!visited[i][j-1]){
                que.offer(new Book(i,j-1));
            }
        }
        if(j<n-1){
            if(picture[i][j+1] == color&&!visited[i][j+1]){
                que.offer(new Book(i,j+1));
            }
        }
    }
}
