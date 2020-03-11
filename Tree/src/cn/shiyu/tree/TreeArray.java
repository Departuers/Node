package cn.shiyu.tree;

/**
 * https://www.bilibili.com/video/av69667943
 * 树状数组
 * https://www.luogu.com.cn/problemnew/solution/P3374
 * tree[0]其实是无意义的
 * 单点修改,单点查询,区间查询,
 */
public class TreeArray {
    private int n;//数组长度
    private int[] tree;

    public TreeArray(int[] arr) {
        this.n = arr.length;
        tree = new int[arr.length + 1];
        for (int i = 1; i < tree.length; i++) {
            add(i, arr[i - 1]);
        }
    }

    //与下面的lowbit操作相同
    public static int LowBit(int x) {
        return x & (-x);
    }

//    public static int lowbit(int x) {
//        return x - (x & (x - 1));
//    }

    /**
     * 单点修改
     *
     * @param index 在index位置添加
     * @param k     需要添加的值
     */
    public void add(int index, int k) {
        while (index <= n) {
            tree[index] += k;
            index += LowBit(index);
        }
    }

    /**
     * 区间查询
     *
     * @param l 区间左边界
     * @param r 区间右边界
     * @return 区间和
     */
    public int search(int l, int r) {
        if (l > r)
            return search(r, l);//如果左右边界填反了
        return getSum(r + 1) - getSum(l);
    }

    /**
     * 单点查询
     *
     * @param index 需要查询第index个数
     * @return 返回得到的第index个数
     */
    public int search(int index) {
        return getSum(index) - getSum(index - 1);
    }

    /**
     * 区间查询
     * index在这里不是索引,不是从0开始的,而是从1开始的
     *
     * @param index 前index个数的
     * @return 返回前index个数的和
     */
    public int getSum(int index) {
        int res = 0;
        while (index != 0) {
            res += tree[index];
            index -= LowBit(index);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {3, 4, 6, 8, 5, 1, 7, 9};
        TreeArray a = new TreeArray(arr);
        a.add(4, 3);
        System.out.println(a.search(2, 4));
    }
}