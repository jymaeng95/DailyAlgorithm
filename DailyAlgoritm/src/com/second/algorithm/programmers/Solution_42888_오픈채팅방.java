package com.second.algorithm.programmers;

import java.util.*;

public class Solution_42888_오픈채팅방 {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] rst = solution(record);

        Arrays.stream(rst).forEach(System.out::println);

    }

    private static String[] solution(String[] record) {
        Map<String, String> users = new HashMap<>();
        List<Log> logs = new ArrayList<>();

        for (String log : record) {
            StringTokenizer st = new StringTokenizer(log);

            String command = st.nextToken();
            String uid = st.nextToken();

            // Leave의 경우 닉네임이 들어오지 않는다.
            if (!command.equals("Leave")) {
                String nickname = st.nextToken();

                // 유저정보에 uid:nickname 명령어에 따라 저장
                if (command.equals("Enter")) {
                    if (users.containsKey(uid)) users.replace(uid, nickname);
                    else users.put(uid, nickname);

                    // 이력에 저장
                    logs.add(new Log(command, uid));
                } else {
                    users.replace(uid, nickname);
                }
            }
            else {
                logs.add(new Log(command, uid));
            }
        }

        // String 으로 변환
        return logs.stream().map(log -> {
            String nickname = users.get(log.getUid());
            String command = "들어왔습니다.";
            if (log.getCommand().equals("Leave")) command = "나갔습니다.";

            return nickname + "님이 " + command;
        }).toArray(String[]::new);
    }

    static class Log {
        private String command;
        private String uid;

        public Log(String command, String uid) {
            this.command = command;
            this.uid = uid;
        }

        public String getCommand() {
            return command;
        }

        public String getUid() {
            return uid;
        }
    }
}
