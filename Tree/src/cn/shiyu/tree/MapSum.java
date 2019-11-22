package cn.shiyu.tree;

import java.util.TreeMap;
//Trie
public class MapSum {
    private class Node {
        int value;
        TreeMap<Character, Node> tree;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.tree.get(c) == null) {
                cur.tree.put(c, new Node());
            }
            cur.value = val;
        }
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.tree.get(c) == null)
                return 0;
            cur = cur.tree.get(c);
        }
        return sum(cur);
    }

    private int sum(Node node) {
        if (node.tree.size() == 0)
            return node.value;
        else {
            int res = node.value;
            for (char c : node.tree.keySet())
                res += sum(node.tree.get(c));
            return res;
        }
    }
}
