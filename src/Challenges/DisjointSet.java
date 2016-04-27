package Challenges;
import java.util.*;

/**
 * Created by wentaod on 4/26/16.
 */
public class DisjointSet {

    static class RankTree {
        RankTree parent;
        int rank;
        String value;

        // create a single node rank tree
        public RankTree(String value) {
            this.value = value;
            this.rank = 0;
            parent = this;
        }

        // merge two rank trees into one and return its root
        public static RankTree merge(RankTree tree1, RankTree tree2) {
            RankTree root1 = RankTree.find(tree1);
            RankTree root2 = RankTree.find(tree2);
            if(root1.rank < root2.rank) {
                root1.parent = root2;
                return root2;
            }
            else if(root1.rank > root2.rank) {
                root2.parent = root1;
                return root1;
            }
            else {
                root1.parent = root2;
                root2.rank++;
                return root2;
            }
        }

        // Given a rank tree node, find its root
        public static RankTree find(RankTree tree) {
            if(tree.parent == tree)
                return tree;
            tree.parent = find(tree.parent);
            return tree.parent;
        }

        public String getValue() {
            return value;
        }
    }

    Map<String, RankTree> map = new HashMap<>();

    public RankTree getNode(String value) {
        return map.get(value);
    }

    // generate a rank tree from a list of input strings, and return its root
    public RankTree generate(String[] list) {
        if(list.length == 0)
            return null;
        RankTree root = new RankTree(list[0]);
        map.put(list[0], root);

        for(int i=1; i<list.length; i++) {
            RankTree node = new RankTree(list[i]);
            map.put(list[i], node);
            root = RankTree.merge(root, node);
        }

        return root;
    }

    public static void main(String args[]) {
        String[] animal = {"cat", "dog", "lion"};
        String[] fish = {"shark", "carp", "earl"};
        String[] bird = {"swallow", "eagle", "lark"};

        DisjointSet dis = new DisjointSet();
        RankTree animalTree = dis.generate(animal);
        RankTree fishTree = dis.generate(fish);
        RankTree birdTree = dis.generate(bird);

        System.out.println(RankTree.find(dis.getNode("lion")).getValue());
        System.out.println(RankTree.find(dis.getNode("shark")).getValue());
        System.out.println(RankTree.find(dis.getNode("eagle")).getValue());
        RankTree mergedTree = RankTree.merge(dis.getNode("lion"), dis.getNode("swallow"));
        System.out.println(RankTree.find(dis.getNode("lion")).getValue());
        System.out.println(RankTree.find(dis.getNode("shark")).getValue());
        System.out.println(RankTree.find(dis.getNode("eagle")).getValue());
    }
}
