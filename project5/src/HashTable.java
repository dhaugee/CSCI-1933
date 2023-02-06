// hauge919, yi000087
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable<T>{

    NGen<T>[] hashTable; // hash table is declared as an array of linked lists
    public String type = "general"; // type is initialized as "general"

    public HashTable(int length, String type){
        hashTable = new NGen[length]; // hash table initialized with length input by constructor
        this.type = type; // type initialized by constructor input, type parameter ended up being unnecessary
    }

    public int hash1(T item) { // hash function #1
        String word = item.toString();
        return word.charAt(0) % hashTable.length; // the ASCII value of the first letter of the input word is returned
    }

    public int hash2(T item) { // hash function #2
        String word = item.toString();
        return word.charAt(word.length() - 1) % hashTable.length; // the ASCII value of the last letter of the input word is returned
    }

    // hash1 and hash2 both perform poorly, with longest chains of more than 7. hash1 performs better though,
    // because there is more variety in the first letter of words than the last

    public int hash3(T item) { // hash function #3
        String word = item.toString();
        int hash = 0; // the integer to be returned (after modulo hash table length) is initialized
        for (int i = 0; i < word.length(); i++){ // the word is iterated through letter by letter
            hash =  hash * 33 + word.charAt(i); // hash is multiplied by 33 and the character's ASCII value is added
        }
        return Math.abs(hash % hashTable.length); // absolute value is used because for an unknown reason, negative values were sometimes returned
    }

    // this was our best performing hash function. We also tested with combination of an initial hash value of 5381 and
    // the constant value being 31, but the combo of 0 and 33 performed best. We chose these values because they continually
    // popped up during our research. every combination passed though, with consistent average collision length values of 1.0.
    // this function performed best for both the specific and the general case, which is why the type indicator is unnecessary
    // in our code

    public int hash4(T item){ // hash function #4
        String word = item.toString();
        double hash = 0; // the to-be integer to be returned (after modulo hash table length) is initialized
        for (int i = 0; i < word.length(); i++){ // the word is iterated through letter by letter
            hash = Math.floor(hash * (.32787 * word.charAt(i) % 1)); // hash is multiplied by a decimal constant and the decimal remainder is multiplied by hash, and is rounded down to the nearest integer
        }
        return (int) Math.abs(hash % hashTable.length); // hash is converted to an integer
    }

    // hash4 performed badly, with longest collision lengths reaching 50. The equation used was inspired by an article
    // describing different hash functions, but was difficult to properly implement

    public int hash5(T item) { // hash function #5
        String word = item.toString();
        int hash = 0; // the integer to be returned (after modulo hash table length) is initialized
        for (int i = 0; i < word.length(); i++){ // the word is iterated through letter by letter
            hash =  hash * 33 + word.charAt(i) * i; // hash is multiplied by 33 and the character's ASCII value multiplied by i is added
        }
        return Math.abs(hash % hashTable.length);
    }

    // hash5 performed similarly to hash3, with the only difference being the product of the ascii value and i. It passes all the
    // requirements.


    public void add(T item) { // method to add an item to a hash table
        int number = hash3(item); // hash3 is used, as it performs the best for all cases. It returns the index that will be used
        if (hashTable[number] == null){ // if the index has no element in it yet
            NGen<T> head = new NGen(item, null); // create a new node holding the string
            hashTable[number] = head; // set the index equal to the node
        }
        else { // otherwise, if the index already holds a node(s)
            NGen<T> temp = hashTable[number]; // a temp node is created at the head of the linked list
            while (temp.getNext() != null) { // while a next node exists
                if (temp.getData().equals(item)) { // if the current node holds the same string as input (a duplicate)
                    return; // return, breaking from the method
                }
                temp = temp.getNext(); // temp is moved along the linked list
            }
            if (temp.getData().equals(item)) { // since when we leave the while loop we don't have info on the data of the current node, we check if it's a duplicate
                return; // return, breaking the method
            }
            NGen<T> itemNode = new NGen(item, null); // create a new node holding the string
            temp.setNext(itemNode); // a node is added to the end of the linked list holding the string
        }
    }

    // ** Already implemented -- no need to change **
    // Adds all words from a given file to the hash table using the add(T item) method above
    @SuppressWarnings("unchecked")
    public void addWordsFromFile(String fileName) {
        Scanner fileScanner = null;
        String word;
        try {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName + " not found.");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            word = fileScanner.next();
            word = word.replaceAll("\\p{Punct}", ""); // removes punctuation
            this.add((T) word);
        }
    }

    public void display() {
        int unique = 0;
        int empty = 0;
        int nonempty = 0;
        int longest = 0;
        for (int i = 0; i < hashTable.length; i++){ // loop through the hash table
            int length = 0; // for every index, its length is set to 0
            if (hashTable[i] == null){ // if the index is null
                empty++; // the amount of empty indices is increased
            }
            else { // if the index is not null
                nonempty++; // the amount of nonempty indices is increased
                NGen<T> temp = hashTable[i]; // a temp node is created at the head of the linked list
                while (temp != null){ // while the current node is not null
                    length++; // the length of the linked list is increased by 1
                    temp = temp.getNext(); // temp is moved along the linked list
                }
                if (length > longest){ // if the length of the linked list is longer than the current longest collision
                    longest = length; // longest is set to said length
                }
            }
            unique += length; // the amount of unique words is just the amount of words since no duplicates exist, so unique is increased by the length of the current linked list
            System.out.println(i + ": " + length); // index and length are printed
        }
        double avgColLen = unique/nonempty; // average collision length is equal to the amount of words divided by the amount of filled indices
        System.out.println("# of unique words: " + unique);
        System.out.println("# of empty indices: " + empty);
        System.out.println("# of nonempty indices: " + nonempty);
        System.out.println("average collision length: " + avgColLen);
        System.out.println("length of longest chain: " + longest + "\n");
    }

    // TODO: Create a hash table, store all words from "canterbury.txt", and display the table
    //  Create another hash table, store all words from "keywords.txt", and display the table
    public static void main(String args[]) {
        HashTable table1 = new HashTable(149, "general");
        table1.addWordsFromFile("canterbury.txt");
        table1.display();
        HashTable table2 = new HashTable(149, "specific");
        table2.addWordsFromFile("keywords.txt");
        table2.display();
    }
}
