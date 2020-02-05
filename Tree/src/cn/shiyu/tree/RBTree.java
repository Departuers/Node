package cn.shiyu.tree;

import java.util.ArrayList;
import java.util.Stack;

//二分搜索树实现map
@SuppressWarnings("all")
public class RBTree<K extends Comparable<K>, V> {
    private class Node {
        Node left;
        Node right;
        K key;
        V value;
        int height;//树的高度

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    public Node root;
    public int size;

    public void add(K k, V v) {
        root = add(root, k, v);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //重点！应该在添加元素时维护树的平衡
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
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;//维护高度
        int balance = getBalanceFactor(node);//计算平衡因子
//        if (Math.abs(balance) > 1)
//            System.out.println("unbalaend");
        //平衡维护
        //LL
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        //RR
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //LR
        if (balance > 1 && getBalanceFactor(node.left) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balance < 1 && getBalanceFactor(node.left) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //中序遍历非递归实现
    public void midOrderByStack() {
        Stack<Node> stack = new Stack<Node>();
        Node node = stack.push(root);
        while (!stack.empty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            Node cur = stack.pop();
            System.out.println(cur.key);
            node = cur.right;
        }
    }

    //获取平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断是否是一颗二分搜索树，思路，二分搜索树中序遍历的结果是从小到大的，所以遍历一遍把key值放进ArrayList里面
    //再遍历一遍看是不是从小到大排序的
    private boolean isBST() {
        ArrayList<K> keys = new ArrayList<K>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        }
        return true;
    }

    /**
     * 左旋转
     *
     * @param y 旧的根节点
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //颜色翻转
    private void flipColors(Node node) {

    }

    /**
     * 右旋转
     *
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 判断这个二叉树是不是平衡二叉树
     *
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 递归判断是否是平衡二叉树
     * 判断左节点的平衡因子，判断右节点的平衡因子，递归判断
     *
     * @param node
     * @return
     */
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
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
        root = remove(root, k);
    }

    private Node remove(Node node, K k) {
        if (node == null) {
            return null;
        }
        Node retNode;
        if (k.compareTo(node.key) > 0) {
            node.right = remove(node.right, k);
            retNode = node;
        } else if (k.compareTo(node.key) < 0) {
            node.left = remove(node.left, k);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightnode = node.right;
                node.right = null;
                size--;
                retNode = rightnode;
            }
            if (node.right == null) {
                Node leftnode = node.left;
                node.left = null;
                size--;
                retNode = leftnode;
            }
            Node successor = minNum(node.right);
            successor.right = remove(node.right, successor.key);
            successor.left = node.left;

            node.left = node.right = null;
            retNode = successor;
        }
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;//维护高度
        int balance = getBalanceFactor(retNode);//计算平衡因子
//        if (Math.abs(balance) > 1)
//            System.out.println("unbalaend");
        //平衡维护
        //LL
        if (balance > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        //RR
        if (balance < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        //LR
        if (balance > 1 && getBalanceFactor(retNode.left) > 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balance < 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;

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
        Node a = cur;
        return cur;
    }
}
