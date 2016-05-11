package com.leetcode;

/**
 * Self Crossing
 * You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.
 Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
 * Created by wentaod on 5/9/16.
 */
public class SelfCrossing {
    static public class Solution {

        static class Horizontal {
            int y;
            int x1;
            int x2;
            public Horizontal(int y, int x1, int x2) {
                this.y = y;
                this.x1 = x1;
                this.x2 = x2;
            }
        }

        static class Vertical {
            int x;
            int y1;
            int y2;
            public Vertical(int x, int y1, int y2) {
                this.x = x;
                this.y1 = y1;
                this.y2 = y2;
            }
        }

        static class Point {
            int x;
            int y;
        }

        public boolean isSelfCrossing(int[] x) {
            if(x == null || x.length < 4)
                return false;

            Point point = new Point();
            Horizontal north = new Horizontal(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            Horizontal south = new Horizontal(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            Vertical west = new Vertical(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            Vertical east = new Vertical(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            Horizontal north2 = new Horizontal(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            Horizontal south2 = new Horizontal(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            Vertical west2 = new Vertical(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            Vertical east2 = new Vertical(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);

            int count = 0;
            for(int step : x) {
                count ++;
                int direction = count % 4;
                if(direction == 1) {
                    // to north
                    if (south.x1 <= point.x && south.x2 >= point.x) { // x falls in south boundary
                        // see if we meet south boundary
                        if (south.y >= point.y && south.y <= point.y + step) // before moving north, point is below south boundary, but after moving, point is above, then there must be a crossing.
                            return true;
                    }
                    if (north.x1 <= point.x && north.x2 >= point.x) { // same check on north boundary
                        // see if we meet north boundary
                        if (north.y >= point.y && north.y <= point.y + step)
                            return true;
                    }
                    if (east.x == point.x) { // check if overlapping with east boundary
                        if (point.y + step >= east.y1 && point.y + step <= east.y2) // if following right in east boundary after moving
                            return true;
                    }
                    east2 = new Vertical(point.x, point.y, point.y + step); // record east boundary
                    point.y += step; // update point
                    south = south2; // after going north is done,  south boundary is determined.
                } else if (direction == 2) {
                    // to west
                    if (east.y1 <= point.y && east.y2 >= point.y) {
                        if (east.x <= point.x && east.x >= point.x - step)
                            return true;
                    }
                    if (west.y1 <= point.y && west.y2 >= point.y) {
                        if (west.x <= point.x && west.x >= point.x - step)
                            return true;
                    }
                    if (north.y == point.y) {
                        if (north.x1 <= point.x - step && point.x - step <= north.x2)
                            return true;
                    }
                    north2 = new Horizontal(point.y, point.x - step, point.x);
                    point.x -= step;
                    east = east2;
                } else if (direction == 3) {
                    // to south
                    if (south.y <= point.y && south.x1 <= point.x && south.x2 >= point.x) {
                        // see if we meet south boundary
                        if (south.y >= point.y - step)
                            return true;
                    }
                    if (north.y <= point.y && north.x1 <= point.x && north.x2 >= point.x) {
                        // see if we meet north boundary
                        if (north.y >= point.y - step)
                            return true;
                    }
                    if (west.x == point.x) {
                        if (west.y1 <= point.y - step && point.y - step <= west.y2)
                            return true;
                    }
                    west2 = new Vertical(point.x, point.y - step, point.y);
                    point.y -= step;
                    north = north2;
                } else {
                    // to east
                    if (east.y1 <= point.y && east.y2 >= point.y) {
                        if (east.x >= point.x && east.x <= point.x + step)
                            return true;
                    }
                    if (west.y1 <= point.y && west.y2 >= point.y) {
                        if (west.x >= point.x && west.x <= point.x + step)
                            return true;
                    }
                    if (south.y == point.y) {
                        if (south.x1 <= point.x + step && point.x + step <= south.x2)
                            return true;
                    }
                    south2 = new Horizontal(point.y, point.x, point.x + step);
                    point.x += step;
                    west = west2;
                }
            }

            return false;
        }

        public void test() {
            int[] steps = {1, 1, 2, 1, 1};
            System.out.println(isSelfCrossing(steps));
        }

    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
