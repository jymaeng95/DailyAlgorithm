package com.algorithm.Programmers.Lv2;

import java.util.Arrays;

public class Solution_77484_로또의_최고순위와_최저순위 {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        int[] solution = solution(lottos, win_nums);
        System.out.println(solution[0]+" "+solution[1]);
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        int unknown = 0;
        int correct = 0;
        for(int i=0;i<lottos.length;i++){
            if (lottos[i] == 0) {
                unknown++;
                continue;
            }
            for(int j=0;j<win_nums.length;j++) {
                if (lottos[i] == win_nums[j]) {
                    correct++;
                    break;
                }
            }
        }
        int high = 7 - correct - unknown;
        int low = 7 - correct;
        answer[0] = (high == 7 ? 6 : high);
        answer[1] = (low == 7 ? 6  : low);

        return answer;
    }
}
