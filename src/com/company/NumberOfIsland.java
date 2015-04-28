package com.company;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by WentaoD on 4/11/15.
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */

class IslandSolution {
    class Position {
        int row;
        int col;
        Position(int r, int c) {row=r; col=c;}
    }

    public int numIslands(char[][] grid) { //null, 1 line, 1x1, 0 elements, all 0
        if(grid==null)
            return 0;

        int count = 0;
        for(int i=0; i<grid.length; i++)
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]=='1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        return count;
    }

    //BFS from position (col, row). Visit 4 neighbors.
    private void bfs(char[][] grid, int row, int col) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(row, col));
        while(!queue.isEmpty()) {
            Position pos = queue.poll();
            row = pos.row;
            col = pos.col;
            grid[row][col] = '0';
            if(row!=0 && grid[row-1][col] == '1') {
                queue.offer(new Position(row-1, col));
            }
            if(row!=grid.length-1 && grid[row+1][col] == '1') {
                queue.offer(new Position(row+1, col));
            }
            if(col!=0 && grid[row][col-1] == '1') {
                queue.offer(new Position(row, col-1));
            }
            if(col!=grid[0].length-1 && grid[row][col+1] == '1') {
                queue.offer(new Position(row, col+1));
            }
        }
    }

    //DFS from position (col, row). Visit 4 neighbors.
    private void dfs(char[][] grid, int row, int col) {
        grid[row][col] = 0;
        if(row!=0 && grid[row-1][col]=='1')
            dfs(grid, row-1, col);
        if(row!=grid.length-1 && grid[row+1][col]=='1')
            dfs(grid, row+1, col);
        if(col!=0 && grid[row][col-1]=='1')
            dfs(grid, row, col-1);
        if(col!=grid[0].length-1 && grid[row][col+1]=='1')
            dfs(grid, row, col+1);
    }

    public void test() {
        char[][] grid = {{'1','1','0','0','0'},
                         {'1','1','0','0','0'},
                         {'0','0','1','0','0'},
                         {'0','0','0','1','1'}};
        int count = numIslands(grid);
        System.out.println(count);
    }
}

public class NumberOfIsland {
    static public void main(String args[]) {
        IslandSolution sol = new IslandSolution();
        sol.test();
    }
}
