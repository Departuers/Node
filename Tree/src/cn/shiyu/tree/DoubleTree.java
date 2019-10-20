package cn.shiyu.tree;

import java.util.Arrays;

/**
 * 二叉堆，，，
 */
public class DoubleTree {
    /**
     * 上浮调整
     *
     * @param array
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    public static void DownAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            if (temp <= array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
            array[parentIndex] = temp;
        }
    }

    public static void buildHeap(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            DownAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 141, 151, 31, 2, 141, 12, 1, 53};
        upAdjust(array);
        System.out.println(Arrays.toString(array));
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
