package com.algorithm.Programmers.Lv1;

import java.util.Stack;

public class Solution_64061 {
    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(solution(board,moves));
    }
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int index : moves){
            for(int i = 0; i<board.length;i++){
                if(board[i][index-1] != 0){
                    int doll = board[i][index-1];
                    board[i][index-1] = 0;
                    if(!stack.isEmpty() && stack.peek() ==  doll) {
                        stack.pop();
                        answer += 2;
                        break;
                    }
                    stack.push(doll);
                    break;
                }
            }
        }
        return answer;
    }
}
