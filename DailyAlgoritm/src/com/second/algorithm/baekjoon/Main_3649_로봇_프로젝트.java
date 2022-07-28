package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_3649_로봇_프로젝트 {
    private static int X, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String checkCase = "";
        while((checkCase = br.readLine()) != null) {
            try {
                X = Integer.parseInt(checkCase);
                N = Integer.parseInt(br.readLine());

                int[] length = new int[N];
                for (int lego = 0; lego < N; lego++) {
                    length[lego] = Integer.parseInt(br.readLine());
                }

                String rst = isBlockHole(length);
                System.out.println(rst);
            } catch (Exception e) {
                break;
            }
        }
        br.close();
    }

    private static String isBlockHole(int[] length) {
        // 정렬
        Arrays.sort(length);

        int left = 0;
        int right = N - 1;
        long xToNano = X * (long) 1e7;

        // 정렬 후 첫번째 레고와 마지막 레고의 합이 X 나노미터보다 작은 경우면 딱맞는 블럭을 찾을 수 없음
        if(length[left] + length[right] < xToNano) return "danger";

        boolean blocked = false;
        while(left < right) {
            // 두 레고 길이의 합이 X 나노미터보다 크면 큰 레고의 길이를 줄여야 한다.
            if (length[left] + length[right] > xToNano) {
                right--;
            }
            // 두 레고 길이의 합이 X 나노미터보다 작으면 작은 레고의 길이를 늘려본다.
            else if(length[left] + length[right] < xToNano){
                left++;
            }
            // 두 레고의 길이의 합이 X 나노미터인 경우 최초의 경우가 최대 길이이다.(정렬 되어있기 때문에)
            else {
                blocked = true;
                break;
            }
        }

        // 구멍을 막을 수 없는 경우
        if(!blocked) return "danger";
        return "yes " + length[left] + " " + length[right];
    }
}
