package com.leetcode;

/**
 * Created by wedeng on 2/16/15.
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

 A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
//TODO: not passed yet.
class SuroundedAreaSolution {
    char[][] board;

    public void solve(char[][] board) {
        if(board.length==0 || board.length==1 || board[0].length==1)
            return;

        this.board = board;

        for(int i=0; i<board.length; i+=board.length-1)
            for(int j=0; j<board[0].length; j++)
                checkSurrounded(i, j);

        for(int j=0; j<board[0].length; j+=board[0].length-1)
            for(int i=0; i<board.length; i++)
                checkSurrounded(i, j);

        for(int i=0; i<board.length; i++)
            for(int j=0; j<board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '1')
                    board[i][j] = 'O';
            }
    }

    void checkSurrounded(int row, int col) {
        if(row<0 || row>=board.length || col<0 || col>=board[0].length)
            return;
        if(board[row][col]=='X')
            return;
        else if(board[row][col]=='1')
            return;
        else {
            board[row][col] = '1';
            checkSurrounded(row-1, col);
            checkSurrounded(row+1, col);
            checkSurrounded(row, col-1);
            checkSurrounded(row, col+1);
        }
    }

    public void test() {
        char[][] board =
               {{'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};

        solve(board);
        display(board);
        System.out.println();
    }

    void display(char[][] matrix) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class SuroundedArea {
    public static void main(String[] args) {
        SuroundedAreaSolution sol = new SuroundedAreaSolution();
        sol.test();
    }
}
