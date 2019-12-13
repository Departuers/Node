package cn.shiyu.Linear;

@SuppressWarnings("all")
public class LinkedMap<K, V> {
    private class Node<K, V> {
        Node next;
        K k;
        V v;

        public Node(K k, V v, Node next) {
            this.next = next;
            this.k = k;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    '}';
        }

        public Node(K k) {
            this(k, null, null);
        }

        public Node() {
            this(null, null, null);
        }
    }

    public Node dummyHead;
    public int size;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append("[" + cur.k + "," + cur.v + "]");
            cur = cur.next;
        }
        return "LinkedMap{" + sb +
                "size=" + size +
                '}';
    }

    public LinkedMap() {
        this.dummyHead = new Node();
        this.size = 0;
    }


    private Node getNode(K k) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.k.equals(k))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    public boolean contain(K k) {
        return getNode(k) != null;
    }

    //和链表增加元素很像，
    public void add(K k, V v) {
        Node node = getNode(k);
        if (node == null) {
            dummyHead.next = new Node(k, v, dummyHead.next);
            //最重要，新元素指向原来链表的第一个元素，再让虚拟头结点指向新元素，就插入到链表头部了。！！！
            size++;
        } else
            node.v = v;
    }

    public V set(K k, V v) {
        Node cur = getNode(k);
        if (cur != null)
            cur.v = v;
        return null;
    }

    /**
     * 和链表删除元素很像，找到需要删除元素的前一个元素，
     * 使该元素的前一个元素指向该元素的下一个元素。
     * 跳过该元素，就被删除，再维护size
     */


    public Node remove(K k) {
        Node node = dummyHead;
        while (node.next != null) {
            if (node.next.k.equals(k))
                break;
            node = node.next;
        }
        if (node.next != null) {
            Node cur = node.next;
            node.next = cur.next;
            cur = null;
            size--;
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedMap<Integer, String> list = new LinkedMap<Integer, String>();
        list.add(1, "shehui");
        list.add(4, "shehuiasedrlka");
        list.add(8, "sadlkjflask");
        System.out.println(list);
        list.remove(4);
        System.out.println(list);
        list.remove(8);
        System.out.println(list);
    }
}
