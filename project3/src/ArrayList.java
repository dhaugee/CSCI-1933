/**by Peter Yi (yi000097)**/
import java.util.Arrays;
public class ArrayList<T extends Comparable<T>> implements List<T> {
    private int size = 0; //amount of elements in the list
    public int capacity = 2; //how many elements the list can hold
    private boolean isSorted = false; //sorted boolean
    T[] a = (T[]) new Comparable[capacity]; //initializing the list with capacity of 2

    public ArrayList() {
        isSorted = true; //nothing in list, so technically sorted
        capacity = 2; //default capacity is 2
        size = 0; //size is elements in list
    }

    public boolean add(T element) {
        if (element == null) { //goes to false if element is null (cannot add) and while there is space in the list
            return false;
        } else if (size >= capacity) { //if list is full, make new list
            whenArrayFull(); //call helper function
            a[size] = element; //adding element
            size++; //size +1'd
            return true;
        } else {
            a[size] = element; //adds the element to the back of the list
            size++; //list has one more element
            return true;
        }
    }

    public void whenArrayFull() { //helper function
        T[] temp = (T[]) new Comparable[capacity * 2];//makes a temporary list double the size
        System.arraycopy(a, 0, temp, 0, capacity);//copies the array
        capacity *= 2;
        a = temp;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size) {//if index isn't in range or bigger than the # of elements or if it is null, return false
            return false;
        }
        if (size >= capacity) {
            whenArrayFull(); //call helper function
            a[index] = element; //adding element
            size++; //size +1
            sorting(); //check sorted
            return true;
        } else {
            if (get(index) != null) {
                for (int i = size - 1; i >= index; i--) { //shifts the array to the right by 1
                    a[i+1] = a[i];
                }
            }
            a[index] = element; //add the element
            size++; //added the element, size +1
            sorting(); //check if sorted
            return true;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) { //loops through the whole list
            a[i] = null; //sets everything to null
        }
        isSorted = true; //is sorted because it is empty
        size = 0; //nothing in list
        capacity = 2; //capacity has returned to the default, 2
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null; //index is not in bounds
        } else {
            return a[index]; //return the element
        }
    }

    @Override
    public int indexOf(T element) {
        if (element == null) { //if element is null, return -1
            System.out.println("element is null");
            return -1;//element is null
        } else {
            for (int index = 0; index < capacity; index++) { //loop through whole array
                if (a[index] == element) { //to find which index the element is at
                    return index; //return the index
                }
            }
        }
        return -1; //element is not in list
    }

    @Override
    public boolean isEmpty() {
        if (a.length == 0 || size == 0) { //if size or length is 0, it is empty
            return true;
        } else {
            for (int i = 0; i < a.length; i++) { //looping through the array
                if (a[i] != null) { //if there is an object...
                    return false; //the array is not empty
                }
            }
            return true; //if it passes through the loop, there is nothing in the array
        }
    }

    @Override
    public int size() {
        return size; //things inside the array
    }

    @Override
    public void sort() { /**TAKEN FROM SAMPLE CODE FROM LECTURE**/
        int i, j, minIndex; //initialize the variables
        for (i = 0; i < a.length - 1; i++) {
            minIndex = i; //min index is the first since there is nothing else to compare to
            for (j = i + 1; j < a.length; j++) {
                if (a[j] == null) {
                    break; //avoid null pointer exception
                }
                if (a[j].compareTo(a[minIndex]) < 0) //if item is less than, it returns -1
                    minIndex = j; //smallest is saved
            }
            T temp = a[minIndex]; //saves smallest
            a[minIndex] = a[i]; //swaps with current
            a[i] = temp; //current is now smallest, moves on
        }
        isSorted = true;
    }

    public void sorting() { //helper function
        if (isSorted() == false) {
            sort();
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) { //if index isn't in range or bigger than the # of elements, return null
            System.out.println("index is out of bounds");
            return null;
        } else {
            T removed = a[index]; //hold the obj
            a[index] = null; //remove
            for (int i = index; i <= size - 1; i++) { //shifts the array to the right by 1
                a[i] = a[i + 1]; //set the current obj to the one after, moving the array
            }
            size--; //element was taken out
            return removed; //return the removed element
        }
    }

    @Override
    public void equalTo(T element) {
        if (element != null) { //can't compare a null
            for (int i = 0; i < size; i++) { //loop for the elements in the list
                if (a[i].equals(element) != true) { //if the object is not equal to the element
                    remove(i); //remove it
                    i--;
                }
            }
        }
    }

    @Override
    public void reverse() {
        T temp; //temp object holder
        int j = size - 1; //index of end of the array
        for (int i = 0; i < size / 2; i++) { //keep swapping the ends of the array, stop in the middle
            temp = a[i]; //temp is the x (obj. closest to the beginning)
            a[i] = a[j]; //x is y (the obj. closest to the end) *swap happens here
            a[j] = temp; //y is temp
            j--; //move the index by 1- last index has swapped
        }
    }

    @Override
    public void merge(List<T> otherList) {
        ArrayList<T> other = (ArrayList<T>) otherList; //make list into an arraylist
        sort(); //sort the list
        other.sort(); //sort the other list
        if (otherList != null) {
            int perfectcapacity = other.size + size; //perfect size
            T[] merge = (T[]) new Comparable[perfectcapacity]; //merge array of perfect size
            int  i = 0; //merge list index literator
            while(get(0) != null && other.get(0) != null) { //stops loop when smallest array is done
                if (other.get(0).compareTo(get(0)) < 0) { //checks if the object in the other list is less than the
                                                        //one in the current list.
                    merge[i] = other.remove(0); //removes the object and moves it into the merging list
                    i++; //moves to next index in the merged list
                } else if (get(0).compareTo(other.get(0)) < 0) {//checks if the object in the original list is
                                                                //less than the one in the other list
                    merge[i] = remove(0); //removes object from original list and moves it into the merging list
                    i++; //moves the index by 1
                }
            }
            if (size == 0){ //if all the elements are gone from the original list...
                while (other.get(0) != null){ //while the next object is not null,
                merge[i] = other.remove(0);// add every element left in the other list into the merged list
                                                 // (because the list is sorted already
                i++; //moves index by 1
                }
            }
            if (other.size == 0){ //if all the elements from the other lists...
                while(get(0) != null){ //while the next object is not null,
                    merge[i] = remove(0); //add everything to the merged list (because it is already sorted)
                    i++; //move index by 1
                }
            }
            a = merge; //assigns merged list to original list
            size = perfectcapacity; //reassigns size to the appropriate amount
        }
    }
    @Override
    public void pairSwap() {/**inspiration from swapping lecture**/
        for (int i = 0; i < size - 1; i = i+2) { //have to iterate by 2; because we are going by pairs
            T temp = a[i]; //temp holds the 1st obj, so we don't overwrite it and get rid of it
            a[i] = a[i + 1]; //swap the current object with the other
            a[i + 1] = temp; //assign the swapped object (2nd) to the temp object
        }
        sorting(); //check if is sorted
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
}
