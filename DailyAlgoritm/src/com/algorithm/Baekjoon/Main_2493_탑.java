package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> tower = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int loop = 0; loop < N; loop++) {
            tower.push(Integer.parseInt(st.nextToken()));
        }

        int[] rsts = getTowerIndex(N, tower);
        for (int rst : rsts) {
            System.out.print(rst+" ");
        }
        br.close();
    }

    private static int[] getTowerIndex(int n, Stack<Integer> tower) {
        int[] towerIndex = new int[n];
        Stack<Integer> indexStack = new Stack<>();
        while(!tower.isEmpty()) {
            int height = tower.pop();
            int index = tower.size();
            indexStack.push(index); // 현재 탑 인덱스 넣어주기

            // 다음 신호가 안닿는 경우 indexStack에 넣어주기
            while (!tower.isEmpty() && tower.peek() < height) {
                tower.pop();
                int popTowerIndex = tower.size();
                indexStack.push(popTowerIndex);
            }

            // tower.size = 만나는 타워의 인덱스
            // pop한 타워보다 작은 타워들은 모두 tower.size 타워에 부딪히므로 인덱스 넣어주기
            while(!indexStack.isEmpty()) {
                towerIndex[indexStack.pop()] = tower.size();
            }
        }
        return towerIndex;
    }
}
