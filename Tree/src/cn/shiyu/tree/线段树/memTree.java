package cn.shiyu.tree.线段树;

/**
 * 主席树
 * https://blog.csdn.net/williamsun0122/article/details/77871278
 * https://blog.csdn.net/chenxiaoran666/article/details/81501461
 * https://blog.csdn.net/WilliamSun0122/article/details/77885781?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 * https://blog.csdn.net/a_forever_dream/article/details/80450549
 * https://blog.csdn.net/ModestCoder_/article/details/90107874
 * https://blog.csdn.net/creatorx/article/details/75446472?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 * https://blog.csdn.net/Frocean/article/details/80888718?depth_1-utm_source=distribute.wap_relevant.none-task&utm_source=distribute.wap_relevant.none-task
 * https://blog.csdn.net/zearot/article/details/48299459
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
