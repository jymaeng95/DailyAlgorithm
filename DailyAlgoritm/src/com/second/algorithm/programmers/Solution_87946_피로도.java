package com.second.algorithm.programmers;

public class Solution_87946_피로도 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};

        int rst = solution(k, dungeons);
        System.out.println("rst = " + rst);
    }

    private static int solution(int k, int[][] dungeons) {
        boolean[] explores = new boolean[dungeons.length];

        exploreCount = 0;   // 탐험한 던전 개수
        exploreDungeon(0,k, dungeons, explores);

        return exploreCount;
    }

    private static int exploreCount;
    private static void exploreDungeon(int count, int curTired, int[][] dungeons, boolean[] explores) {
        // 방문한 던전 개수 갱신
        exploreCount = Math.max(exploreCount, count);

        // 피로도 모두 소모 한 경우 리턴
        if(curTired < 1) return;


        // 던전 방문 선택
        for (int order = 0; order < dungeons.length; order++) {
            if(!explores[order]) {
                int minTired = dungeons[order][0];
                int useTired = dungeons[order][1];

                // 현재 피로도가 최소 요구 피로도보다 높거나 같으면 던전 입장
                if(curTired >= minTired) {
                    explores[order] = true;
                    exploreDungeon(count + 1, curTired - useTired, dungeons, explores);
                    explores[order] = false;
                }
            }
        }
    }
}
