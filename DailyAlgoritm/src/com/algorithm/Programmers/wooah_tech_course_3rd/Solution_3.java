package com.algorithm.Programmers.wooah_tech_course_3rd;

public class Solution_3 {
    public static void main(String[] args) {
        int money = 30000;
        String[] expected = {"H","H","H","H","H"};
        String[] actual = {"H","H","H","H","H"};
        System.out.println(solution(money, expected, actual));
    }
    public static int solution(int money, String[] expected, String[] actual) {
            int batting = 100;
            for(int i = 0; i < expected.length; i++){
                if(money == 0)
                    break;

                money -= batting;
                if(isResult(expected[i],actual[i])){
                    money += batting * 2;
                    batting = 100;
                    continue;
                }

                batting *= 2;
                if(money < batting)
                    batting = money;
            }
            return money;
    }
    public static boolean isResult(String expected, String actual) {
        if(expected.equals(actual)){
            return true;
        }
        return false;
    }
}
