package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_15961_회전_초밥 {

    private static int N, d, k, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for (int index = 0; index < N; index++) {
            sushi[index] = Integer.parseInt(br.readLine());
        }

        int rst = eatSushi(sushi);
        System.out.println(rst);

        br.close();
    }

    private static int eatSushi(int[] sushi) {
        // 0번 인덱스부터 마지막 인덱스까지 k개수 될때마다 몇개의 스시 먹을수 있는지 체크 - Map 사이즈로, c를 포함하지 않는 경우 개수 카운트 증가,
        Map<Integer, Integer> sushiCount = new HashMap<>();

        int start = 0;
        int end = 0;
        int maxEat = 0;
        // 회전 초밥이기 때문에 마지막에 도달한 경우 첫번째부터 다시 확인 필요
        while (start < N) {
            // 연속해서 다먹은 경우
            if (end - start == k) {
                // 쿠폰에 해당하는 스시가 없는 경우 개수 추가
                if (!sushiCount.containsKey(c)) maxEat = Math.max(maxEat, sushiCount.size() + 1);
                else maxEat = Math.max(maxEat, sushiCount.size());

                // start index의 스시를 Map에서 하나 빼줌
                int startSushiCount = sushiCount.get(sushi[start]);

                // 현재 접시를 뺄 것이기 때문에 1개인 경우는 삭제한다
                if (startSushiCount == 1) sushiCount.remove(sushi[start]);
                else sushiCount.replace(sushi[start], startSushiCount - 1);

                start++;
            }
            // 아닌 경우 end 인덱스 넣어주기
            else {
                sushiCount.put(sushi[end % N], sushiCount.getOrDefault(sushi[end % N], 0) + 1);
                end++;
            }
        }

        return maxEat;
    }
}
