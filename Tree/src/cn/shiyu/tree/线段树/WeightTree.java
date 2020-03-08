package cn.shiyu.tree.线段树;

/**
 * 权值线段树
 * https://www.cnblogs.com/young-children/p/11787493.html
 */
public class WeightTree {

    static int[] tree;
    static int[] weight;

    static void build(int l, int r, int rt, int pos) {
        if (l == r) tree[rt]++;
        else {
            int mid = mid(l, r);
            if (pos <= mid) build(l, mid, L(rt), pos);
            else build(mid + 1, r, R(rt), pos);
            tree[rt] = tree[L(rt)] + tree[R(rt)];
        }
    }

    static int L(int r) {
        return r << 1;
    }

    static int R(int r) {
        return r << 1 | 1;
    }

    static int mid(int l, int r) {
        return l + ((r - l) >> 1);
    }

    public static void main(String[] args) {

    }
}
