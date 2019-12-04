package cn.shiyu.tree;

import java.util.ArrayList;
import java.util.Arrays;

//二叉堆最大堆
public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MaxHeap(int data) {
        this.data = new ArrayList<>(data);
    }

    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    //将任意数组整理成堆的形状
    public MaxHeap(E[] array) {
        data = new ArrayList<>();
        data.addAll(Arrays.asList(array));
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public int parent(int index) {
        if (index == 0) {
            System.out.println("该节点没有父节点");
            return -1;
        }
        return (index - 1) / 2;
    }

    public int leftchild(int index) {
        return index * 2 + 1;
    }

    public int rightchild(int index) {
        return index * 2 + 2;
    }

    //增加元素
    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {
        //根节点不能上浮,,,,,,索引为k的值和k的父节点的值,比父节点值大就交换元素
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    private void swap(int i, int d) {
        E e = data.get(i);
        data.set(i, data.get(d));
        data.set(d, e);
    }

    public E getMax() {
        if (data.isEmpty())
            throw new IllegalArgumentException("no data");
        return data.get(0);
    }

    public E extractMax() {
        E e = getMax();
        data.set(0, data.get(size() - 1));
        data.remove(size() - 1);
        siftDown(0);
        return e;
    }

    private void siftDown(int k) {
        while (leftchild(k) < size()) {
            int j = leftchild(k);
            if (j + 1 < size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                //j+1是k的右孩子，如果k有右孩子并且，右孩子比左孩子大，
                j = rightchild(k);//就把有孩子赋值给j
            }//data[j]是leftChild和rightChild节点中的最大值；
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            swap(k, j);
            k = j;
        }
    }

    //取出堆顶元素替换成e并下沉
    public E replace(E e) {
        E ret = getMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "data=" + data +
                '}';
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(12);
        maxHeap.add(123);
        System.out.println(maxHeap);
        maxHeap.add(542);
        System.out.println(maxHeap);
        maxHeap.add(253);
        System.out.println(maxHeap);
        maxHeap.add(743);
        System.out.println(maxHeap);
        maxHeap.add(131);
        System.out.println(maxHeap);
        maxHeap.extractMax();
        System.out.println(maxHeap);
        maxHeap.extractMax();
        System.out.println(maxHeap);
        MaxHeap<Integer> a = new MaxHeap<>(new Integer[]{21, 314, 13, 114, 2, 312, 312, 12231, 312, 312, 13, 15, 22, 3416, 4, 53, 45});
        System.out.println(a);
        a.add(168657);
        System.out.println(a);
    }
}
