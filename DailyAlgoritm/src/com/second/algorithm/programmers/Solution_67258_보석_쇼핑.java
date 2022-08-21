package com.second.algorithm.programmers;

import java.util.*;

public class Solution_67258_보석_쇼핑 {
    public static void main(String[] args) {
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        String[] gems = {"D", "D", "A", "B", "B", "C"};
        int[] rst = solution(gems);
        Arrays.stream(rst).forEach(System.out::println);

    }

    private static int[] solution(String[] gems) {
        int[] answer = new int[2];

        //진열대의 특정 범위의 보석을 구매
        //모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간

        //set으로 모든 보석 종류 구하기

        //시작 진열대 번호, 끝 진열대 번호 리턴

        //여러개라면 시작 진열대 번호가 제일 작은 구간

        HashSet<String> set = new HashSet<>();

        for (String s : gems) {
            set.add(s);
        }

        int n = gems.length;

        int distance = Integer.MAX_VALUE;
        //범위
        int left = 0;
        int right = 0;

        //정답
        int start = 0;
        int end = 0;

        HashMap<String, Integer> map = new HashMap<>();

        while (true) {

            if (set.size() == map.size()) {
                //크기 같은 경우 원하는 범위 좁히기
                map.put(gems[left], map.get(gems[left]) - 1);

                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            } else if (right == n) {
                break;
            } else {
                //right 오른쪽으로 이동
                //set에 해당하는 보석들을 다 찾아야함
                //해당 보석의 개수 +1
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                right++;
            }

            if (map.size() == set.size()) {
                if (right - left < distance) {
                    distance = right - left;
                    start = left + 1;
                    end = right;
                }
            }

        }//while

        answer[0] = start;
        answer[1] = end;

        return answer;
    }
}
