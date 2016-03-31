package Challenges;
import java.util.*;

/**
 * Traveling Sales Person (TSP)
 Given an undirected weighted graph, such that cities are the graph's vertices, paths are the graph's edges, and a path's distance is the edge's length. What's the minimum cost of a path starting and finishing at a specified vertex after having visited each other vertex exactly once.
 * Created by wentaod on 3/29/16.
 */
public class TravelingSalesPerson {
    static final int X = Integer.MAX_VALUE >> 4;
    int[][] graph = {
            {0, 2, X, 2, 8, X},
            {2, 0, 2, 1, X, 2},
            {X, 2, 0, 1, X, X},
            {2, 1, 1, 0, 2, 2},
            {8, X, X, 2, 0, 8},
            {X, 2, X, 2, 8, 0}};
    char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F'};
    Map<Integer, Integer> dp = new HashMap<>();

    int key(Set<Character> visited, char node) {
        int key = 0;
        for(char c : visited)
            key += 1<<(c-'A');
        return key + ((node-'A') << 16);
    }

    int TSP(Set<Character> visited, char node) {
        if(visited.size()==1)
            return 0;

        int key = key(visited, node);
        if(key==196670)
            System.out.println("===============================");
        if(dp.containsKey(key))
            return dp.get(key);

        int min = X;
        Set<Character> remainder = new HashSet<>(visited);
        remainder.remove(node);
        for(char n : remainder) {
            if(graph[node-'A'][n-'A']==X)
                continue;
            int cost = TSP(remainder, n) + graph[node-'A'][n-'A'];
            if(min > cost)
                min = cost;
        }

        for(char n : visited)
            System.out.print(n + ",");
        System.out.println(" -> [" + node + "]: key=" + key + ", cost=" + min);

        dp.put(key, min);
        return min;
    }

    void test() {
        Set<Character> set = new HashSet<>();
        for(char n : nodes)
            set.add(n);
        System.out.println(TSP(set, 'F'));
    }

    public static void main(String args[]) {
        TravelingSalesPerson tsp = new TravelingSalesPerson();
        tsp.test();
    }
}
