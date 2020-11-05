package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_42577 {
    public static void main(String[] args) {
        String[] phone_book ={"2","23","454545","123", "1195524421","119"};
        System.out.println(solution(phone_book));
    }
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        // Comparator를 통해 배열 길이 순으로 오름차순 정렬
        Comparator<String> comparator = (s1,s2) -> Integer.compare(s1.length(),s2.length());

        Arrays.sort(phone_book,comparator);

        for(int i=0;i< phone_book.length;i++){
            for(int j=i+1;j< phone_book.length;j++){
                if(phone_book[j].startsWith(phone_book[i])){
                    answer = false;
                    // break을 쓰면 for문 하나만 탈출 하므로 return
                    // 효율성 문제 해결
                    return answer;
                }
            }
        }
        return answer;
    }
}
