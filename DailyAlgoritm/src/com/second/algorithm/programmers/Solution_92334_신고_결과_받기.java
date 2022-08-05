package com.second.algorithm.programmers;

import java.util.*;

public class Solution_92334_신고_결과_받기 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

//        String[] id_list = {"con", "ryan"};
//        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
//        int k = 3;
        int[] rst = solution(id_list, report, k);
        Arrays.stream(rst).forEach(System.out::println);

    }

    private static int[] solution(String[] id_list, String[] report, int k) {
        // 중복 신고 제거
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));

        // 신고 정보 저장
        Map<String, List<String>> logs = new HashMap<>();
        for (String user : id_list) {
            logs.put(user, new ArrayList<>());
        }

        Map<String, Integer> reportCounts = new HashMap<>();
        for (String log : reportSet) {
            StringTokenizer st = new StringTokenizer(log);
            String reporter = st.nextToken();
            String user = st.nextToken();

            // 신고 정보 저장
            logs.get(reporter).add(user);

            // 신고 횟수 저장
            if (reportCounts.containsKey(user))
                reportCounts.replace(user, reportCounts.get(user) + 1);
            else
                reportCounts.put(user, 1);
        }

        int[] receive = new int[id_list.length];
        int index = 0;
        // 신고 정보확인하면서 개수 확인하기
        for (String reporter : id_list) {
            receive[index] = (int) logs.get(reporter).stream()
                    .filter(user -> reportCounts.containsKey(user) && reportCounts.get(user) >= k)
                    .count();
            index++;
        }
        return receive;
    }
}
