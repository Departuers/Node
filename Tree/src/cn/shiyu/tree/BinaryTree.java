package cn.shiyu.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树，照着写了才发现不是二分搜索树
 */
public class BinaryTree {
    private static class TreeNode {
        int data;
        int size;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 把线性的链表转换成非线性的二叉树
     * 链表节点的顺序恰好是链表前序遍历的结果
     *
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode Node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if (data != null) {
            Node = new TreeNode(data);
            Node.leftChild = createBinaryTree(inputList);
            Node.rightChild = createBinaryTree(inputList);
        }
        return Node;
    }

    /**
     * 二叉树层序遍历，广度优先遍历，使用队列
     *
     * @param root
     */
    public static void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.println(treeNode.data);
            if (treeNode.leftChild != null) {
                queue.offer(treeNode.leftChild);
            }
            if (treeNode.rightChild != null) {
                queue.offer(treeNode.rightChild);
            }
        }
    }

    /**
     * 前序遍历非递归实现，使用栈
     *
     * @param root
     */
    public static void preOrderTraveralWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.data);
                stack.push(node);       //迭代访问节点左孩子，并入栈
                node = node.leftChild;
            }
            //如果节点没有左孩子则弹出栈顶节点，访问节点右孩子
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.rightChild;
            }
        }
    }

    /**
     * 二叉树的前序遍历的递归实现
     */
    public static void preOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树的中序遍历的递归实现
     */
    public static void inOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderTraveral(node.leftChild);
        System.out.println(node.data);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树的后序遍历的递归实现
     */
    public static void postOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
        System.out.println(node.data);
    }

    public static void main(String[] args) {
//        LinkedList<Integer> integerLinkedList = new LinkedList<>();
//        for (int i = 0; i < 40; i++) {
//            integerLinkedList.add(Common.random(20,399));
//        }
//
//        TreeNode binaryTree = BinaryTree.createBinaryTree(integerLinkedList);
//        BinaryTree.preOrderTraveral(binaryTree);
        System.out.println("----------------------");

        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, null, 5, 6, 7, 8));
        TreeNode binaryTree1 = BinaryTree.createBinaryTree(linkedList);
        BinaryTree.levelOrderTraversal(binaryTree1);
    }
}