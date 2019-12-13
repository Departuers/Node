package cn.shiyu.tree;

//近最优实现
public class UnionFind5 implements UF {
    private int[] parent;
    private int[] rank;

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }//初始并查集每个元素并无连接关系
    }

    //O(h)h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        //将元素少的树根节点，挂到元素多的节点树根上去
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[p] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    //O(h)h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //O(h)h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("out");
        while (p != parent[p]) {//相等代表它是个根节点
            parent[p] = parent[parent[p]];//p指向他的父节点的父节点,有可能会改变树的高度
            /**
             *在这里没有维护rank数组中树的高度,
             *主要是因为rank只需要知道谁相对来说比较高就可以了,
             *并不需要存储树真实高度
             */

            p = parent[p];
        }
        return p;
    }
}