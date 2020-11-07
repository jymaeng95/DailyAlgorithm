package com.algorithm.Programmers.Lv2;

public class Solution_42839 {
    public static int count=0;
    public static void main(String[] args) {
        System.out.println(solution("011"));
    }
    public static int solution(String numbers) {
        int answer = 0;
        int[]arr = new int[numbers.length()];
        boolean[] visited = new boolean[numbers.length()];
        for(int i=0;i<numbers.length();i++){
            arr[i] = Integer.parseInt(String.valueOf(numbers.charAt(i)));
        }
        int r = arr.length;
        while(r>0){
            combination(arr,visited,0,arr.length,r);
            r--;
        }
        answer = count;
        return answer;
    }

    public static void combination(int[] arr, boolean[] visited, int depth, int n, int r){
        if(r==0) {
            isPrime(arr,visited,n);
            return;
        }
        if(depth == n)
            return;
        visited[depth] = true;
        combination(arr,visited,depth+1,n,r-1);

        visited[depth] = false;
        combination(arr,visited,depth+1,n,r);
    }
    public static void isPrime(int[] arr, boolean[] visited, int n){
        String num = "";
        for(int i=0;i<n;i++){
            if(visited[i]){
                System.out.print(arr[i] +" ");
                num += Integer.toString(arr[i]);
            }
        }
        System.out.println();
        boolean flag = true;
        int combNum = Integer.parseInt(num);
//        System.out.println(combNum);
        for(int i=2;i*i<=combNum;i++){
            if(combNum%i==0){
                flag = false;
            }
            if(flag)
                count++;
        }
    }
}
