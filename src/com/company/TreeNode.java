package com.company;

import java.util.List;

public class TreeNode<T> {
    public T key;
    public TreeNode<T> parent;
    public List<TreeNode<T>> children;

    public TreeNode(T key, TreeNode<T> parent) {
        this.key = key;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "key为" + key + "->";
    }
}