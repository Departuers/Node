package cn.shiyu.tree;

/**
 * 第一版并查集
 * Quick Find查询连接   时间复杂度O(1)
 * Union 合并时间   复杂度元素O(n)
 */
public class UnionFind1 implements UF {
    /**
     * id代表编号，数组下标索引代表元素编号
     * 存储的值代表存储在哪个集合
     */
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;//元素对应集合编号不一样，每个元素所属不同集合，都不相连
        }
    }


    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID)
            return;
        //遍历整个，如果元素所属p集合，就把它改成q集合
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("out");
        return id[p];
    }
}
