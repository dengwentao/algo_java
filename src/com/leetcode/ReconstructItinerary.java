package com.leetcode;
import java.util.*;

/**
 * Created by wentaod on 11/16/16.
 */
public class ReconstructItinerary {
    static public class Solution {

        private Map<String, List<String>> itineraries = new HashMap<>(); // source to destinations mapping, destinations sorted.
        private Map<String, Integer> cityCount = new HashMap<>(); // for a city how many times it's a destination
        private List<String> path = new ArrayList<>(); // result
        private int total; // total count of tickets

        public List<String> findItinerary(String[][] tickets) {
            if (tickets == null || tickets.length == 0)
                return new ArrayList<String>();

            total = tickets.length + 1;
            populateItineraries(tickets);
            findPath("JFK");

            return path;
        }

        private void populateItineraries(String[][] tickets) {
            for (String[] it : tickets) {
                if (it != null && it.length == 2) {
                    List<String> destinations = itineraries.get(it[0]);
                    if (destinations == null) {
                        destinations = new LinkedList<String>();
                        itineraries.put(it[0], destinations);
                    }
                    destinations.add(it[1]);

                    String key = it[0] + '>' + it[1];
                    Integer count = cityCount.get(key);
                    if (count == null) {
                        count = 0;
                        cityCount.put(key, 0);
                    }
                    cityCount.put(key, count + 1);
                }
            }

            for (Map.Entry<String, List<String>> entry : itineraries.entrySet()) {
                Collections.sort(entry.getValue());
            }
        }

        // DFS the graph. If in one recursion all cities are visited, return the result.
        private boolean findPath(String start) {
            List<String> destinations = itineraries.get(start);
            if (destinations == null || destinations.isEmpty()) {
                if (total == 1) {
                    path.add(start); // we are on a dead end but all tickets are taken
                    return true;
                }
                return false;
            }

            path.add(start);
            total --;
            if (total == 0)
                return true;

            for (int i = 0; i < destinations.size(); i ++) {
                if (i > 0 && destinations.get(i) == destinations.get(i - 1)) // we don't need to repeat
                    continue;
                String key = start + '>' + destinations.get(i);
                int count = cityCount.get(key);
                if (count == 0)
                    continue;
                cityCount.put(key, count - 1);
                if (findPath(destinations.get(i)))
                    return true;
                else
                    cityCount.put(key, count);
            }

            path.remove(path.size() - 1);
            total ++;
            return false;
        }

        public void test() {
            String[][] tickets = {{"MEL","PER"},{"SYD","CBR"},{"AUA","DRW"},{"JFK","EZE"},{"PER","AXA"},{"DRW","AUA"},{"EZE","SYD"},{"AUA","MEL"},{"DRW","AUA"},{"PER","ANU"},{"CBR","EZE"},{"EZE","PER"},{"MEL","EZE"},{"EZE","MEL"},{"EZE","TBI"},{"ADL","DRW"},{"ANU","EZE"},{"AXA","ADL"}};
            System.out.println(findItinerary(tickets));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }

}
