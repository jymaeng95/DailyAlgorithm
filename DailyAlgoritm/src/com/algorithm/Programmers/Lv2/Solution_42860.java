package com.algorithm.Programmers.Lv2;

//프로그래머스 LV2(조이스틱)
public class Solution_42860 {

    public static void main(String[] args) {

        //M 12
//        solution("JEROEN");
//        solution("JAZ");
//        solution("JAN");
//        solution("AAA");
//        solution("ZZZZ");
//        solution("AGENDA");
//        solution("BBBAAAB");
        solution("ABAAAAAAAAABB");

    }

    public static int solution(String name) {
        return Math.min(minCountForward(name), minCountBackward(name));
    }

    private static int minCountBackward(String name) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(1, name.length())).append(name.charAt(0));
        sb = sb.reverse();

        for (char letter : sb.toString().toCharArray()) {
            if (letter != 'A') {
                // 아래 이동 혹은 위로 이동
                answer += Math.min(letter - 'A', 90 - letter + 1);
            }
            // 이동
            answer++;
        }
        answer -= getLastAcount(sb.toString());
        System.out.println(answer -1);
        return answer - 1;
    }

    private static int minCountForward(String name) {
        int answer = 0;
        for (char letter : name.toCharArray()) {
            if (letter != 'A') {
                // 아래 이동 혹은 위로 이동
                answer += Math.min(letter - 'A', 90 - letter + 1);
            }
            answer++;
        }

        answer -= getLastAcount(name);
        System.out.println(answer -1);
        return answer - 1;
    }

    private static int getLastAcount(String name) {
        int count = 0;
        for(int index = name.length()-1; index >= 0; index--) {
            if(name.charAt(index) != 'A') break;
            count++;
        }
        return count;
    }
}
