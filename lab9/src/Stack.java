public class Stack<T extends Comparable<T>> {
    private int size;
    private Node<T> node;
    private Node<T> top;
    private T data;
    private int stackSize = 0;

    public Stack(){
        size = 5;
        Node<T> node = new Node<T>(null, null);
    }

    public Stack(int size){
        this.size = size;
        Node<T> node = new Node<T>(null, null);
    }

    T pop()throws StackException {
        if (stackSize == 0){
            throw new StackException(size);
        }
        T firstData = top.getData();
        top = top.getNext();
       // top.setData(null);
        stackSize -= 1;
        return firstData;
    }

    void push(T item)throws StackException {
        if (stackSize == 5){
            throw new StackException(size);
        }
        Node<T> firstNode = new Node<T>(item, top);
        top = firstNode;
        stackSize += 1;
    }

}
