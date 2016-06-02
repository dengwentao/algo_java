package Challenges;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Dijkstra Algorithm
 * Shortest Path of Graph
 * Created by wentaod on 6/2/16.
 */
public class DijkstraAlgorithm {

    static public void main(String args[]) {
        final int F = Integer.MAX_VALUE / 10;
        int graph[][] = {
                {0,   7,    3,  1,  F,  F},
                {F,   0,    F,  F,  1,  F},
                {F,   2,    0,  2,  F,  F},
                {F,   F,    F,  0,  F,  2},
                {F,   F,    1,  F,  0,  4},
                {F,   F,    F,  F,  F,  0}};
        final int dist[] = {0, F, F, F, F, F};

        PriorityQueue<Integer> queue = new PriorityQueue<>(graph.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1] - dist[02];
            }
        });

        for(int i=0; i<graph.length; i++)
            queue.add(i);

        while(!queue.isEmpty()) {
            int index = queue.poll();
            for(int i=0; i<graph.length; i++) {
                if (i == index || graph[index][i] == F)
                    continue;
                int dis = graph[index][i] + dist[index];
                if (dis < dist[i]) {
                    dist[i] = dis;
                    queue.remove(i); // remove and then push into the queue so that it can be ordered to new position
                    queue.offer(i);
                }
            }
        }

        for (int dis : dist)
            System.out.println(dis);
    }
}
