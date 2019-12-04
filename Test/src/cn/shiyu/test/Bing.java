package cn.shiyu.test;

public class Bing {
    public int[] parent;

    public Bing(int size) {
        this.parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public boolean isCo(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
    }

    public int find(int index) {
        if (index < 0 || index >= parent.length)
            return -1;
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    public static void main(String[] args) {
        Bing bing = new Bing(32);
        bing.union(5, 4);

        bing.union(4, 3);

        bing.union(3, 2);
        bing.union(6, 5);
        bing.union(7, 3);

        for (int i : bing.parent) {
            System.out.print(i + "->");
        }
        System.out.println(bing.isCo(3, 5));
        for (int i : bing.parent) {
            System.out.print(i + "->");
        }
    }
}
