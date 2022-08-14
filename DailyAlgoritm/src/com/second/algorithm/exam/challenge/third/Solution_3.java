package com.second.algorithm.exam.challenge.third;

public class Solution_3 {
    public static void main(String[] args) {
        int distance = 10;
//        int[][] scope = {{7, 8}, {4, 6}, {11,10}};
        int[][] scope = {{3, 4}, {5, 8}};
//        int[][] times = {{2, 2}, {2, 4}, {3, 3}};
        int[][] times = {{2, 5}, {4, 3}};

        int rst = solution(distance, scope, times);
        System.out.println("rst = " + rst);
    }

    private static int solution(int distance, int[][] scope, int[][] times) {
        int move = distance;

        for (int person = 0; person < scope.length; person++) {
            // 시작 시간, 종료 시간
            int startTime = 1;
            int endTime = startTime + times[person][0] - 1;
            int elapsed = times[person][0] + times[person][1];

            // 감시 구역 시작과 종료
            int watchStart = scope[person][0];
            int watchEnd = scope[person][1];

            // 감시 구역이 정렬 안되어 있음
            if(watchStart > watchEnd) {
                watchStart = scope[person][1];
                watchEnd = scope[person][0];
            }

            // 근무시간에 감시구역 도달하는 경우 => 감시구역 == 근무시간
            for (int index = watchStart; index <= watchEnd; index++) {
                // 근무시간은 반복되기 때문에 경과 시간으로 나머지 연산 (1-3분 동안 감시하고 2분 휴식 : [3,2] 1분 -> 6분 -> 11분 근무 시작 / 3분 -> 8분 -> 13분 근무 종료
                // 감시 구역을 각각 경과 시간으로 나눠 나머지가 시작시간과 종료시간과 동일하면 감시에 걸린 경우 :  나머지 연산해서 1분 - 3분에 포함되는 경우 갱신
                if(index % elapsed >= startTime && index % elapsed <= endTime) {
                    move = Math.min(index, move);
                    break;
                }
            }
        }
        return move;
    }
}
