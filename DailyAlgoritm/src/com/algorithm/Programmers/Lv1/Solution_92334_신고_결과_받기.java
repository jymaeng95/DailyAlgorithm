package com.algorithm.Programmers.Lv1;

import java.util.*;

public class Solution_92334_신고_결과_받기 {
    static class Report {
        private String person;
        private String reporter;

        public Report(String person, String reporter) {
            this.person = person;
            this.reporter = reporter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Report report = (Report) o;
            return Objects.equals(person, report.person) && Objects.equals(reporter, report.reporter);
        }

        @Override
        public int hashCode() {
            return Objects.hash(person, reporter);
        }
    }
    public static void main(String[] args) {
        String[] id_list = {"con", "ryan"};
        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 2;
        int[] solution = solution(id_list, report, k);
        for (int x : solution) {
            System.out.println("x = " + x);
        }
    }

    private static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Set<Report> reportSet = new HashSet<>();
        List<Report> list = new ArrayList<>();
        for(String rp : report) {
            StringTokenizer st = new StringTokenizer(rp);
            reportSet.add(new Report(st.nextToken(), st.nextToken()));
        }
        Map<String, Integer> punish = new HashMap<>();
        for (Report rp : reportSet) {
            list.add(rp);
            if (punish.containsKey(rp.reporter)) punish.replace(rp.reporter, punish.get(rp.reporter) + 1);
            else punish.put(rp.reporter, 1);
        }

        Map<String, Integer> reported = new HashMap<>();
        for(String id : id_list) {
            reported.put(id,0);
        }
        for(String id : id_list) {
            if(punish.containsKey(id) && punish.get(id) >= k) {
                for (Report rp : list) {
                    if (id.equals(rp.reporter)) {
                        reported.replace(rp.person, reported.get(rp.person) + 1);
                    }
                }
            }
        }

        for(int i=0;i< id_list.length;i++) {
            answer[i] = reported.get(id_list[i]);
        }
        return answer;
    }
}
