// by: Dylan Haugee: hauge919
public class LinkedList<T extends Comparable<T>> implements List<T> {

    private boolean isSorted; // boolean to keep track of if the linked list is sorted (in ascending order)
    public Node<T> head;
    public Node<T> tail;
    public int size = 0;

    public LinkedList() {
        head = new Node<>(null); // head is set up as a dummy node
        tail = head;
        isSorted = true; // because the list is initially empty, it is sorted
    }

    @Override
    public boolean add(T element) { // method to add an element to the end of the list, returns its success
        if(element == null){ // if the element is null, adding it won't do anything, so return false
            return false;
        }
        else if (head.getNext() == null){ // if the list is empty
            tail.setNext(new Node<>(element, head.getNext())); // tail points to a new node holding the element that points to null
            tail = tail.getNext(); // this node is set to be tail
            size++; // increment size
            isSorted = checkSorted(); // because there's only one element, the list must be sorted
            return true;
        }
        else { //
            Node<T> endNode = new Node<>(element); // a new node holding the element is created
            tail.setNext(endNode); // tail points to this node
            tail = tail.getNext(); // the node is set to be the tail
            size++; // increment size
            isSorted = checkSorted(); // call helper function to determine if the list is sorted
            return true;
        }
    }

    @Override
    public boolean add(int index, T element) { // method to add an element at a specific index
        if(index < 0 || index >= size || element == null) { // if the element is null or the index is out of bounds, return false
            return false;
        }
        else if (index == 0){ // if the index is the first node
            Node<T> newNode = new Node<>(element, head.getNext()); // create a new node holding the element and pointing to null
            head.setNext(newNode); // set the node after the head to be newNode
            size++; // increment size
            isSorted = checkSorted(); // call helper function to determine if the list is sorted
            return true;
        }
        else { // otherwise it's an index not equal to zero
            Node<T> temp = head.getNext(); // create a temp node at the first node
            for(int i = 0; i < index - 1; i++){ // iterate by 1 until temp is the node 1 less than the index
                temp = temp.getNext(); // temp moves along the linked list
            }
            Node<T> newNode = new Node<>(element, temp.getNext()); // create a new node holding the element and pointing to the node after temp
            temp.setNext(newNode); // set the node after temp to be newNode
            size++; // increment size
            isSorted = checkSorted(); // call helper function to determine if the list is sorted
            return true;
        }
    }

    @Override
    public void clear() { // method to clear list of all elements
        head.setNext(null); // head points to null, cutting the chain and removing all nodes besides head
        isSorted = true; // with no elements, the list is sorted
        tail = head; // tail is reset to head
        size = 0; // size is reset to zero
    }

    @Override
    public T get(int index) {  // method to get element at some index
        if(index < 0 || index >= size) { // if input index is out of bounds, return null
            return null;
        }
        else if(size == 1){
            return head.getNext().getData(); // if there's only 1 element in the list, return that element
        }
        else {
            Node<T> temp = head.getNext(); // set a temp node that starts at the first element
            for(int i = 0; i < index; i++){ // iterate through list until temp is on the node we'd like info from
                temp = temp.getNext();
            }
            return temp.getData(); // return the value of this node
        }
    }

