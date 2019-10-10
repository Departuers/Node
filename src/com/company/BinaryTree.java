package com.company;

import java.util.Arrays;
import java.util.LinkedList;

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
//            integerLinkedList.add((int) (Math.random() * 400));
//        }
//
//        TreeNode binaryTree = BinaryTree.createBinaryTree(integerLinkedList);
//        BinaryTree.preOrderTraveral(binaryTree);
        System.out.println("----------------------");
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(23, 14, 123, 1412, null, 123, 144, 1415, 165));
        TreeNode binaryTree1 = BinaryTree.createBinaryTree(linkedList);
        BinaryTree.preOrderTraveral(binaryTree1);

    }
}