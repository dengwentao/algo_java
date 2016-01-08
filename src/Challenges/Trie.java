package Challenges;

/**
 * Created by wentaod on 1/7/16.
 */
public class Trie {
    // This trie has ^ as root.
    static public class Node {
        char c;
        boolean last;
        Node[] children;

        public Node(char c) {
            this.c = c;
            this.last = false;
            children = new Node[26];
        }

        // check if the word is in the trie under this node.
        public boolean hasWord(String word) {
            char[] chars = word.toCharArray();
            Node node = this;
            for(char x : chars) {
                node = node.children[x-'a'];
                if(node == null)
                    return false;
            }
            return node.last;
        }

        // insert the input word into subtree under current trie node.
        public void insert(String word) {
            if(word.isEmpty()) {
                last = true;
                return;
            }
            insert(word.toCharArray(), 0);
        }

        // insert the char array starting from index into subtree under current trie node.
        private void insert(char[] chars, int index) {
            Node node = children[chars[index] - 'a'];
            if (node == null) {
                node = new Node(chars[index]);
                children[chars[index] - 'a'] = node;
            }

            if(chars.length-1 == index)
                node.last = true; // current char is the last one
            else
                node.insert(chars, index+1);
        }

    }

    static public void main(String args[]) {
        Node root = new Node('^');
        String word = "banana";
        for(int i=0; i<word.length(); i++)
            root.insert(word.substring(i)); // insert all suffix
        root.insert("");
        System.out.println(root.hasWord(""));
        System.out.println(root.hasWord("na"));
        System.out.println(root.hasWord("ana"));
    }
}
