package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11509_풍선_맞추기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] balloon = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++) {
            balloon[i] = Integer.parseInt(st.nextToken());
        }

        int rst = getMinArrow(N, balloon);
        System.out.println(rst);
        br.close();
    }

    private static int getMinArrow(int n, int[] balloon) {
        Queue<Integer> queue = new LinkedList<>();
        for(int bl :balloon) queue.offer(bl);

        int arrow = 0;
        while(!queue.isEmpty()) {
            int prev = queue.poll();
            //타겟보다 하나 작은 대상 나오는 경우 삭제 후 타겟 -1 사이즈만큼 for 문
            int size = queue.size();
            for(int i=0;i<size;i++){
                if(!queue.isEmpty()) {
                    int next = queue.poll();
                    if (next == prev - 1) prev--;
                    else queue.offer(next);
                }
            }
            arrow++;
        }
        return arrow;
    }
}
