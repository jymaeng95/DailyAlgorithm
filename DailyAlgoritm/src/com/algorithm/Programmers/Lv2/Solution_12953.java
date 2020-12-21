package com.algorithm.Programmers.Lv2;
//N개의 최소공배수
public class Solution_12953 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(solution(arr));
    }

    public static int solution(int[] arr) {
        int a = arr[0];
        for(int i=1;i<arr.length;i++){
            int b = arr[i];
            int r = 1;
            int tmp1=a,tmp2=b;
            if(a<b){
                tmp1 = b;
                tmp2 = a;
            }
            while(r>0){
                r = tmp1 % tmp2;
                tmp1 = tmp2;
                tmp2 = r;
            }
            a = a * b / tmp1;
        }
        return a;
    }
}
