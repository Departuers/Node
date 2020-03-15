package cn.shiyu.tree.线段树;

/**
 * 主席树
 * https://blog.csdn.net/ModestCoder_/article/details/90107874
 */
public class memTree {
    static class node {
        int l, r, w;

        public node(int l, int r, int w) {
            this.l = l;
            this.r = r;
            this.w = w;
        }
    }

    static node[] tree = new node[50000];
    static int cnt = 0;

    static void build(int k, int l, int r) {
        k = ++cnt;
        tree[k] = new node(0, 0, 0);
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(tree[k].l, l, mid);
        build(tree[k].r, mid + 1, r);
    }


    public static void main(String[] args) {

    }
}
