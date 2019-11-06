package cn.shiyu.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(13);
        bst.add(15);
        bst.add(10);
        bst.add(12);
        bst.add(11);
        bst.add(8);
        bst.add(9);
        bst.add(7);
        bst.add(14);
        bst.add(16);
        bst.add(6);
        bst.preOrderByStack();
        bst.remove(13);
        System.out.println();
        bst.preOrderByStack();
    }

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }


    /**
     * 前序遍历以node为根的二分搜索树, 递归算法
     * 如果传入的值不为空，就访问传入值的左孩子，然后再访问传入值的右孩子
     * 第一次访问该元素的时候打印该元素就是前序遍历，
     * 第二次访问该元素的时候打印该元素就是中序遍历，输出有序的
     * 第三次访问该元素的时候打印该元素就是后序遍历，用来手动释放内存,
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {

        if (node == null)
            return;
        System.out.print(node.e + "->");
        preOrder(node.left);
        preOrder(node.right);
    }

    public E minNum() {
        return minNum(root).e;
    }

    //非递归写法
    private Node minNumNR(Node cur) {
        while (cur.left != null)
            cur = cur.left;
        return cur;
    }

    //递归写法
    private Node minNum(Node cur) {
        if (cur.left == null)
            return cur;
        return minNum(cur.left);
    }

    public E removeMin() {
        E ret = minNum();
        root = removeMin(root);
        return ret;
    }

    /**
     * @param node 删除以node为根的二分搜索树的最小节点，
     * @return 返回删除节点后新的二分搜索树的根节点
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 前序遍历非递归写法
     * 先把root入栈，栈不为空作为white循环条件，
     * 再打印这个元素，出栈一个元素用一个引用接收出来的节点，再把那个元素的右孩子左孩子顺序入栈。
     */
    public void preOrderByStack() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.e + "->");
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.compareTo(e) == 0) {
            return true;
        } else if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 层序遍历（广度优先遍历）
     * 使用队列，放root进去，
     * while循环当队列为空时取消循环
     * 弹出队首
     * 弹出谁就把谁的左孩子右孩子顺序放进队列，
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.e);
            if (cur.left != null)
                queue.offer(cur.left);
            if (cur.right != null)
                queue.offer(cur.right);
        }
    }

    public void remove(E e) {
        root = reomve(root, e);
    }

    private Node reomve(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) > 0) {
            node.right = reomve(node.right, e);
            return node;
        } else if (e.compareTo(node.e) < 0) {
            node.left = reomve(node.left, e);
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

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

}
