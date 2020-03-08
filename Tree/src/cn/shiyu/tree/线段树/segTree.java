package cn.shiyu.tree.线段树;

/**
 * 线段树
 * https://blog.csdn.net/huangzihaoal/article/details/81813454
 * 另一个版本
 */
public class segTree {
    static class Node {
        public int l, r, w, lazy;

        public Node(int l, int r, int w) {
            this.l = l;
            this.r = r;
            this.w = w;
        }

    }

    static Node[] tree;
    static int[] data = {4, 5, 7, 2, 3, 6, 8};
    static int c = 0;
    static int ans;

    static void build(int k, int l, int r) {
        tree[k] = new Node(l, r, -1);
        if (l == r) {
            tree[k] = new Node(l, r, data[c++]);
            return;
        }
        int mid = l + (r - l) / 2;
        build(k * 2, l, mid);
        build(k * 2 + 1, mid + 1, r);
        tree[k].w = tree[k * 2].w + tree[k * 2 + 1].w;
    }

    static void ask(int k, int i) {
        if (tree[k].l == tree[k].r) {
            ans = tree[k].w;
            return;
        }
        int mid = (tree[k].l + tree[k].r) / 2;
        if (i <= mid) ask(k * 2, i);
        else ask(k * 2 + 1, i);
    }

    static void change(int k, int i, int y) {
        if (tree[k].l == tree[k].r) {
            tree[k].w = y;
            return;
        }
        int mid = (tree[k].l + tree[k].r) / 2;
        if (i <= mid) change(k * 2, i, y);
        else change(k * 2 + 1, i, y);
        tree[k].w = tree[k * 2].w + tree[k * 2 + 1].w;
    }

    static void query(int k, int ll, int rr) {
        if (tree[k].l >= ll && tree[k].r <= rr) {
            System.out.println(tree[k].w);
            ans += tree[k].w;
            return;
        }
        int mid = (tree[k].l + tree[k].r) / 2;
        if (ll <= mid) query(k * 2, ll, rr);
        if (rr > mid) query(k * 2 + 1, ll, rr);
    }

    public static void main(String[] args) {
        tree = new Node[4 * data.length + 1];
        build(1, 1, data.length);

        for (int i = 1; i <= 7; i++) {
            ask(1, i);
            System.out.println(ans);
        }
        System.out.println();

        ans = 0;
        query(1, 3, 7);
        System.out.println(ans);
        System.out.println();


        for (int i = 1; i <= 7; i++) {
            ask(1, i);
            System.out.println(ans);
        }
    }
}
