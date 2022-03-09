package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_17676_추석_트래픽 {
    public static void main(String[] args) {
        String[] lines = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};

//        String[] lines = {"2016-09-15 01:00:04.001 2.0s",
//                "2016-09-15 01:00:07.000 2s"};

//        String[] lines = {"2016-09-15 01:00:04.002 2.0s",
//                "2016-09-15 01:00:07.000 2s"};

//        String[] lines = {"2016-09-15 03:10:33.020 0.011s"};
        int rst = solution(lines);
        System.out.println("rst = " + rst);
    }

    static class Traffic implements Comparable<Traffic> {
        private long startTime;
        private long endTime;

        public Traffic(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        @Override
        public int compareTo(Traffic o) {
            return Long.compare(this.startTime, o.startTime);
        }
    }

    private static int solution(String[] lines) {
        List<Traffic> traffics = new ArrayList<>();
        for (int index = 0; index < lines.length; index++) {
            // 밀리초 계산
            String[] line = lines[index].split(" ");
            StringTokenizer st = new StringTokenizer(line[1], ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            double second = Double.parseDouble(st.nextToken());

            st = new StringTokenizer(line[2], "s");
            long elapsed = (long) (Double.parseDouble(st.nextToken()) * 1000);

            long endTime = hour * 3600000L + min * 60000L + (long) (second * 1000);
            long startTime = endTime - elapsed + 1;

            traffics.add(new Traffic(startTime, endTime));
        }

        Collections.sort(traffics);

        return getTimelineCount(traffics, traffics.size());
    }

    private static int getTimelineCount(List<Traffic> traffics, int size) {
        int count = 0;
        for (int loop = 0; loop < size; loop++) {
            int startCount = 1;
            long throughputEnd = traffics.get(loop).getStartTime() + 999;

            // 시작 시간 순 정렬을 했으므로, 시작 시간 기준 1초 처리량은 다음 인덱스 부터 진행
            for (int index = loop + 1; index < size; index++) {
                long nextStartTime = traffics.get(index).getStartTime();
                if (throughputEnd < nextStartTime) break;
                startCount++;
            }

            int endCount = 1;
            long throughput = traffics.get(loop).getEndTime();
            throughputEnd = traffics.get(loop).getEndTime() + 999;

            // 종료 시간은 정렬이 안되어있으므로 모두 탐색
            for (int index = 0; index < size; index++) {
                if (index == loop) continue; // 자기 자신인 경우 continue
                long nextStartTime = traffics.get(index).getStartTime();
                long nextEndTime = traffics.get(index).getEndTime();
                if (throughputEnd < nextStartTime) break;    // 종료 시간 기준 1초 처리량 종료보다 다음 트래픽 시작 시간 보다작으면 이후 시작 시간은 처리량에 포함 안됨칠 = break
                else if (throughput > nextEndTime) continue; // 종료 시간 기준 1초 처리량 시작보다 다음 트래픽 종료 시간 보다크면 해당 안되므로 continue;
                endCount++;
            }

            // 현재까지 처리량과 시작 및 종료 기준 처리량 맥스 갯수 비교
            count = Math.max(count, Math.max(startCount, endCount));
        }
        return count;
    }
}
