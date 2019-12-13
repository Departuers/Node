package cn.shiyu.Linear;

public class LinkedList<E> {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(13);
        list.addLast(213);
        list.addLast(2123);

        System.out.println(list);
        list.remove(1);
        System.out.println(list);

    }

    private class Node {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }

    private Node dumHead;
    private int size;

    public LinkedList() {
        dumHead = new Node();
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node prev = dumHead.next;
        for (int i = 0; i < size; i++) {
            sb.append(prev.e);
            prev = prev.next;
            if (i != size - 1)
                sb.append(',');
        }
        sb.append(']').append('{');
        sb.append("size:" + size).append('}');
        return sb.toString();
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            System.out.println("索引错误");
        }
        Node prev = dumHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            System.out.println("索引错误");
        }
        Node prev = dumHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node temp = prev.next;
        prev.next = temp.next;
        size--;
        return temp.e;
    }


    public int getSize() {
        return size;
    }
}
