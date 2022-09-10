package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_42890_후보키 {
    public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};
        int rst = solution(relation);
        System.out.println("rst = " + rst);
    }

    private static List<String> keys;
    private static int counts;

    private static int solution(String[][] relation) {
        counts = 0;
        keys = new ArrayList<>();
        for (int count = 1; count <= relation[0].length; count++) {
            boolean[] visited = new boolean[relation[0].length];
            findCandidate(0, 0, count, relation, visited);
        }


        return counts;
    }

    // 유일성 구하기
    private static void findCandidate(int start, int curCount, int selectCount, String[][] relation, boolean[] visited) {
        // 선택 개수와 현재 개수가 동일한 경우 전체 선택 완료
        if (curCount == selectCount) {
            // 중복되는 튜플 확인
            if (checkDuplicate(visited, relation) && checkMaxKey(visited)) counts++;
            return;
        }

        // 조합
        for (int column = start; column < relation[0].length; column++) {
            // 컬럼 사용안한 배열과 현재 방문 배열 안사용한 경우 체크
            if (!visited[column]) {
                visited[column] = true;
                findCandidate(column + 1, curCount + 1, selectCount, relation, visited);
                visited[column] = false;
            }
        }
    }

    private static boolean checkMaxKey(boolean[] visited) {
        StringBuilder builder = new StringBuilder();

        // 방문한 키 만들기
        for (int column = 0; column < visited.length; column++) {
            if (visited[column]) builder.append(column);
        }

        // 키 포함 여부 확인

        for (String key : keys) {
            boolean contain = true;
            for (int index = 0; index < key.length(); index++) {
                // 후보키 중 하나라도 포함하고 있지 않다면 후보키 될 수 있음
                if (!builder.toString().contains(String.valueOf(key.charAt(index)))) {
                    contain = false;
                    break;
                }
            }
            if (contain) return false;
        }

        keys.add(builder.toString());
        return true;
    }

    private static boolean checkDuplicate(boolean[] visited, String[][] relation) {
        Set<String> set = new HashSet<>();

        for (String[] tuple : relation) {
            StringBuilder builder = new StringBuilder();
            // 후보키 만들기
            for (int column = 0; column < tuple.length; column++) {
                if (visited[column]) {
                    builder.append(tuple[column]);
                }
            }
            set.add(builder.toString());
        }

        // 중복 없는 경우 유일성
        return set.size() == relation.length;
    }
}
