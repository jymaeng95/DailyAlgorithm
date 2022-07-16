package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1101_카드_정리_1 {
    private static int N, M;
    private static int[][] boxes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boxes = new int[N][M];
        for (int box = 0; box < N; box++) {
            st = new StringTokenizer(br.readLine());
            for (int color = 0; color < M; color++) {
                boxes[box][color] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getMoveCount();
        System.out.println(rst);
        br.close();
    }

    private static int getMoveCount() {
        int moveCount = N; // 이동 최대 횟수는 모든 박스 조커 박스로 옮겨버리기

        // 조커박스 모두 지정해보기
        for (int box = 0; box < N; box++) {
            boolean[] check = new boolean[M]; // 카드의 색상만큼 배열 선언
            moveCount = Math.min(moveCount, moveCard(box, check));
        }
        return moveCount;
    }

    private static int moveCard(int jokerBox, boolean[] check) {
        int count = 0;
        for (int box = 0; box < N; box++) {
            // 조커 박스는 체크하지 않는다.
            if(jokerBox == box) continue;

            int colorCount = 0; // 현재 박스의 색상 종류
            int colorIndex = 0; // 색상이 한종류인 경우 인덱스 체크
            for (int color = 0; color < M; color++) {
                if(boxes[box][color] > 0) {
                    colorCount++;
                    colorIndex = color; // 두개 이상인 경우 인덱스 사용 x
                }
            }

            // 색상이 여러개인 경우 조커박스로 한번에 넣어버리기
            if(colorCount > 1) count++;
            // 색상이 한개만 있는 경우
            else if(colorCount == 1){
                // 이미 해당 색상을 가진 박스가 있는 경우 해당 박스로 이동
                if(check[colorIndex]) count++;
                // 해당색상을 가진 박스가 없는 경우 해당 박스 체크
                else check[colorIndex] = true;
            }
        }
        return count;
    }
}
