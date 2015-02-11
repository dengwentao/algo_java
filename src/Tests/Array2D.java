package Tests;

/**
 * Created by wedeng on 2/9/15.
 */
public class Array2D {

    static void display(int[][] map) {
        for(int[] r : map) {
            for(int c : r)
                System.out.print(c + " ");
            System.out.println();
        }
    }
    static public void main1(String[] args) {
        final int row = 3;
        final int col = 4;
        int[][] map;
        map = new int[row][];
        for(int i=0; i<row; i++) {
            map[i] = new int[col];
            for(int j=0; j<col; j++)
                map[i][j] = 1;
        }

        display(map);
    }

    static public void main2(String[] args) {
        int row = 3;
        int col = 4;
        int[][] map = new int[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++)
                map[i][j] = 2;
        }

        display(map);
    }

    static public void main(String[] args) {
        int[][] map = {{1,2}, {3,4}, {5,6}};
        display(map);
    }
}
