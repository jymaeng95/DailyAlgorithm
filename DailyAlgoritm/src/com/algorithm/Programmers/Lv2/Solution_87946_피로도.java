package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class Solution_87946_피로도 {
    public static void main(String[] args) {
        int k = 70;
//        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        int[][] dungeons = {{60,10},{30,5},{40,20},{70,40},{50,10}};
        int solution = solution(k, dungeons);
        System.out.println("solution = " + solution);
    }

    private static int solution(int k, int[][] dungeons) {
        int answer = 0;

        List<Dungeons> list = new ArrayList<>();
        for (int[] arr : dungeons) {
            list.add(new Dungeons(arr[0], arr[1]));
        }
        list.sort(((o1, o2) -> {
            int x1 = o1.minTired - o1.useTired;
            int x2 = o2.minTired - o2.useTired;
            if (x1 == x2) return Integer.compare(o2.minTired, o1.minTired);
            return Integer.compare(x2, x1);
        }));

        for(Dungeons dg : list) {
            if(k < dg.minTired)
                continue;
            k -= dg.useTired;
            answer++;
        }

        return answer;
    }

    static class Dungeons {
        int minTired;
        int useTired;

        public Dungeons(int minTired, int useTired) {
            this.minTired = minTired;
            this.useTired = useTired;
        }
    }
}
