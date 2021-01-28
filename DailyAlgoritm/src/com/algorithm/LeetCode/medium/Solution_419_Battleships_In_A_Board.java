package com.algorithm.LeetCode.medium;

public class Solution_419_Battleships_In_A_Board {
    public static void main(String[] args) {
        char[][] board = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        int battleships = countBattleships(board);
        System.out.println(battleships);
    }
    public static int countBattleships(char[][] board) {
        if(board.length==0)
            return 0;
        int count =0 ;
        for(int i =0;i<board.length;i++){
            for(int j =0;j<board[i].length;j++){
                if(board[i][j] =='X'){
                    char top = (i > 0) ?  board[i-1][j] : '.';
                    char left = (j>0) ? board[i][j-1] : '.';
                    if(top == '.' && left =='.')
                        count ++;
                }
            }
        }
        return count;
    }
}
