package cn.shiyu.Linear;

import java.util.Arrays;

/**
 * 稀疏数组,压缩数组,当很多个值相同时,或者都为0
 * 开稀疏数组,三列,如下存储行列坐标及其值
 * <p>
 * 行  列   值
 * 2   3    5
 * 3   4    6
 */
public class SparseArray {
    public static int[][] ToSparseArray(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    sum++;//统计非0数字
                }
            }
        }
        int[][] res = new int[sum + 1][3];
        res[0][0] = arr.length;//记录行数
        res[0][1] = arr[0].length;//记录列数
        res[0][2] = sum;//记录非0值的数量
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    res[count][0] = i;
                    res[count][1] = j;
                    res[count][2] = arr[i][j];
                }
            }
        }
        return res;
    }

    public static int[][] ToTwoArray(int[][] arr) {
        int[][] result = new int[arr[0][0]][arr[0][1]];
        for (int i = 1; i < arr.length; i++) {//第一行存的是元数据,源数组的长度
            // 宽度,以及有多少个需要存储的值
            result[arr[i][0]][arr[i][1]] = arr[i][2];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr = new int[10][10];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (Math.random() < 0.6)
                    arr[i][j] = 3;
            }
        }
        int[][] s1 = ToSparseArray(arr);
        for (int[] ints : arr) {
            for (int a : ints) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println("=====");

        for (int[] ints : s1) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("=====");

        int[][] end = ToTwoArray(s1);
        for (int i = 0; i < end.length; i++) {
            System.out.println(Arrays.toString(end[i]));
        }

    }
}
