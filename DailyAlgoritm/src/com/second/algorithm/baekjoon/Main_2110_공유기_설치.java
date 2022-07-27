package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_공유기_설치 {
    private static int N, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] house = new int[N];
        for (int position = 0; position < N; position++) {
            house[position] = Integer.parseInt(br.readLine());
        }

        int rst = setIpTime(house);
        System.out.println(rst);

        br.close();
    }

    private static int setIpTime(int[] house) {
        // 거리를 기준으로 이분탐색을 진행한다. (이분탐색은 기준이 중요한듯 ... )
        Arrays.sort(house);
        int maxLength = house[N - 1] - house[0];
        int start = 0;
        int end = N - 1;

        if(C == 2) return maxLength;

        // 공유기 2개 설치했으므로 빼주고 시작
        C -= 2;
        boolean first = false;
        boolean right = false;
        while (start < end) {
            int mid = (start + end) / 2;
            

            // 중간 위치에 공유기 설치 후 큰 거리에 있는 부분으로 탐색진행
            if (house[end] - house[mid] > house[mid] - house[start]) {
                if(!first) {
                    first = true;
                    right = true;
                }
                maxLength = Math.min(maxLength, house[mid] - house[start]);
                start = mid + 1;
            }
            else {
                end = mid;
            }
            C--;
        }


        System.out.println("C = " + C);
        return maxLength;
    }
}
