package cn.shiyu.tree;

//路径压缩到树只有2层
public class UnionFind6 implements UF {
    private int[] parent;
    private int[] rank;

    public UnionFind6(int size) {
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
            parent[qRoot] = pRoot;//挂到元素高度高的位置上去
        } else {
            parent[qRoot] = pRoot;//右边代表父节点
            rank[pRoot] += 1;//树的真实高度不重要，重要的是挂在谁上面
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
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}