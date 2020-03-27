package cn.shiyu.tree.线段树;

public class sgTree {
    static int[] sum = new int[100005];
    static int[] a = new int[100005];

    static void build(int l, int r, int x) {
        if (l == r) {
            sum[x] = a[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, x << 1);
        build(mid + 1, r, x << 1 | 1);
        update(x);
    }

    static void update(int x) {
        sum[x] = sum[x << 1] + sum[x << 1 | 1];
    }

    static int query(int ll, int rr, int l, int r, int x) {
        if (ll <= l && r <= rr) {
            return sum[x];
        }
        int mid = (l + r) >> 1, ans = 0;
        if (ll <= mid) ans += query(ll, rr, l, mid, x << 1);
        if (mid < rr) ans += query(ll, rr, mid + 1, r, x << 1 | 1);
        return ans;
    }

    //pos位置更新为v
    static void change(int pos, int v, int l, int r, int x) {
        if (l == r) {
            sum[x] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (pos <= mid) change(pos, v, l, mid, x << 1);
        else change(pos, v, mid + 1, r, x << 1 | 1);
        update(x);
    }

    public static void main(String[] args) {

    }
}
