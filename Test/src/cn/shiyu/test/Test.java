package cn.shiyu.test;

//合根植物
public class Test {
    public int[] parent;
    public int[] rank;

    Test(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    Test() {
        this(16);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (qRoot == pRoot)
            return;
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = parent[qRoot];
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = parent[pRoot];
        } else {
            parent[qRoot] = parent[pRoot];
            rank[pRoot] += 1;
        }
    }

    public boolean isCon(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        if (p > parent.length - 1 || p < 0)
            throw new IllegalArgumentException("out");
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
