package cn.shiyu.tree;

public class PriorityQueue<E extends Comparable<E>> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<E>();
    }

    public int getsize() {
        return maxHeap.size();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    public E getFront() {
        return maxHeap.getMax();
    }

    public void enQueue(E e) {
        maxHeap.add(e);
    }

    public E deQueue() {
        return maxHeap.extractMax();
    }
}
