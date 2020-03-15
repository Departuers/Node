package cn.shiyu.tree.线段树;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * https://www.cnblogs.com/TheRoadToTheGold/p/6254255.html
 * 竞赛版本线段树
 * 每个节点维护一个闭区间[l,r](l<=r)的信息
 * 根节点表示:[1,n]的信息
 * 如果l==r说明是叶子节点
 * 如果l<r就是内部结点,一定有左孩子和右孩子,
 * 其左节点为[1,(l+r)/2]右节点为[(l+r)/2+1,r]
 * <p>
 * 1代表根节点
 * 线段树处理的数列长度为n,则根节点区间为[1,n]
 * 节点数不超过2n
 * 证明:由于叶子节点存储单个元素,
 * 而完全二叉树第n层,有2^(n-1)个叶子节点,
 * 假设让完全二叉树的叶子节点第x层存储单个元素,
 * 根据二叉树每层节点数量的性质,
 * x+x/2+x/4+x/8+...收敛于2x
 * 但在存储时开4x空间保险,因为有可能多一层出来
 * <p>
 * 无穷
 * ∑   x/2^i    收敛于2x
 * i=0
 * <p>
 * 而线段树的深度也是log n
 * 对于单点修改O(log n),单点查询O(log n),找到叶子节点操作即可
 * 对于区间查询O(log n):找到符合条件的内部节点,拼接答案即可
 * (如查询[3,9],若没有这个区间拼接[3,5]+[6,9])
 *
 * 对于区间修改,如果对叶子节点进行(r-l)+1次修改,那还不如模拟
 * 所以进行懒标记,如果不查询某叶子节点,那就把内部节点大区间打上标签
 * 查询到那个叶子节点之前,进行标记下传
 *
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
     * 单点查询
     *
     * @param k     起点1
     * @param index 要查询的点
     */
    static int ask_one(int k, int index) {
        if ((tree[k].l == tree[k].r) && tree[k].r == index) {
            return tree[k].w;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2;
        if (index <= mid) return ask_one(k * 2, index);
        else return ask_one(k * 2 + 1, index);
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
            ans += tree[k].w;
            return;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2;
        if (ll <= mid) ask_some(k * 2, ll, rr);
        if (rr > mid) ask_some(k * 2 + 1, ll, rr);
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
     * @return 返回区间值
     */
    static int ask_someRe(int k, int ll, int rr) {
        if (tree[k].l >= ll && tree[k].r <= rr) {
            return tree[k].w;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2;
        if (rr <= mid) {
            return ask_someRe(k * 2, ll, rr);
        } else if (ll > mid) {
            return ask_someRe(k * 2 + 1, ll, rr);
        } else {
            return ask_someRe(k * 2, ll, mid) + ask_someRe(k * 2 + 1, mid + 1, rr);
        }
    }

    /**
     * 显然最好的写法
     *
     * @param k  起点
     * @param ll 左边界
     * @param rr 右边界
     * @return 区间值
     */
    static int ask_somelast(int k, int ll, int rr) {
        if (tree[k].l >= ll && tree[k].r <= rr) {
            return tree[k].w;
        }
        if (tree[k].lazy != 0) down(k);
        int mid = (tree[k].l + tree[k].r) / 2, ans = 0;
        if (ll <= mid) ans += ask_somelast(k * 2, ll, rr);
        if (rr > mid) ans += ask_somelast(k * 2 + 1, ll, rr);
        return ans;
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
            ask_some(1, 1, i);
            System.out.println(ans);
            ans = 0;
        }
        System.out.println();
        for (int i = 1; i <= 6; i++) {
            System.out.println(ask_somelast(1, 1, i));
        }
    }

}
