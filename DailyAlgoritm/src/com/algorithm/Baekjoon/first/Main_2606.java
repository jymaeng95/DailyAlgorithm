package com.algorithm.Baekjoon.first;


import java.io.*;
import java.util.*;

public class Main_2606 {
    /*
    첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다.
    둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다.
    이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.
    (입력) (출력) 1번 컴퓨터 웜 바이러스 걸렸을때 1번 컴퓨터를 통해 걸리는 바이러스 카운트
    7
    6
    1 2
    2 3
    1 5
    5 2
    5 6
    4 7
    */
    public static List<LinkedList<Integer>> node;
    public static int computer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int comCount = Integer.parseInt(br.readLine());
        int netCount = Integer.parseInt(br.readLine());
        node = new ArrayList<LinkedList<Integer>>();

        for(int i=0;i<=comCount;i++){
            node.add(new LinkedList<>());
        }

        for(int i=0;i<netCount;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int firstCom = Integer.parseInt(st.nextToken());
            int secondCom = Integer.parseInt(st.nextToken());

            node.get(firstCom).add(secondCom);
            node.get(secondCom).add(firstCom);

            Collections.sort(node.get(firstCom));
            Collections.sort(node.get(secondCom));
        }

        boolean[] visited = new boolean[comCount+1];
        int virus = bfs(1,visited);

        bw.write(String.valueOf(virus));
        br.close();
        bw.close();
    }

    private static int bfs(int computer, boolean[] visited) {
        int virus = 0;
        Queue<Integer> que = new LinkedList<>();
        que.offer(computer);
        while(!que.isEmpty()){
            computer = que.poll();
            if(visited[computer])
                continue;
            visited[computer] = true;
            if(computer!=1)
                virus ++;
            for(int com : node.get(computer)){
                que.offer(com);
            }
        }
        return  virus;
    }
}

