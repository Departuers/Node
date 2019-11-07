package cn.shiyu.sort;

import cn.shiyu.utils.Common;

import java.util.Arrays;

/**
 * 快速排序双边循环法
 */
public class QuickSort {
    public static void Quick(int[] data, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition(data, startIndex, endIndex);
        Quick(data, startIndex, pivotIndex - 1);
        Quick(data, pivotIndex + 1, endIndex);
    }

    private static int partition(int[] data, int startIndex, int endIndex) {

        int pivot = data[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            while (left < right && data[right] > pivot) {
                right--;
            }
            while (left < right && data[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }
        data[startIndex] = data[left];
        data[left] = pivot;

        return left;
    }

    /**
     * 单边循环快速排序
     */
//    public static void QuickByOne(int[] data,int startIndex,int endIndex) {
//        if (startIndex>=endIndex){
//            int pivotIndex=partition(data,startIndex,endIndex);
//            QuickByOne(data,);
//        }
//    }
    public static void main(String[] args) {
        int[] a = Common.random(50, 200);
        Quick(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}