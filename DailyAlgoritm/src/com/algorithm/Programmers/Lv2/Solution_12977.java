package com.algorithm.Programmers.Lv2;

public class Solution_12977 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(solution(nums));
    }

//    private static int count;

    public static int solution(int[] nums) {
        int count = 0;

        for(int i=0;i<nums.length;i++){
            int sum = 0;
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++) {
                    sum = nums[i]+nums[j]+nums[k];
                    if(isPrime(sum))
                        count++;
                }
            }
        }
//        count = 0;
//        boolean[] visited = new boolean[nums.length];
//        combination(nums, visited, 0, nums.length, 3);

        return count;
    }

//    private static void combination(int[] nums, boolean[] visited, int start, int n, int r) {
//        if (r == 0) {
//            int sum = 0;
//            for (int i = 0; i < n; i++) {
//                if (visited[i])
//                    sum += nums[i];
//            }
//            if (isPrime(sum))
//                count++;
//        }
//
//        for(int i=start;i<n;i++){
//            visited[i] = true;
//            combination(nums, visited, i+1, n, r-1);
//            visited[i] = false;
//        }
//    }
//
    private static boolean isPrime(int sum) {
        for (int i = 2; i <= Math.sqrt(sum); i++) {
            if (sum % i == 0)
                return false;
        }
        return true;
    }
}
