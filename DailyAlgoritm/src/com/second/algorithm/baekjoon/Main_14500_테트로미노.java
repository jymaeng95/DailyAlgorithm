package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = maxSum(board);
        System.out.println(rst);

        br.close();
    }

    private static int max;
    private static final Block[] blocks = {
            new Block(1, 0, 3), new Block(2, 3, 0), new Block(3, 1, 1),
            new Block(4, 1, 2), new Block(5, 2, 1)
    };
    private static final RemoveBlock[] removeRowBlocks = {
            new RemoveBlock(1, 0, 1, 1), new RemoveBlock(1, 1, 1, 2),
            new RemoveBlock(0, 0, 0, 1), new RemoveBlock(0, 1, 0, 2), // ㄱ자 모앙
            new RemoveBlock(0, 0, 0, 2), new RemoveBlock(1, 0, 1, 2), // ㅗ자 모양
            new RemoveBlock(1, 0, 0, 2), new RemoveBlock(0, 0, 1, 2)  // ㄱㄴ자 모양
    };
    private static final RemoveBlock[] removeColBlocks = {
            new RemoveBlock(1, 0, 2, 0), new RemoveBlock(1, 1, 2, 1),
            new RemoveBlock(0, 0, 1, 0), new RemoveBlock(0, 1, 1, 1), // ㄱ자 모앙
            new RemoveBlock(0, 1, 2, 1), new RemoveBlock(0, 0, 2, 0), // ㅗ자 모양
            new RemoveBlock(0, 1, 2, 0), new RemoveBlock(0, 0, 2, 1)  // ㄱㄴ자 모양
    };

    private static int maxSum(int[][] board) {
        max = 0;

        for (Block block : blocks) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (checkBoundary(row + block.getRowLength(), col + block.getColLength())) {
                        putBlock(row , col, block, board);
                    }
                }
            }
        }

        return max;
    }

    // 블럭 놓기
    private static void putBlock(int row, int col, Block block, int[][] board) {
        int size = 0;
        for (int r = row; r <= row + block.getRowLength(); r++) {
            for (int c = col; c <= col + block.getColLength(); c++) {
                size += board[r][c];
            }
        }

        // 가로로 놓인 ㄱ,ㄱㄴ, ㅗ 타입 블럭 사이즈 제거
        if(block.getType() == 4) {
            for (RemoveBlock removeRowBlock : removeRowBlocks) {
                max = Math.max(max, size - removeRowBlock.removeBlockSize(row, col, board));
            }
        }
        // 세로로 놓인 ㄱ, ㄱㄴ, ㅗ 타입 블럭 사이즈 제거
        else if(block.getType() == 5) {
            for (RemoveBlock removeColBlock : removeColBlocks) {
                max = Math.max(max, size - removeColBlock.removeBlockSize(row, col, board));
            }
        }
        else {
            max = Math.max(max, size);
        }
    }

    private static boolean checkBoundary(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    static class Block {
        private final int type;
        private final int rowLength;
        private final int colLength;

        public Block(int type, int rowLength, int colLength) {
            this.type = type;
            this.rowLength = rowLength;
            this.colLength = colLength;
        }

        public int getType() {
            return type;
        }

        public int getRowLength() {
            return rowLength;
        }

        public int getColLength() {
            return colLength;
        }
    }

    static class RemoveBlock {
        private final int firstRow;
        private final int firstCol;
        private final int secondRow;
        private final int secondCol;

        public RemoveBlock(int firstRow, int firstCol, int secondRow, int secondCol) {
            this.firstRow = firstRow;
            this.firstCol = firstCol;
            this.secondRow = secondRow;
            this.secondCol = secondCol;
        }

        // 제거할 블럭 사이즈
        public int removeBlockSize(int row, int col, int[][] board) {
            return board[row + this.firstRow][col + this.firstCol] + board[row + this.secondRow][col + this.secondCol];
        }
    }
}
