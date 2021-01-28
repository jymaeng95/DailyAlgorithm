package com.algorithm.LeetCode.medium;

public class Solution_74_Search_A_2D_Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 15;
        System.out.println(searchMatrix(matrix,target));
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0] <= target && matrix[i][matrix[i].length-1]>= target) {
                row = i;
                break;
            }
        }
        for(int num : matrix[row]){
            if(target == num)
                return true;
        }
        return false;
    }
}
