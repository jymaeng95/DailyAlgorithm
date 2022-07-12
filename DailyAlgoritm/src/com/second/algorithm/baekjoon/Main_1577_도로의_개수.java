package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1577_도로의_개수 {
    private static int N, M;
    private static final int DISTRICT = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long[][] city = new long[M + 2][N + 2];   // 좌표이기 때문에 한칸 늘린다.
        int K = Integer.parseInt(br.readLine());

        for (int district = 0; district < K; district++) {
            st = new StringTokenizer(br.readLine());
            int startCol = Integer.parseInt(st.nextToken());
            int startRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());

            city[endRow + 1][endCol + 1] = DISTRICT;
        }

        long rst = arriveSchool(city);
        System.out.println(rst);

        br.close();
    }

    private static long arriveSchool(long[][] city) {
        // 시작점은 경로가 무조건 있다.
        city[1][1] = 1;

        for (int row = 1; row <= M + 1; row++) {
            for (int col = 1; col <= N + 1; col++) {
                // 처음 시작점은 제외한다.
                if(row == 1 && col ==1) continue;

                // 현재 위치가 공사중이지 않다면
                if (city[row][col] != DISTRICT) {
                    // 위와 오르쪽 경로가 모두 공사중이면 해당 경로는 도달할 수 없기 때문에 0
                    if (city[row - 1][col] == DISTRICT && city[row][col - 1] == DISTRICT) city[row][col] = 0;

                    // 위가 공사중이라면 오른쪽 경로로만 현재 위치에 도달 가능하다.
                    else if (city[row - 1][col] == DISTRICT) city[row][col] = city[row][col - 1];

                    // 오른쪽이 공사중이라면 위에서 온 경로로만 현재 위치에 도달이 가능하다.
                    else if (city[row][col - 1] == DISTRICT) city[row][col] = city[row - 1][col];

                    // 둘 다 공사중이지 않다면 양쪽의 경로에서 모두 올 수있다.
                    else city[row][col] = city[row - 1][col] + city[row][col - 1];
                }
            }
        }

        // 공사장에 도달 못하는 경우 return -1
        return city[M + 1][N + 1] == DISTRICT ? 0 : city[M + 1][N + 1];
    }
}
