package cn.shiyu.tree;
//二分搜索树实现map
public class BSTMap<K extends Comparable<K>, V> {
    private class Node {
        Node left;
        Node right;
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public Node root;
    public int size;

    public void add(K k, V v) {
        root = add(root, k, v);
    }
//重点！！！！！！！！！！

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else {
            node.value = value;           //传来的新value传给node
        }
        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node;
        } else if (node.key.compareTo(key) < 0)
            return getNode(node.left, key);
        else {
            return getNode(node.right, key);
        }
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K k) {
        return getNode(root, k) == null ? null : getNode(root, k).value;
    }

    public void set(K key, V v) {
        Node node = getNode(root, key);
        if (node != null) {
            node.value = v;
        }
        System.out.println("不存在");
    }
    //todo 删除哎。。。。。。。。。。。。。。。。。

}
