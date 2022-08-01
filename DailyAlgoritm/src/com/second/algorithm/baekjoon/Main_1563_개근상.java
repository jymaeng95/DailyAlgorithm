package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1563_개근상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long rst = getPrize(N);
        System.out.println(rst);

        br.close();
    }

    private static long getPrize(int n) {
        if(n==1) return 3;
        if(n==2) return 8;
        // 지각(L)인 경우를 구하기 위해서는 출석, 결석 정보만 가지고 조합 필요 (지각 한번되기 때문에)
        long[][] late = new long[2][n + 1];
        late[0][1] = late[1][1] = 1;
        late[0][2] = late[1][2] = late[0][1] + 1;

        // OA, AO, AA, OO같이 L이 없는 경우에만 현재 날짜에 지각을 할 수 있다. -> 날짜별 가능한 OA, AO이런 조합 찾기
        for (int day = 3; day <= n; day++) {
            late[0][day] = (late[0][day - 1] + late[1][day - 1]) % 1000000;
            late[1][day] = (late[0][day - 2] + late[0][day - 1]) % 1000000;
        }

        // 전체 출결 정보 구하기
        long[][] info = new long[3][n + 1];
        info[0][1] = info[1][1] = info[2][1] = 1;
        info[0][2] = info[2][2] = 3;
        info[1][2] = late[0][1] + late[1][1];

        for (int day = 3; day <= n; day++) {
            // 출석을 하는 경우는 전날까지 개근상 조건이면 모두 가능하다.
            info[0][day] = (info[0][day - 1] + info[1][day - 1] + info[2][day - 1]) % 1000000;

            // 현재 날짜에 지각을 하는 경우는 지금까지 지각을 한번도 안한 경우만 가능하다. -> late 배열 찾기
            info[1][day] = (late[0][day - 1] + late[1][day - 1]) % 1000000;

            // 3일 연속 결석이 아닌 경우 = (어제 출석을 했거나, 그저께 출석을 한경우) + (어제 지각을 했거나, 그저께 지각을 한경우)
            info[2][day] = (info[0][day - 2] + info[0][day - 1] + info[1][day - 1] + info[1][day - 2]) % 1000000;
        }

        return (info[0][n] + info[1][n] + info[2][n]) % 1000000;
    }
}
