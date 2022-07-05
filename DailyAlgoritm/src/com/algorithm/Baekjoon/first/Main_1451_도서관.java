package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1451_도서관 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] pos = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        int rst = getWalking(N, M, pos);
        System.out.println(rst);
        br.close();
    }

    private static int getWalking(int n, int m, int[] pos) {
        int left = 0;
        int right = n - 1;
        int walking = 0;
        Arrays.parallelSort(pos);
        boolean first = false;
        /* 체크 : left가 음수 right가 양수인 경우 방향 반대이므로 왕복 안됌 */
        while (left <= right) {
            // left 와 right 절대값 비교
            if (Math.abs(pos[left]) >= Math.abs(pos[right])) {
                // 첫 번째 인 경우 (가장 먼 경우) 한번만 가도록
                if (!first) {
                    walking += Math.abs(pos[left]);
                    first = true;
                }
                // 그 외 왕복
                else walking += Math.abs(pos[left]) * 2;

                // 왼쪽이 음수인 경우까지만 이동 (left가 애초에 양수이면 right 가 무조간 큼)
                for (int i = 0; i < m; i++) {
                    left += 1;
                    if (left < n && pos[left] >= 0) break;
                }
            } else {
                // 첫 번째인 경우 (가장 먼 경우) 한번만 가도록
                if (!first) {
                    walking += Math.abs(pos[right]);
                    first = true;
                }
                // 그 외 왕복
                else walking += Math.abs(pos[right]) * 2;

                // 오른쪽이 양수인 경우까지만 이동 (right가 애초에 음수이면 left가 무조건 큼)
                for (int i = 0; i < m; i++) {
                    right -= 1;
                    if (right >= 0 && pos[right] <= 0) break;
                }
            }
        }
        return walking;
    }
}
