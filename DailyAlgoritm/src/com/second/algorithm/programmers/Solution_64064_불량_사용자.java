package com.second.algorithm.programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution_64064_불량_사용자 {
    public static void main(String[] args) {

    }

    public int solution(String[] user_id, String[] banned_id) {
        boolean[] check = new boolean[user_id.length];
        String[] available = new String[banned_id.length];

        bannedSet = new HashSet<>();
        findBanUser(0, user_id, banned_id, check, available);

        return bannedSet.size();
    }

    private static Set<String> bannedSet;

    private static void findBanUser(int count, String[] user_id, String[] banned_id, boolean[] check, String[] available) {
        if (count == banned_id.length) {
            // 복사 후 정렬
            String[] sortTarget = new String[available.length];
            System.arraycopy(available, 0, sortTarget, 0, available.length);
            Arrays.sort(sortTarget);

            // 키값 만들기
            StringBuilder sb = new StringBuilder();
            for (String uid : sortTarget) sb.append(uid);

            // 중복 제거
            bannedSet.add(sb.toString());
            return;
        }

        for (int index = 0; index < user_id.length; index++) {
            // 길이가 똑같고, 불량 후보자이고, 아직 밴 안된 유저인 경우
            if (user_id[index].length() == banned_id[count].length()
                    && availableBanned(user_id[index], banned_id[count])
                    && !check[index]) {

                check[index] = true;
                available[count] = user_id[index];

                findBanUser(count + 1, user_id, banned_id, check, available);

                available[count] = "";
                check[index] = false;
            }
        }
    }

    // 정규표현식을 공부해서 이용해보자!
    private static boolean availableBanned(String uid, String bid) {
        for (int index = 0; index < uid.length(); index++) {
            if (bid.charAt(index) != '*' && uid.charAt(index) != bid.charAt(index)) return false;
        }

        return true;
    }
}
