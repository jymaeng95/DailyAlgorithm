package com.second.algorithm.programmers;

import java.util.*;

public class Solution_64063_호텔_방_배정 {
    public static void main(String[] args) {
//        long k = (long) 1e12;
//        long[] room_number = {1, 3, 4, 1, 3, 1};
        long[] room_number = {1, 4, 6, 1, 2};
        long k = 10;
        long[] rst = solution(k, room_number);
        Arrays.stream(rst).forEach(System.out::println);
    }
    private static Map<Long, Long> roomMap;
    private static long[] solution(long k, long[] room_number) {
        roomMap = new HashMap<>();
        long[] infromation = new long[room_number.length];
        for (int index = 0; index < room_number.length; index++) {
            infromation[index] = findRoom(room_number[index]);
        }

        return infromation;
    }

    private static long findRoom(long number) {
        // 배정 안된 경우 다음 빈방을 넣어준다.
        if(!roomMap.containsKey(number)) {
            roomMap.put(number, number + 1);

            return number;
        }
        // 배정된 경우 빈 방을 넣어준다.
        long nextRoom = roomMap.get(number);
        long emptyRoom = findRoom(nextRoom);
        roomMap.put(number, emptyRoom);
        return emptyRoom;
    }
}
