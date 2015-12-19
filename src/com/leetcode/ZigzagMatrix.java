package com.leetcode;

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


    public static void main(String args[]) {
        int[][] matrix = {  {1, 2, 3, 4},
                            {4, 5, 6, 7},
                            {2, 5, 1, 6},
                            {9, 3, 2, 7},
                            {0, 2, 1, 6},
                            {5, 6, 7, 8}};

        zigzag(matrix);
        System.out.println();
        spiral(matrix);
    }
}
