package Challenges;
import java.util.*;

/**
 * Centered Interval Tree
 * Created by wentaod on 5/15/16.
 */
public class IntervalTree {
    static public class Interval {
        int start;
        int end;
        boolean left; // this interval goes to the left
        boolean right; // this interval goes to the right

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void print() {
            System.out.println(" (" + start + ", " + end + ") ");
        }
    }

    static public class IntvNode {
        int center;
        IntvNode left;
        IntvNode right;
        List<Interval> starts; // ordered by start point
        List<Interval> ends; // ordered by end point

        // input a list of intervals, construct an interval tree node with its subtree.
        public IntvNode(List<Interval> starts, List<Interval> ends) {
            int start = starts.get(0).start;
            int end = ends.get(ends.size() - 1).end;
            this.center = (end - start) / 2 + start;

            // mark if a interval is to the left or to the right of the center
            for (int i = starts.size() - 1; i >= 0; i--) {
                Interval intv = starts.get(i);
                if (intv.start > center)
                    intv.right = true;
                else
                    break;
            }
            for (Interval intv : ends) {
                if (intv.end < center)
                    intv.left = true;
                else
                    break;
            }

            // distribute the intervals into left/center/right according to the marks
            List<Interval> leftStarts = new ArrayList<>();
            List<Interval> rightStarts = new ArrayList<>();
            this.starts = new ArrayList<>();
            for (Interval itv : starts) {
                if (itv.left) {
                    leftStarts.add(itv);
                } else if (itv.right) {
                    rightStarts.add(itv);
                } else {
                    this.starts.add(itv);
                }
            }
            List<Interval> leftEnds = new ArrayList<>();
            List<Interval> rightEnds = new ArrayList<>();
            this.ends = new ArrayList<>();
            for (Interval itv : ends) {
                if (itv.left) {
                    leftEnds.add(itv);
                } else if (itv.right) {
                    rightEnds.add(itv);
                } else {
                    this.ends.add(itv);
                }
            }

            // clear states
            for (Interval itv : starts) {
                itv.left = false;
                itv.right = false;
            }

            // create left & right subtree
            if (!leftStarts.isEmpty())
                this.left = new IntvNode(leftStarts, leftEnds);
            if (!rightStarts.isEmpty())
                this.right = new IntvNode(rightStarts, rightEnds);
        }

        // output all intervals containing point x in this subtree
        public List<Interval> containPoint(int x) {
            List<Interval> results = new ArrayList<>();
            if (x < center) {
                for (Interval intv : starts) {
                    if (intv.start <= x)
                        results.add(intv);
                }
                if (left != null)
                    results.addAll(left.containPoint(x));
            } else {
                for (Interval intv : ends) {
                    if (intv.end >= x)
                        results.add(intv);
                }
                if (right != null)
                    results.addAll(right.containPoint(x));
            }
            return results;
        }

        // output all intervals intersecting interval (x, y) in this subtree
        public List<Interval> intersectInterval(int x, int y) {
            Set<Interval> results = new HashSet<>();
            results.addAll(containPoint(x));
            results.addAll(containPoint(y));
            results.addAll(insideInterval(x, y));
            return new ArrayList<>(results);
        }

        // output all intervals which is fully covered by interval (x, y)
        public List<Interval> insideInterval(int x, int y) {
            Set<Interval> results = new HashSet<>();
            for (Interval itv : starts) {
                if (itv.start >= x && y >= itv.end)
                    results.add(itv);
            }
            if (x <= center && left != null)
                results.addAll(left.insideInterval(x, y));
            if (y >= center && right != null)
                results.addAll(right.insideInterval(x, y));
            return new ArrayList<>(results);
        }
    }

    // input a list of intervals to create an interval tree.
    static public IntvNode createIntervalTree(List<Interval> input) {
        if (input == null || input.isEmpty())
            return null;

        input.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> starts = (List<Interval>)(((ArrayList<Interval>)input).clone());

        input.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        List<Interval> ends = input;

        return new IntvNode(starts, ends);
    }

    public static void main(String args[]) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(8, 8));
        list.add(new Interval(2, 5));
        list.add(new Interval(3, 3));
        list.add(new Interval(15, 18));
        list.add(new Interval(8, 15));
        list.add(new Interval(15, 15));
        list.add(new Interval(1, 4));
        list.add(new Interval(2, 7));
        list.add(new Interval(4, 7));

        IntvNode root = createIntervalTree(list);

        for (Interval l : root.containPoint(3)) {
            l.print();
        }

        System.out.println();
        for (Interval l : root.intersectInterval(3, 8)) {
            l.print();
        }

    }
}
