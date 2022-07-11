package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14501_퇴사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Consulting> consultingList = new ArrayList<>();
        for (int day = 1; day <= N; day++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            consultingList.add(new Consulting(time, price));
        }

        int rst = consult(N, consultingList);
        System.out.println(rst);

        br.close();
    }

    private static int consult(int n, List<Consulting> consultingList) {
        int[] dp = new int[n + 2];

        for (int day = 1; day <= n; day++) {
            Consulting consulting = consultingList.get(day-1);
            int time = consulting.getTime();
            int price = consulting.getPrice();

            // 하루 전 날짜와 오늘 중 최대이익 중 최대 이익을 가져온다.
            dp[day] = Math.max(dp[day], dp[day - 1]);

            // 퇴사 전에 일을 끝낼 수 있는 경우
            if (day + time < n + 2)
                // 끝나는 날의 현재 최대이익과 오늘까지의 최대 이익 + 상담 가격 중 최대값
                dp[day + time] = Math.max(dp[day + time], dp[day] + price);

        }

        // 마지막날에 일이 끝나는 경우 체크
        dp[n + 1] = Math.max(dp[n + 1], dp[n]);

        return dp[n+1];
    }

    static class Consulting {
        private int time;
        private int price;

        public Consulting(int time, int price) {
            this.time = time;
            this.price = price;
        }

        public int getPrice() {
            return price;
        }

        public int getTime() {
            return time;
        }
    }
}
