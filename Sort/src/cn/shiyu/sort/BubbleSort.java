package cn.shiyu.sort;

import cn.shiyu.utils.Common;

import java.util.Arrays;

// 冒泡排序
public class BubbleSort {
    public static int[] sortToSmall(int data[]) {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                count++;
                if (data[i] < data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
        System.out.println(count);
        return data;
    }

    public static int[] sortToBig(int data[]) {
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
        return data;
    }

    public static int[] sortToS(int data[]) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                int temp = 0;
                if (data[j] > data[j + 1]) {
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        return data;
    }

    public static void main(String[] args) {
        int[] ad = Common.random(10, 2000);
        sortToBig(ad.clone());
        System.out.println(Arrays.toString(ad));
    }
}