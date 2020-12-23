package com.algorithm.Programmers.Lv2;

public class Solution_12924 {
    public static void main(String[] args) {
        System.out.println(solution(15));
    }

    public static int solution(int n) {
        int answer = 0;
        for(int i=1;i<=n;i++){
            int sum = i;
            for(int j=i+1;j<=n;j++){
                if(sum == n) {
                    answer++;
                    break;
                }
                sum += j;
                if(sum > n)
                    break;
            }
        }
        return answer+1;
    }
}
