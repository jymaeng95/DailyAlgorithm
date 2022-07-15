package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1092_배 {
    private static int N, M;
    private static List<Integer> cranes, boxes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        cranes = new ArrayList<>();
        for (int weight = 0; weight < N; weight++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boxes = new ArrayList<>();
        for (int weight = 0; weight < M; weight++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        int rst = loadBoxes();
        System.out.println(rst);

        br.close();
    }

    private static int loadBoxes() {
        int minute = 0;
        cranes.sort(Collections.reverseOrder());
        boxes.sort(Collections.reverseOrder());

        // 박스가 없어질 때 까지
        while (boxes.size() > 0) {
            int boxIndex = 0;
            int craneIndex = 0;
            // 크레인이 아직 남아있고, 마지막 박스까지 가지 않은 경우에만 크레인이 작동 가능
            while(craneIndex < N && boxIndex < boxes.size()) {
                // 크레인이 실을 수 있는 가장 큰 박스를 싣는다.
                if (cranes.get(craneIndex++) >= boxes.get(boxIndex))
                    // 박스 제거
                    boxes.remove(boxIndex);

                // 크레인이 실을 수 없다면 더 작은 박스 찾기
                else if(cranes.get(craneIndex) < boxes.get(boxIndex))boxIndex++;
            }

            minute++;
        }

        return minute;
    }
}
