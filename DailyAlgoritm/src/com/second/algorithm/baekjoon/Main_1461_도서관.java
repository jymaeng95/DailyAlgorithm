package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1461_도서관 {
    private static List<Integer> leftDistances, rightDistances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        leftDistances = new ArrayList<>();
        rightDistances = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            int book = Integer.parseInt(st.nextToken());
            if (book > 0) rightDistances.add(book);
            else leftDistances.add(book);
        }

        int rst = getMinDistance(N, M);
        System.out.println(rst);
        br.close();
    }

    private static int getMinDistance(int n, int m) {
        // 왼쪽 오른쪽 정렬
        Collections.sort(leftDistances);
        rightDistances.sort(Collections.reverseOrder());

        // 가장 먼 곳은 왕복 하지 않는 것이 최선
        int left = 0;
        int right = 0;
        int minDistance = 0;
        
        // 왼쪽 리스트와 오른쪽 리스트 있는 경우 큰값 비교
        if (leftDistances.size() > 0 && rightDistances.size() > 0) {
            if (Math.abs(leftDistances.get(0)) >= Math.abs(rightDistances.get(0))) {
                minDistance = Math.abs(leftDistances.get(0));
                left += m;
            } else {
                minDistance = Math.abs(rightDistances.get(0));
                right += m;
            }
        }
        // 둘 중 하나 빈 리스트인 경우 있는 리스트를 한번 왕복안함
        else {
            if (leftDistances.size() > 0) {
                minDistance = Math.abs(leftDistances.get(0));
                left += m;
            } else {
                minDistance = Math.abs(rightDistances.get(0));
                right += m;
            }
        }

        minDistance = moveBooks(m, left, right, minDistance);

        return minDistance;
    }

    private static int moveBooks(int m, int left, int right, int minDistance) {
        while (left < leftDistances.size() || right < rightDistances.size()) {
            // 음수의 경우
            if (left < leftDistances.size()) {
                minDistance += Math.abs(leftDistances.get(left)) * 2;
                left += m;
            }

            // 양수의 경우
            if (right < rightDistances.size()) {
                minDistance += Math.abs(rightDistances.get(right)) * 2;
                right += m;
            }
        }
        return minDistance;
    }
}
