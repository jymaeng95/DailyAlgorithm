package com.second.algorithm.programmers;

public class Solution_17676_추석_트래픽 {
    public static void main(String[] args) {
   /*     String[] lines = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};*/
        String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        int rst = solution(lines);
        System.out.println("rst = " + rst);
    }

    private static int solution(String[] lines) {
        int[][] traffics = parseLines(lines);

        int maxThroughput = 1;
        for (int index = 0; index < traffics.length; index++) {
            int startTraffic = traffics[index][1];
            int endTraffic = traffics[index][1] + 999;

            int count = 1;
            for (int compare = index + 1; compare < traffics.length; compare++) {
                int compareStart = traffics[compare][0];
                int compareEnd = traffics[compare][1];

                // 1. compare의 시작이 startTraffic 과 endTraffic 사이에 있는 경우 개수 증가
                if (startTraffic <= compareStart && compareStart <= endTraffic) count++;

                // 2. compare의 끝이 startTraffic과 endTraffic 사이에 있는 경우 개수 증가
                else if(startTraffic <= compareEnd && compareEnd <= endTraffic) count++;

                // 3. comapre의 시작이 startTraffic보다 먼저 시작되고 compare의 끝이 endTraffic보다 늦게 끝나는 경우
                else if (startTraffic > compareStart && compareEnd > endTraffic) count++;
            }

            maxThroughput = Math.max(maxThroughput, count);
        }
        return maxThroughput;
    }

    private static int[][] parseLines(String[] lines) {
        int[][] traffics = new int[lines.length][2];        // 처리시작시간 , 종료시간

        for(int index = 0; index < lines.length; index++) {
            String[] parse = lines[index].split(" ");

            // 응답 완료 시간 파싱
            String[] startParse = parse[1].split(":");
            traffics[index][1] = convertToMilliSecond(startParse[0], startParse[1], startParse[2]);

            // 응답 시작 시간 파싱
            String[] endParse = parse[2].split("s");
            traffics[index][0] = traffics[index][1] - (int) (Double.parseDouble(endParse[0]) * 1000) + 1;
        }
        return traffics;
    }

    private static int convertToMilliSecond(String hour, String minute, String second) {
        return Integer.parseInt(hour) * 60 * 60 * 1000 +
                Integer.parseInt(minute) * 60 * 1000 +
                (int) (Double.parseDouble(second) * 1000);
    }
}
