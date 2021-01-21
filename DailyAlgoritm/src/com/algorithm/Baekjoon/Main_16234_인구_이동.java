//package com.algorithm.Baekjoon;
//
//import java.io.*;
//import java.util.Stack;
//import java.util.StringTokenizer;
//
//public class Main_16234_인구_이동 {
//    /*
//    첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
//    둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
//    인구 이동이 발생하는 횟수가 2,000번 보다 작거나 같은 입력만 주어진다.
//     */
//    private static int N, L, R, sum, cnt;
//    private static int[][] people;
//    private static boolean[][] visited;
//    private static int[] DX = {1, -1, 0, 0};
//    private static int[] DY = {0, 0, 1, -1};
//    private static Stack<Index> stack;
//    static class Index {
//        private int x;
//        private int y;
//
//        public Index(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        L = Integer.parseInt(st.nextToken());
//        R = Integer.parseInt(st.nextToken());
//
//        people = new int[N][N];
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < N; j++) {
//                people[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//        int count =0 ;
//        while(true){
//            visited = new boolean[N][N];
//
//            sum =0;
//            stack = new Stack<>();
//            for(int i=0;i<N;i++){
//                for(int j=0;j<N;j++){
//                    if(!visited[i][j])
//                        dfs(i,j);
//                }
//            }
//            if(stack.size() < 1)
//                break;
//            int div = sum / stack.size();
//            for(int i =0;i<stack.size();i++){
//                Index idx = stack.pop();
//                people[idx.x][idx.y] = div;
//            }
//            count++;
//        }
//        bw.write(String.valueOf(count));
//        bw.close();
//        br.close();
//    }
//
//    private static void dfs(int x, int y) {
//        if(visited[x][y])
//            return;
//        visited[x][y] = true;
//        stack.add(new Index(x,y));
//
//        int count = 0;
//        for(int i =0;i<DX.length;i++){
//            int nx = x + DX[i];
//            int ny = y + DY[i];
//            if(nx >= 0 && nx <N && ny >= 0 && ny<N){
//                int cap = Math.abs(people[nx][ny] - people[x][y]);
//                if(cap >= L && cap <= R) {
//                    count ++;
//                    sum += people[x][y];
//                    if(!visited[nx][ny]) {
//                        dfs(nx,ny);
//                    }
//                }
//            }
//        }
//        if(count < 1) {
//            stack.pop();
//            sum -= people[x][y];
//        }
//    }
//}
