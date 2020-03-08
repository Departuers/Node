package cn.shiyu.tree.线段树;

import java.util.Arrays;
import java.util.Scanner;


/**
 * https://www.cnblogs.com/Judge/p/9514862.html
 */
@SuppressWarnings("all")
public class zkwTree {
    int m = 0;
    int[] data;//源数组
    int[] min;//区间最小值
    int[] max;//区间最大值
    public int[] tree;//

    public zkwTree(int N) {
        Scanner sc = new Scanner(System.in);
        int m = 1;
        min = new int[4 * N];
        max = new int[4 * N];
        tree = new int[4 * N];
        for (int i = 0; i < 4 * N; i++) {
            min[i] = Integer.MAX_VALUE;
        }//只有这里需要初始化,

        for (; m <= N; m <<= 1) ;//找到第一个叶子
        this.m = m;
        for (int i = m + 1; i <= m + N; i++) {
            tree[i] = max[i] = min[i] = sc.nextInt();
        }

        for (int i = m - 1; i > 0; i--) {
            //对于二叉树,一个数x转化成2进制,x|1,如果x是偶数则x|1=x+1,如果x是奇数则x|1=x
            //任意一个节点的左孩子肯定是偶数,(从1开始),显然i<<1肯定是偶数,则肯定i<<1这个节点肯定是一个节点的左孩子
            //显然i<<1是节点i的左孩子,i<<1|1是该节点的右孩子
            tree[i] = tree[i << 1] + tree[i << 1 | 1];//区间求和,显然自底向上构造
            min[i] = Math.min(min[i << 1], min[i << 1 | 1]);//显然区间最小值需要初始化数组为INF
            max[i] = Math.max(max[i << 1], max[i << 1 | 1]);//区间最大值
        }
        //显然这里的区间最大值,还有区间最小值,这样写不支持修改操作
    }

    /**
     * 可修改版本
     *
     * @param data 传入数组建树
     * @param N
     */
    public zkwTree(int[] data, int N) {

        int m = 1;//必须初始化为1
        min = new int[4 * N];
        max = new int[4 * N];
        tree = new int[4 * N];

        for (; m <= N; m <<= 1) ;//找到第一个叶子
        this.m = m;
        int index = 0;
        for (int i = m + 1; i <= m + N; i++) {
            tree[i] = min[i] = max[i] = data[index++];
        }
        for (int i = m - 1; i > 0; i--) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];

            min[i] = Math.min(min[i << 1], min[i << 1 | 1]);
            min[i << 1] -= min[i];
            min[i << 1 | 1] -= min[i];

            max[i] = Math.max(max[i << 1], max[i << 1 | 1]);
            max[i << 1] -= max[i];
            max[i << 1 | 1] -= max[i];
        }

        this.data = data;
        //max[1]为整体最大值,min[1]为整体最小值
    }

    /**
     * index节点增加v
     * 单点修改需要引入差分数组
     *
     * @param index 第index个,从1开始
     * @param v
     */
    void update(int index, int v) {
        index += m;//算出该点的叶子节点位置
        min[index] += v;
        max[index] += v;
        int temp = 0;
        for (; index >= 1; index >>= 1) {
            tree[index] += v;//更新该节点的值
            temp = Math.min(min[index], min[index ^ 1]);//比较的是相邻孩子
            min[index] -= temp;
            min[index ^ 1] -= temp;
            min[index >> 1] += temp;

            temp = Math.max(max[index], max[index ^ 1]);
            max[index] -= temp;
            max[index ^ 1] -= temp;
            max[index >> 1] += temp;
        }
        //显然左孩子索引肯定都是偶数,index^1
        //如果index是偶数则index^1=index+1
        //如果index是奇数则index^1=index-1
    }

    //单点查询min
    int query_node(int x) {
        int ans = 0;
        for (x += m; x >= 1; x >>= 1) {
            ans += max[x];
        }
        return ans;
    }

    //单点查询求和
    int query_one(int x) {
        return tree[x + m];
    }

    void change(int x, int v) {
        tree[x = x + m] += v;
        while (x != 0) {
            tree[x >>= 1] = tree[x << 1] + tree[x << 1 | 1];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        zkwTree z = new zkwTree(arr, arr.length);

        System.out.println(Arrays.toString(z.tree));
        System.out.println(Arrays.toString(z.min));
        System.out.println(Arrays.toString(z.max));
        z.change(3, 6);
        System.out.println();
        System.out.println(Arrays.toString(z.tree));
        System.out.println(Arrays.toString(z.min));
        System.out.println(Arrays.toString(z.max));

        for (int i = 1; i <= 5; i++) {
            System.out.println(z.query_one(i));
        }
    }

}
