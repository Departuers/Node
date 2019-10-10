package cn.shiyu.Linear;

public class Lined<T> {
    private Node dummyhead;
    private int size;

    public Lined() {
        dummyhead = new Node();
        size = 0;
    }

    public int getsize() {
        return size;
    }

    public void add(int index, T elemnet) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("index out");
        }
        Node prev = dummyhead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(elemnet, prev.next);
        size++;
    }

    public void addLast(T element) {
        add(size, element);
    }

    public T find(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index out");
        }
        Node prev = dummyhead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        return prev.data;
    }

    public T getLast() {
        return find(size - 1);
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index out");
        }
        Node prev = dummyhead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node temp = prev.next;
        prev.next = temp.next;
        size--;
        temp.next = null;
        return temp.data;
    }

    private class Node {
        public T data;
        public Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyhead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        Lined<String> lined = new Lined<String>();
        lined.addLast("niah");
        lined.addLast("buhao");
        lined.addLast("ddddd");
        lined.addLast("ccccc");
        System.out.println(lined.getLast());
        System.out.println(lined);
        lined.remove(3);
        System.out.println(lined);
        System.out.println(lined.getLast());
        lined.remove(0);
        System.out.println(lined);
    }
}
