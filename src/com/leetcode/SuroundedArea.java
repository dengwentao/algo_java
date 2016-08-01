package com.leetcode;
import java.util.*;

/**
 * Surrounded Regions
 * Created by wedeng on 2/16/15.
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

 A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
class SuroundedAreaSolution {
    char[][] board;
    boolean[][] visited;

    public void solve(char[][] board) {
        if(board.length < 3 || board[0].length < 3)
            return;

        this.board = board;
        this.visited = new boolean[board.length][board[0].length];

        for (int c = 0; c < board[0].length; c++) {
            if (board[0][c] == 'O' && !visited[0][c])
                bfs(0, c);
            if (board[board.length - 1][c] == 'O' && !visited[board.length - 1][c])
                bfs(board.length - 1, c);
        }
        for (int r = 0; r < board.length; r++) {
            if (board[r][0] == 'O' && !visited[r][0])
                bfs(r, 0);
            if (board[r][board[0].length - 1] == 'O' && !visited[r][board[0].length - 1])
                bfs(r, board[0].length - 1);
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 'O' && !visited[r][c]) {
                    board[r][c] = 'X';
                }
            }
        }
    }

    void bfs(int r, int c) {

        class Pair {
            int r;
            int c;
            Pair(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (pair.r - 1 >= 0 && board[pair.r - 1][pair.c] == 'O' && !visited[pair.r - 1][pair.c]) {
                q.add(new Pair(pair.r - 1, pair.c));
                visited[pair.r - 1][pair.c] = true;
            }
            if (pair.r + 1 < board.length && board[pair.r + 1][pair.c] == 'O' && !visited[pair.r + 1][pair.c]) {
                q.add(new Pair(pair.r + 1, pair.c));
                visited[pair.r + 1][pair.c] = true;
            }
            if (pair.c - 1 >= 0 && board[pair.r][pair.c - 1] == 'O' && !visited[pair.r][pair.c - 1]) {
                q.add(new Pair(pair.r, pair.c - 1));
                visited[pair.r][pair.c - 1] = true;
            }
            if (pair.c + 1 < board[0].length && board[pair.r][pair.c + 1] == 'O' && !visited[pair.r][pair.c + 1]) {
                q.add(new Pair(pair.r, pair.c + 1));
                visited[pair.r][pair.c + 1] = true;
            }
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
