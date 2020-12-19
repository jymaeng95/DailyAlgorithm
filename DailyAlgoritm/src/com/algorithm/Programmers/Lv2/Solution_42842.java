package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class Solution_42842 {
    public static void main(String[] args) {
        int brown = 14;
        int yellow = 6;
        solution(brown,yellow);
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        List<Integer> measure = new ArrayList<>();
        //카펫의 가로 * 세로가 될 수 있는 경우의 수 (약수)
        for(int i=1;i<=brown+yellow;i++){
            if((brown+yellow)%i==0){
                measure.add(i);
            }
        }

        int front = 0;
        int end = measure.size()-1;
        int mid = measure.size()/2;

        //가장 앞과 뒤 부터 가운데까지 접근해서 약수의 경우중에 중심이 되는 부분 구하기
        while(front <= mid && end >= mid){
            int column = measure.get(end)-2;
            int row = measure.get(front)-2;
            if (column * row ==  yellow){
                answer[0] = column+2;
                answer[1] = row+2;
                break;
            }
            front++;
            end--;
        }

        return answer;
    }
}
