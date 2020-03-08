package cn.shiyu.tree.前缀和;

import java.util.Arrays;

/**
 * https://www.cnblogs.com/-Ackerman/p/11162651.html
 */
public class qzh {
    /**
     * 静态区间求和
     * 最简单的求区间和
     *
     * @param data
     */
    static void querysum(int[] data) {
        int[] arr = new int[data.length];
        arr[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            arr[i] = arr[i - 1] + data[i];
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 求是否有子区间之和%m=x,输出所有符合条件的区间
     * 思路:
     */
    static void zx(int[] arr, int m, int x) {

    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 6, 7};
        querysum(arr);
    }
}