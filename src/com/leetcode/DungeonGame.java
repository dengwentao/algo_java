package com.leetcode;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 Notes:
 The knight's health has no upper bound.
 Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */

/* H(i, j) = min{H(i+1, j), H(i, j+1)}
  if(D[i][j] - H(i, j) > 0) return 0;
  else return H(i, j) - D[i][j] + 1;
    */
class SolutionGame {

    int[][] M;
    int[][] D;

    //returns minimum health points needed when entering position (i, j)
    int H(int i, int j)
    {
        if(i<0 || j<0 || i>=M.length || j>=M[0].length)
            return Integer.MAX_VALUE;
        if(M[i][j] != -1)
            return M[i][j];
        if(i==M.length-1 && j==M[0].length-1)
            return (D[i][j]>0 ? 1 : 1-D[i][j]);

        int h = Math.min(H(i+1, j), H(i, j+1));
        int n = D[i][j] - h;
        M[i][j] = n>=0 ? 1 : -n;

        return M[i][j];
    }

    public int calculateMinimumHP(int[][] dungeon) {
        D = dungeon;
        M = new int[dungeon.length][];
        for(int i=0; i<dungeon.length; i++) {
            M[i] = new int[dungeon[0].length];
            for(int  j=0; j<M[i].length; j++)
                M[i][j] = -1;
        }
        return H(0, 0);
    }

    public void test()
    {
        int[][] dungeon = {{-2, -3, 3}, {-5, 1, 1}, {10, 30, -5}};
        System.out.println(calculateMinimumHP(dungeon));
    }
}

public class DungeonGame {
    public static void main(String[] args) {
        // write your code here
        SolutionGame sol = new SolutionGame();
        sol.test();
    }
}
