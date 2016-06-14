package com.leetcode;
import java.util.*;

/**
 * N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * Created by wentaod on 6/14/16.
 */
public class NQueens {
    static public class Solution {

        List<List<String>> solutions = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            if (n <= 0)
                return new ArrayList<>();

            int[][] chess = new int[n][n];
            solveNQueens(chess, 0);
            return solutions;
        }

        private void solveNQueens(int[][] chess, int row) {
            if (row == chess.length - 1) { // last row
                for (int col = 0; col < chess.length; col ++) {
                    if (chess[row][col] == -1)
                        continue;

                    chess[row][col] = 1;
                    List<String> solution = new ArrayList<>();
                    for (int r = 0; r < chess.length; r ++) {
                        StringBuilder sb = new StringBuilder(chess.length);
                        for (int c = 0; c < chess.length; c ++) {
                            sb.append(chess[r][c] == 1 ? 'Q' : '.');
                        }
                        solution.add(sb.toString());
                    }
                    solutions.add(solution);
                    return; // only one possible solution
                }
            }

            for (int col = 0; col < chess.length; col ++) {
                if (chess[row][col] == -1)
                    continue;
                // we'll put one in [row][col]
                int[][] clone = cloneChess(chess);
                for (int i = 0; i < chess.length; i ++) // whole row
                    clone[row][i] = -1;
                for (int i = row + 1; i < chess.length; i ++) // whole col
                    clone[i][col] = -1;
                for (int i = row, j = col; i < chess.length && j < chess.length; i ++, j ++) // right - down diog
                    clone[i][j] = -1;
                for (int i = row, j = col; i < chess.length && j >= 0; i ++, j --) // right - down diog
                    clone[i][j] = -1;

                clone[row][col] = 1;
                solveNQueens(clone, row + 1);
            }
        }

        private int[][] cloneChess(int[][] chess) {
            int[][] result = new int[chess.length][];
            for (int i=0; i<chess.length; i++)
                result[i] = chess[i].clone();
            return result;
        }

        public void test() {
            for (List<String> l : solveNQueens(4)) {
                for (String s : l) {
                    System.out.println(s + "\t");
                }
                System.out.println();
            }
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
