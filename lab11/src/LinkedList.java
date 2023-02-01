public class LinkedList<T extends Comparable<T>> implements Comparable<LinkedList<T>> {

        public Node<T> head;
        public int size;

        public LinkedList() {
            head = new Node<T>(null, null);
            size = 0;
        }

        // add()
        public boolean add(T element) {
            if (element == null) {
                return false;
            } else {
                Node<T> ptr = this.head;
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                }
                ptr.setNext(new Node<T>(element, null));
                return true;
            }
        }

        // get()
        public T get(int index) {
            if (index < 0) {
                return null;
            }
            Node<T> ptr = this.head.getNext();
            int currentIndex = 0;
            while (ptr != null && currentIndex <= index) {
                if (currentIndex == index) {
                    return ptr.getData();
                } else {
                    ptr = ptr.getNext();
                    currentIndex++;
                }
            }
            return null;
        }

        // isEmpty()
        public boolean isEmpty() {
            if (head.getNext() == null) return true;
            return false;
        }

        // size()
        public int size() {
            Node<T> ptr = this.head.getNext();
            int size=1;
            if (!this.isEmpty()){
                while(ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    size++;
                }
                return size;
            }
            //return 0 if the list was empty
            return 0;
        }

        // clear()
        public void clear(){
            this.head = new Node<T>(null, null);
        }

        @Override
        public int compareTo(LinkedList<T> o) {
            return 0;
        }

        public String toString(){
            Node<T> cur = head;
            StringBuilder s = new StringBuilder();

            for (int i = 0; cur != null; cur = cur.getNext(), i++)
                s.append("Element ".concat(String.valueOf(i)).concat(": ").concat(String.valueOf(cur.getData())).concat("\n"));

            return s.toString();
        }

        // TODO: removeEvery()
        public void removeEvery(int n) {
            if ( n== 0 || n > size()){
                return;
            }
            else{
                Node<T> trail = head;
                Node<T> ptr = head.getNext();
                int counter = 1;
                while (ptr.getNext() != null){
                    if (counter % n == 0){
                        trail.setNext(ptr.getNext());
                        size --;
                    }
                    trail = ptr;
                    ptr = ptr.getNext();
                    counter ++;
                }
            }
        }

        // TODO: extractGroupsOf()
        public LinkedList<LinkedList<T>> extractGroupsOf(int n) {
            LinkedList<LinkedList<T>> lofl = new LinkedList<LinkedList<T>>(); // create the list
            Node<LinkedList<T>> loflTail = lofl.head; // set a tail for the list of lists
            Node<T> trail = head; // make a trailer
            Node<T> ptr = head.getNext(); // make a pointer
            int counter = 1; // set a counter for knowing where to cut the linked list
            while (ptr.getNext() != null){ // while the list has objects
                if (counter % n == 0){ // if ptr is on the node to cut off
                    LinkedList<T> linl = new LinkedList<T>(); // make the list to add the nodes to
                    trail = ptr.getNext();
                    ptr.setNext(null); // make pointer point to null, cutting the chain
                    linl.head.setNext(head.getNext()); // make the head of linl point to the og list
                    ptr = trail;
                    head = ptr;
                    loflTail.setData(linl);
                    loflTail.setNext(loflTail);
                    size =- counter;
                }
                ptr = ptr.getNext();
                counter ++;
            }

            return null;
        }

        // Main method for testing purposes.
        public static void main(String[] args) {}
}