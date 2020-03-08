package cn.shiyu.tree.线段树;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * https://www.cnblogs.com/TheRoadToTheGold/p/6254255.html
 * 竞赛版本线段树
 */
public class segmentTree {
    static class Node {
        public int l, r, w, lazy;//l节点左边界,r右边界,w为节点值,
        // lazy为懒标记

        Node(int l, int r, int w) {
            this.l = l;
            this.r = r;
            this.w = w;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("l=").append(l);
            sb.append(", r=").append(r);
            sb.append(", w=").append(w);
            sb.append('}');
            return sb.toString();
        }
    }

    static Node[] tree;
    static int[] data = {3, 1, 4, 9, 6, 5};
    static Scanner sc = new Scanner(in);
    static int N;
    static int c = 0;

    /**
     * 建树,以tI为根建立l-r区间
     *
     * @param tI 根节点
     * @param l  左边界
     * @param r  右边界
     */
    static void build(int tI, int l, int r) {
        tree[tI] = new Node(l, r, 0);
        if (l == r) {
            tree[tI] = new Node(l, r, data[c++]);
            return;
        }
        int mid = l + (r - l) / 2;
        build(tI * 2, l, mid);
        build(tI * 2 + 1, mid + 1, r);
        tree[tI].w = tree[tI * 2].w + tree[tI * 2 + 1].w;
    }

    static int ans = 0;

    /**
     * 单点查询
     *
     * @param k     起点1
     * @param index 要查询的点
     */
    static void ask(int k, int index) {
        if (tree[k].l == tree[k].r) {
            ans = tree[k].w;//结果
            return;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2;
        if (index <= mid) ask(k * 2, index);
        else ask(k * 2 + 1, index);
    }

    /**
     * 单点增加
     * 单点修改同理!!!
     *
     * @param k     从k开始
     * @param index 单点
     * @param y     增加y
     */
    static void change(int k, int index, int y) {
        if (tree[k].l == tree[k].r) {
            tree[k].w += y;
            return;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2;
        if (index <= mid) change(k * 2, index, y);
        else change(k * 2 + 1, index, y);
        tree[k].w = tree[k * 2].w + tree[k * 2 + 1].w;
    }

    /**
     * 区间查询
     * 左边界ll必须小于rr右边界否则会报错
     * 区间查询,查询到一个节点的包含要查询的区间的一部分
     * 则分段继续查询,把要查询区间的左右部分,分别交给下一次递归
     *
     * @param k  起点
     * @param ll 左边界
     * @param rr 有边界
     */
    static void ask_some(int k, int ll, int rr) {
        if (tree[k].l >= ll && tree[k].r <= rr) {
            System.out.println(tree[k].w);
            ans += tree[k].w;
            return;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2;
        if (ll <= mid) ask_some(k * 2, ll, rr);
        if (rr > mid) ask_some(k * 2 + 1, ll, rr);
    }

    //标记下传
    static void down(int k) {
        tree[k * 2].lazy += tree[k].lazy;
        tree[k * 2 + 1].lazy += tree[k].lazy;
        tree[k * 2].w += tree[k].lazy * (tree[k * 2].r - tree[k * 2].l + 1);
        tree[k * 2 + 1].w += tree[k].lazy * (tree[k * 2 + 1].r - tree[k * 2 + 1].l + 1);
        tree[k].lazy = 0;
    }

    static void change_some(int k, int ll, int rr, int y) {
        if (tree[k].l >= ll && tree[k].r <= rr) {
            tree[k].w += (tree[k].r - tree[k].l + 1) * y;
            tree[k].lazy += y;
            return;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2;
        if (ll <= mid) change_some(k * 2, ll, rr, y);
        if (rr > mid) change_some(k * 2 + 1, ll, rr, y);
        tree[k].w = tree[k * 2].w + tree[k * 2 + 1].w;
    }

    public static void main(String[] args) {
        tree = new Node[4 * data.length + 1];
        N = data.length;
        build(1, 1, data.length);
        System.out.println(Arrays.toString(tree));
        for (int i = 1; i <= 6; i++) {
            ask(1, i);
            System.out.println(ans);
        }
        change_some(1, 3, 6, 10);
        System.out.println();
        for (int i = 1; i <= 6; i++) {
            ask(1, i);
            System.out.println(ans);
        }
    }

}
