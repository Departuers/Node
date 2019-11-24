package cn.shiyu.test;

//合根植物
public class Test {
    public static int[] parent;
    public static int[] rank;

    public static void main(String[] args) {
        parent = new int[12];
        rank = new int[12];
        for (int i = 0; i < parent.length; i++) {
            rank[i] = 1;
            parent[i] = i;
        }

    }

    public static boolean isCone(int p, int q) {
        return find(p) == find(q);
    }

    public static void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (rank[pRoot] < rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else {
            rank[pRoot] += 1;
            parent[pRoot] = qRoot;
        }
    }

    public static int find(int index) {
        if (index < 0 || index >= parent.length)
            throw new IllegalArgumentException("Index Out");
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
}