    @Override
    public int indexOf(T element) { // method to find the index of an element
        if(element != null) { // if the input element is not null
            int index = 0;
            Node<T> temp = head.getNext(); // set a temp node that starts at the first element
            while (temp != null) { // while temp is not null
                if (temp.getData().compareTo(element) == 0) { // if the element at temp is equal to the input element
                    return index; // return the index temp is at
                }
//                else if(isSorted() && temp.getData().compareTo(element) > 0){ // extra else case to increase efficiency of method
//                    return -1;    // had to be commented out because isSorted does not work
//                }
                index++; // increment index
                temp = temp.getNext(); // temp moves through the list
            }
        }
        return -1; // if the input element IS null, return -1.
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){ // if the list is empty
            return true; // return true
        }
        return false; // otherwise return false
    }

    @Override
    public int size() {
        return size; // return the size of the list
    }

    @Override
    public void sort() { // method to sort list in ascending order
        Node<T> temp = head.getNext(); // set a temp node that starts at the first element
        Node<T> trail; // create a node
        if (head.getNext() == null ){ // if the list is empty, no need for sorting
            return;
        }
        else{
            while(temp != null) { // while temp is not null
                Node<T> min = temp; // create a node min equal to temp
                trail = temp.getNext(); // trail is in front of temp
                while (trail != null){  // while trail is not null
                    if (trail.getData().compareTo(min.getData()) < 0){ // if min's value (min is behind trail) is bigger than trail
                        min = trail; // set min to be trail
                    }
                    trail = trail.getNext(); // move trail forward
                }
                T data = min.getData(); // assign min's data to a variable
                min.setData(temp.getData()); // set min's data to be temp's data
                temp.setData(data); // set temp's data to be min's data
                temp = temp.getNext(); // move temp forward
            }
        }
        isSorted = true; // the list is now sorted
    }

    @Override
    public T remove(int index) { // method to remove the node at an index
        if (index >= size || index < 0){ // if the index is out of bounds, return null
            return null;
        }
        Node<T> temp = head.getNext(); // set a temp node that starts at the first element
        Node<T> trail = head; // set a trail node behind temp
        for(int i = 0; i < index; i++){ // iterate through list until temp is the node to remove
            trail = temp; // trail equals temp
            temp = temp.getNext(); // temp moves forward
        }
        T removeData = temp.getData(); // assign temp's data to a variable
        trail.setNext(temp.getNext()); // trail points at whatever temp points at, skipping the node and hence removing it
        size--; // size decreases by 1
        isSorted = checkSorted();
        return removeData; // return the data we removed
    }

    @Override
    public void equalTo(T element) { // method to remove all nodes not equal to this element
        if (element == null){ // if the element to keep is null, end the method
            return;
        }
        Node<T> temp = head.getNext(); // set a temp node that starts at the first element
        Node<T> ptrHead = new Node<>(null); // set a ptrHead node with no value
        Node<T> ptr = ptrHead; // set ptr to be equal to ptrHead
        int counter = 0;
        while (temp != null) { // while temp has a value
            if (temp.getData().compareTo(element) == 0) { // if temp's value is equal to element
                ptr.setNext(temp); // make ptr point to temp
                ptr = ptr.getNext(); // move ptr forward
                counter++; // increment counter
            }
            temp = temp.getNext(); // move temp forward
        }
        ptr.setNext(null); // make ptr point to null
        tail = ptr; // make the original list's tail ptr
        head = ptrHead; // make the original list's head ptrHead, effectively making the list hold only nodes equal to element
        size = counter; // change the size to how many times we found a node equal to element
    }

    @Override
    public void reverse() {
        Node<T> ls = head; // make a node ls equal to head
        Node<T> trail;
        Node<T> temp;
        if (ls.getNext() == null || ls.getNext().getNext() == null) { // if ls' next value or next, next value is null, end method
            return;
        }
        temp = ls.getNext().getNext();  // temp node moves to front
        trail = ls.getNext(); // trail stays behind temp
        while (temp != null) { // while temp has a value
            trail.setNext(temp.getNext());  // link around temp node
            temp.setNext(ls.getNext());  // link temp to front of list
            ls.setNext(temp);  // link ls to temp
            temp = trail.getNext();  // advance to next node to move
        }
        isSorted = checkSorted();
    }  // reverse method


    @Override
    public void merge(List<T> otherList) { // method to merge two lists into one
        LinkedList<T> other = (LinkedList<T>) otherList; // make otherList into a linked list of type T
        sort(); // sort our original list
        other.sort(); // sort other
        int mergeSize = size + other.size; // establish the size of our merged list
        if (head.getNext() == null || other.head.getNext() == null){ // if either list is empty, no need to merge
            return;
        }
        Node<T> mergedList = new Node<>(null); // create the head of our merged list
        Node<T> mergedListPtr = mergedList; // create the tail of our merged list
        Node<T> ptr1 = head.getNext(); // create a pointer pointing the first node of the og linked list
        Node<T> ptr2 = other.head.getNext(); // create a pointer pointing the first node of the other linked list
        while(ptr1 != null && ptr2 != null){ // while both lists have elements remaining
            Node<T> temp; // make a temp node
            if(ptr1.getData().compareTo(ptr2.getData()) < 0){ // if og list's pointer is smaller than other list's pointer
                temp = ptr1; // set temp to ptr1
                ptr1 = ptr1.getNext(); // move ptr1 forward in og list
            }
            else { // otherwise, other list's pointer is smaller than og list's pointer
                temp = ptr2; // set temp to ptr2
                ptr2 = ptr2.getNext(); // move ptr2 forward in other list
            }
            mergedListPtr.setNext(temp); // set merged list's tail to temp
            mergedListPtr = mergedListPtr.getNext(); // move merged list's tail forward
        } // while loop breaks when one of the lists has added all its elements to the merged list
        if (ptr1 == null){ // if the og list is depleted
            mergedListPtr.setNext(ptr2); // add the rest of the other list to merged list
        }
        else if (ptr2 == null){ // if the other list is depleted
            mergedListPtr.setNext(ptr1); // add the rest of the og list to merged list
        }
        size = mergeSize; // update size to be the sum of both list sizes
        head = mergedList; // set the head of the og list to be the head of the merged list
        tail = mergedListPtr; // set the tail of the og list to be the tail of the merged list (the og list is now the merged list)
        isSorted = true; // both original lists were sorted, so the merged list is also sorted
    }

    @Override
    public void pairSwap() { // swaps every two elements in a list
        Node<T> temp = head.getNext(); // set a temp node that starts at the first element
        while(temp != null && temp.getNext() != null){ // while the current node and next node have values
            T swap = temp.getData(); // assign the data of the current node to a variable
            temp.setData(temp.getNext().getData()); // set temp's value to be the value of the next node
            temp.getNext().setData(swap); // set the next node's value to be the value of the previous node
            temp = temp.getNext().getNext(); // temp jumps forward two nodes
        }
        isSorted = checkSorted();
    }

    @Override
    public boolean isSorted() { // duh
        return isSorted;
    }

    public boolean checkSorted(){ // helper function to check if a list is sorted (DOES NOT WORK)
        if (head.getNext() == null){ // if the list is empty, it is sorted
            return true;
        }
        else if (size == 1){ // if the list has one node, it is sorted
            return true;
        }
        else {
            Node<T> temp = head.getNext(); // set a temp node that starts at the first element
            Node<T> trail = head; // set a node to trail behind temp
            while (temp != null && temp.getNext() != null) { // while the current node and next node have values
                if (temp.getData().compareTo(temp.getNext().getData()) > 0) { // if the current node is bigger than the next node
                    return false; // then that pair of nodes are in descending order, so the list is not sorted
                }
                trail = temp; // set trail to temp
                temp = temp.getNext(); // move temp forward
            } // the while loop breaks when temp reaches the last node, because it will point to null
            if (trail.getData().compareTo(temp.getData()) > 0) { // if the second to last node is bigger than the last node
                return false; // then that pair of nodes are in descending order, so the list is not sorted
            }
            return true; // if false is never reached, the list is entirely in ascending order
        }
    }

    public static void main(String[] args) { // main method used for debugging indexOf
        List<Integer> list1 = new LinkedList<>();
        for (int  i = 0; i < 100; i++) {
            list1.add(i);
        }
        for (int  i = 0; i < 100; i++) {
            System.out.println(list1.indexOf(i));
        }
    }

}
