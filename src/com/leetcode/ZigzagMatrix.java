package com.leetcode;
import java.util.*;

/**
 * Created by wentaod on 12/16/15.
 */
public class ZigzagMatrix {

    static void spiral(int[][] matrix) {
        peel(matrix, 0, 0, matrix.length, matrix[0].length);
    }

    // peel one layer from [r, c], matrix boundary size is row x col
    static void peel(int[][] matrix, int r, int c, int row, int col) {
        if(row<=0 || col<=0)
            return;
        for(int j=0; j<col; j++)
            System.out.print(matrix[r][c+j] + ", ");
        System.out.println();
        for(int i=1; i<row-1; i++)
            System.out.print(matrix[r+i][c+col-1] + ", ");
        System.out.println();

        if(row==1 || col==1)
            return;

        for(int j=col-1; j>=0; j--)
            System.out.print(matrix[r+row-1][c+j] + ", ");
        System.out.println();
        for(int i=row-2; i>0; i--)
            System.out.print(matrix[r+i][c] + ", ");
        System.out.println();

        peel(matrix, r+1, c+1, row-2, col-2);
    }

    static void zigzag(int[][] matrix) {
        visitSlice(matrix, 0, 0, true);
    }

    // visit one line starting from [r, c] with direction increasing or descending
    static void visitSlice(int[][] matrix, int r, int c, boolean inc) {
        if(r==matrix.length-1 && c==matrix[0].length-1) {
            System.out.print(matrix[r][c]);
            return;
        }
        if(inc) {
            int i=r, j=c;
            for(; i>=0 && j<matrix[0].length; i--, j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            if(i==-1) {
                r = 0;
                c = j;
            }
            if(j==matrix[0].length) {
                r = i + 2;
                c = matrix[0].length - 1;
            }
        }
        else {
            int i=r, j=c;
            for(; i<matrix.length && j>=0; i++, j--) {
                System.out.print(matrix[i][j] + ", ");
            }
            if(j==-1) {
                r = i;
                c = 0;
            }
            if(i==matrix.length) {
                r = matrix.length - 1;
                c = j + 2;
            }

        }
        System.out.println();
        visitSlice(matrix, r, c, !inc);
    }


    ///////  New Approach ///////

    static public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return result;

        int rDir[] = {0, 1, 0, -1}; // directions, e.g., when dir=0 row stays while column++.
        int cDir[] = {1, 0, -1, 0};
        int r = 0, c = 0, layer = 0, dir = 0; // start at [0, 0] on layer 0, dir = 0
        while (result.size() < matrix.length * matrix[0].length) {
            result.add(matrix[r][c]);
            r += rDir[dir]; // move to the next according to current direction
            c += cDir[dir];
            if (!validate(r, c, layer, dir, matrix)) { // if we moved into an invalid state
                r -= rDir[dir]; // one step back
                c -= cDir[dir];
                dir ++; // use the next direction
                if (dir == 4) {
                    layer ++; // go to next layer
                    dir = 0;
                }
                r += rDir[dir]; // continue on this correct direction
                c += cDir[dir];
            }
        }
        return result;
    }

    // layer is used to calculate validation
    // when going up we want to avoid going into the start point of this layer
    static private boolean validate(int r, int c, int layer, int dir, int[][] matrix) {
        return (dir != 3 && r >= 0 + layer || dir == 3 && r >= 1 + layer)
                && r < matrix.length - layer && c >= 0 + layer && c < matrix[0].length - layer;
    }


    static public List<Integer> zigzagOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return result;

        int rDir[] = {-1, 1};
        int cDir[] = {1,  -1};
        int r = 0, c = 0, dir = 0;

        while (result.size() < matrix.length * matrix[0].length) {
            result.add(matrix[r][c]);
            r += rDir[dir];
            c += cDir[dir];
            if (!(0 <= r && r < matrix.length && 0 <= c && c < matrix[0].length)) {
                r -= rDir[dir];
                c -= cDir[dir];
                if (dir == 0) { // up
                    if (c == matrix[0].length - 1)
                        r += 1; // this should be valid, because while loop will terminate upon finishing
                    if (r == 0)
                        c += 1;
                    dir = 1;
                } else {
                    if (r == matrix.length - 1)
                        c += 1;
                    if (c == 0)
                        r += 1;
                    dir = 0;
                }
            }
        }

        return result;
    }

    static void diagonalOrder(int matrix[][]) {

        // There will be ROW + COL - 1 lines in the output
        for (int line = 1; line <= (matrix.length + matrix[0].length - 1); line ++) {

            // Get column index of the first element in this line of output.
            // The index is 0 for first ROW lines and line - ROW for remaining lines
            int start_col = Math.max(0, line - matrix.length);

            // Get count of elements in this line. The count
            // of elements is equal to minimum of line number,
            // COL-start_col and ROW
            int count = Math.min(line, Math.min((matrix[0].length - start_col), matrix.length));

            // Print elements of this line
            for (int j = 0; j < count; j++)
                System.out.print(matrix[Math.min(matrix.length, line) - j - 1]
                        [start_col + j] + " ");

            // Print elements of next diagonal on next line
            System.out.println();
        }
    }

    public static void main(String args[]) {
        int[][] matrix = {  {1, 2, 3, 4},
                            {4, 5, 6, 7},
                            {2, 5, 1, 6},
                            {9, 3, 2, 7},
                            {0, 2, 1, 6},
                            {5, 6, 7, 8}};

        for (int x : zigzagOrder(matrix))
            System.out.print(x + ", ");
        System.out.println();
        diagonalOrder(matrix);
        System.out.println();
        for (int x : spiralOrder(matrix))
            System.out.print(x + ", ");
    }
}
