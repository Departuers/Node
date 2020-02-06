package cn.shiyu.tree;

import java.util.Arrays;

/**
 * https://www.bilibili.com/video/av69667943
 * 树状数组
 * https://www.luogu.com.cn/problemnew/solution/P3374
 * tree[0]其实是无意义的
 * 区间修改,单点查询,,,引入差分数组来维护
 */
public class TreeArraySome {
    private int n;//数组长度
    public int[] yuan;//源数组
    public int[] Cai;//差分数组,存储增量

    public TreeArraySome(int[] arr) {
        this.yuan = arr;
        this.Cai = new int[arr.length + 1];
        this.n = arr.length;
    }

    //与下面的lowbit操作相同
    public static int LowBit(int x) {
        return x & (-x);
    }

//    public static int lowbit(int x) {
//        return x - (x & (x - 1));
//    }

    /**
     * 区间修改
     *
     * @param index 在index位置添加
     * @param k     需要添加的值
     */
    private void SomeChange(int index, int k) {
        while (index <= n) {
            Cai[index] += k;
            index += LowBit(index);
        }
    }

    /**
     * 区间修改
     *
     * @param l 区间左边界
     * @param r 区间右边界
     * @param k 增量
     */
    public void SomeChange(int l, int r, int k) {
        SomeChange(l, k);
        SomeChange(r + 1, -k);
    }

    /**
     * 单点查询
     * index在这里不是索引,不是从0开始的,而是从1开始的
     *
     * @param index 前index个数的
     * @return 返回前index个数的和
     */
    public int getSum(int index) {
        int ans = 0;
        while (index != 0) {
            ans += Cai[index];
            index -= LowBit(index);
        }
        return ans;
    }

    /**
     * 单点查询其
     *
     * @param index 索引
     * @return 该点的值
     */
    public int Search(int index) {
        return yuan[index - 1] + getSum(index);
    }


    public static void main(String[] args) {
        int[] arr = {3, 4, 6, 8, 5, 1, 7, 9};
        TreeArraySome a = new TreeArraySome(arr);
        a.SomeChange(1, 7, 4);
        a.SomeChange(3, 8, 2);

        System.out.println(Arrays.toString(a.Cai));
        System.out.println(a.Search(4));
    }
}