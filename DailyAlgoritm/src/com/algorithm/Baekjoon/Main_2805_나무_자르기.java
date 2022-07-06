package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2805_나무_자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] height = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int tree = 0; tree < N; tree++) {
            height[tree] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(height);
        long rst = settingHeight(N, M, height);
        System.out.println(rst);

        br.close();
    }

    private static long settingHeight(int n, int m, int[] heights) {
        long minHeight = 1;
        long maxHeight = heights[n - 1];
        long  setting = 0;
        while (minHeight <= maxHeight) {
            long mid = (minHeight + maxHeight) / 2;
            long sumHeight = 0;
            for (int height : heights) {
                // mid가 트리 높이보다 크면 나무 잘라 얻은 길이 구하기
                if(mid < height) sumHeight += height - mid;
            }

            // 적어도 m 이상 성립 경우
            if(sumHeight >= m) {
                setting = Math.max(setting, mid);
                minHeight = mid + 1;
            }
            else {
                maxHeight = mid - 1;

            }
        }
        return setting;
    }
}
