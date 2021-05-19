package com.algorithm.Programmers.Lv1;

import java.util.Scanner;

public class Solution_12919_서울에서_김서방_찾기 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String next = scan.nextLine();
        System.out.println(next);
    }
    public static String solution(String[] seoul) {
        int index = 0;
        for(String s : seoul) {
            if(s.equals("Kim"))
                break;
            index++;
        }
        return "김서방은 "+index+"에 있다.";
    }
}
