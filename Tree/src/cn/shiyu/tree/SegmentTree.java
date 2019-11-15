package cn.shiyu.tree;

//线段树
@SuppressWarnings("all")
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;

    public SegmentTree(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0,0,data.length-1);
    }
    //在treeIndex创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex,int l,int r) {

    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index > data.length || index < 0)
            throw new IndexOutOfBoundsException("数组下标越界");
        return data[index];
    }

    public int leftChild(int index) {
        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }

}
