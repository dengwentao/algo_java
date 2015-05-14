package com.leetcode;
import java.lang.String;
import java.util.*;


/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 The graph has a total of three nodes, and therefore contains three parts as separated by #.

 First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 Second node is labeled as 1. Connect node 1 to node 2.
 Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:

   1
  / \
 /   \
0 --- 2
     / \
     \_/

 */

class SolutionCG {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
        };

    //mappings between old and new nodes.
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null)
            return null;
        if(map.containsKey(node)) {
            return map.get(node);
        }

        UndirectedGraphNode n = new UndirectedGraphNode(node.label);
        map.put(node, n);

        for(UndirectedGraphNode nd : node.neighbors)
        {
            UndirectedGraphNode r = cloneGraph(nd);
            n.neighbors.add(r);
        }
        return n;
    }

    public UndirectedGraphNode init()
    {
        UndirectedGraphNode node0 = new UndirectedGraphNode(0);
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        node0.neighbors.add(node1);
        node0.neighbors.add(node2);
        node1.neighbors.add(node0);
        node1.neighbors.add(node2);
        node2.neighbors.add(node0);
        node2.neighbors.add(node1);
        node2.neighbors.add(node2);
        return node0;
    }

    HashSet<Integer> set = new HashSet<Integer>();

    public void display(UndirectedGraphNode graph, int indent)
    {
        if(set.contains(graph.label))
            return;
        set.add(graph.label);
        for(int i=0; i<indent; i++)
            System.out.print('\t');
        System.out.println(graph.label);
        for(UndirectedGraphNode node : graph.neighbors)
            display(node, indent+1);
    }

    public void test()
    {
        UndirectedGraphNode graph = init();
        UndirectedGraphNode graph2 = cloneGraph(graph);
        set.clear();
        display(graph2, 0);
        set.clear();
        display(graph2.neighbors.get(0), 0);
        set.clear();
        display(graph2.neighbors.get(1), 0);
    }
}

public class GraphClone {
    public static void main(String[] args) {
        // write your code here
        SolutionCG sol = new SolutionCG();
        sol.test();
    }
};

