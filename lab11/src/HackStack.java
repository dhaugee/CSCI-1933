public class HackStack<T extends Comparable<T>> {
    private int size;
    private Node<T> top;
    private T data;
    private int stackSize = 0;

    public HackStack() {
        size = 5;
        Node<T> node = new Node<T>(null, null);
    }

    public HackStack(int size) {
        this.size = size;
        Node<T> node = new Node<T>(null, null);
    }

    public T pop() throws StackException {
        if (stackSize == 0) {
            throw new StackException(size);
        }
        T firstData = top.getData();
        top = top.getNext();
        stackSize -= 1;
        return firstData;
    }

    public T pop(int n) throws StackException {
        if (stackSize == 0) {
            throw new StackException(size);
        } else {
            Node<T> ptr = top;
            Node<T> trail = top;
            int counter = 0;
            while (top != null) {
                if (counter == n) {
                    T popData = top.getData();
                    trail.setNext(ptr.getNext());
                    ptr = ptr.getNext();
                    size--;
                    return popData;
                } else {
                    trail = ptr;
                    ptr = ptr.getNext();
                    counter++;
                }
            }
        }
        return null;
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


