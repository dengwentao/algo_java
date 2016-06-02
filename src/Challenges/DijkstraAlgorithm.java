package Challenges;

import java.util.*;

/**
 * Dijkstra Algorithm
 * Shortest Path of Graph
 * Created by wentaod on 6/2/16.
 */
public class DijkstraAlgorithm {

    // search input array to find the min value index
    static int findShortestIndex(int dist[], Set<Integer> visited) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<dist.length; i++) {
            if(visited.contains(i))
                continue;
            if(min > dist[i]) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static public void main(String args[]) {
        final int F = Integer.MAX_VALUE;
        int graph[][] = {
                {0,   7,    3,  1,  F,  F},
                {F,   0,    F,  F,  1,  F},
                {F,   2,    0,  2,  F,  F},
                {F,   F,    F,  0,  F,  2},
                {F,   F,    1,  F,  0,  4},
                {F,   F,    F,  F,  F,  0}};
        final int dist[] = {0, F, F, F, F, F};
        Set<Integer> visited = new HashSet<>();

        while(visited.size() < graph.length) {
            int index = findShortestIndex(dist, visited);
            visited.add(index);
            System.out.println("-----" + index + "-----");
            for(int i=0; i<graph.length; i++) {
                if (visited.contains(i) || graph[index][i] == F)
                    continue;
                int dis = graph[index][i] + dist[index];
                if (dis < dist[i])
                    dist[i] = dis;
            }
        }

        for (int dis : dist)
            System.out.println(dis);
    }
}
