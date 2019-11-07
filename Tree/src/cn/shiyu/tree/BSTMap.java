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

    public void remove(K k) {
         root = reomve(root, k);
    }

    private Node reomve(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (k.compareTo(node.key) > 0) {
            node.right = reomve(node.right, k);
            return node;
        } else if (k.compareTo(node.key) < 0) {
            node.left = reomve(node.left, k);
            return node;
        } else {
            if (node.left == null) {
                Node rightnode = node.right;
                node.right = null;
                size--;
                return rightnode;
            }
            if (node.right == null) {
                Node leftnode = node.left;
                node.left = null;
                size--;
                return leftnode;
            }
            Node successor = minNum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }

    }

    public Node minNum(Node cur) {
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public Node removeMin(Node cur) {
        if (cur == null)
            return null;
        while (cur.left != null) {
            cur = cur.left;
        }
        Node a=cur;
        return cur;
    }
}
