package cn.shiyu.Linear;

public class LinkedStack<T> implements stack<T> {
    private Lined<T> lined;

    public LinkedStack() {
        lined = new Lined<T>();
    }

    @Override
    public int getsize() {
        return lined.getsize();
    }

    @Override
    public void push(T element) {

        lined.addLast(element);
    }

    @Override
    public T pop() {

        return lined.remove(getsize() - 1);
    }

    @Override
    public T peek() {

        return lined.getLast();
    }

    @Override
    public boolean isEmpty() {
        return lined.getsize() == 0;
    }

    @Override
    public String toString() {
        return lined.toString();
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<String>();
        linkedStack.push("element");
        linkedStack.push("1234ad");
        linkedStack.push("97340971sadfsd");
        linkedStack.push("afsadfsd");
        System.out.println(linkedStack);
        System.out.println(linkedStack.peek());
        linkedStack.pop();
        System.out.println(linkedStack);
        System.out.println(linkedStack.peek());
    }
}