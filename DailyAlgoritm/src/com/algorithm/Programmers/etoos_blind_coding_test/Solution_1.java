//package com.algorithm.Programmers.etoos_blind_coding_test;
//import java.util.*;
//
//public class Solution_1 {
//    /*
//    더 많은 코스에서 경기를 완주한 선수의 순위가 더 높습니다.       0개수
//1-1. 경기를 완주한 코스의 수가 같다면 2번 조건을 따릅니다.
//더 어려운 코스에서 경기를 완주한 선수의 순위가 더 높습니다.
//2-1. 난이도는 1번 < 2번 < 3번 < 4번 < 5번 코스 순입니다.
//2-2. 각 선수가 완주한 코스 중 가장 어려운 코스를 비교했을 때, 더 어려운 코스를 완주한 선수의 순위가 더 높습니다.
//2-3. 예를 들어 A선수는 [1, 2, 3]번 코스에서 경기를 완주했고, B선수는 [2, 4, 5]번 코스에서 경기를 완주한 경우 A선수가 완주한 가장 어려운 코스는 3번 코스이고 B선수가 완주한 가장 어려운 코스는 5번 코스이므로 B선수의 순위가 더 높습니다.
//2-4. 완주한 가장 어려운 코스가 같다면 3번 조건을 따릅니다.
//각 선수가 획득한 메달 수에 따라 순위를 정합니다. 각 코스를 완주한 참가자 중 기록의 상위 1/12까지 금메달, 1/4까지 은메달, 1/2까지 동메달을 수여하며(소수점 올림), 완주한 시간(초)이 더 짧을수록 상위 기록입니다.
//3-1. 금메달 수가 더 많은 선수의 순위가 더 높습니다.
//3-2. 금메달 수가 같다면 은메달 수가 더 많은 선수의 순위가 더 높습니다.
//3-3. 은메달 수도 같다면 동메달 수가 더 많은 선수의 순위가 더 높습니다.
//3-4. 획득한 메달 수가 같다면 4번 조건을 따릅니다.
//코스별 완주 시간의 합이 더 적은 선수의 순위가 더 높습니다.
//4-1. 완주 시간의 합이 동일하다면 5번 조건을 따릅니다.
//이름이 사전 순으로 빠른 선수의 순위가 더 높습니다.
//5-1. 선수의 이름은 알파벳 소문자만 사용합니다.
//5-2. 동명이인은 없다고 가정합니다.
//     */
//    public static void main(String[] args) {
//        String[] record = {"jack:9,10,13,9,15","jerry:7,7,14,10,17","jean:0,0,11,20,0","alex:21,2,7,11,4","kevin:8,4,5,0,0","brown:3,5,16,3,18","ted:0,8,0,0,8","lala:0,12,0,7,9","hue:17,16,8,6,10","elsa:11,13,10,4,13"};
//        solution(record);
//    }
//
//    public static String[] solution(String[] record) {
//        String[] answer = {};
//        Map<String, List<Integer>> grade = new HashMap<>();
//        List<String> name = new ArrayList<>();
//        for(String s : record){
//            StringTokenizer st = new StringTokenizer(s,":");
//            String n = st.nextToken();
//            name.add(n);
//            grade.put(n,new ArrayList<>());
//            for(String score : st.nextToken().split(",")){
//                grade.get(n).add(Integer.parseInt(score));
//            }
//        }
//        sortGrade(grade,name);
//        System.out.println(grade);
//        return answer;
//    }
//
//    private static void sortGrade(Map<String, List<Integer>> grade, List<String> name) {
//        for(String n :  name) {
//
//        }
//    }
//}
