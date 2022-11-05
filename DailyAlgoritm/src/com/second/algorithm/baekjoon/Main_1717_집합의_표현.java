package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_집합의_표현 {
    private static final int UNION = 0, FIND = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N + 1];
        for (int number = 1; number <= N; number++) {
            numbers[number] = number;
        }

        for (int order = 0; order < M; order++) {
            st = new StringTokenizer(br.readLine());

            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (operation == UNION) union(numbers, a, b);
            else {
                if (find(a, numbers) == find(b, numbers)) System.out.println("YES");
                else System.out.println("NO");
            }
        }

        br.close();
    }

    private static int find(int x, int[] numbers) {
        if (x != numbers[x]) numbers[x] = find(numbers[x], numbers);
        return numbers[x];
    }

    private static void union(int[] numbers, int a, int b) {
        int parentA = find(a, numbers);
        int parentB = find(b, numbers);

        if (parentA < parentB) numbers[parentB] = parentA;
        else numbers[parentA] = parentB;
    }
}
