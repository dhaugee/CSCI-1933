public class Bookshelf {
    private Book[] data;
    private int size;

    private int nextEmpty = 0;

    public Bookshelf(){
        data = new Book[20];
        size = 20;
    }

    public Bookshelf(int size){
        this.size = size;
        data = new Book[size];
    }

    public Bookshelf(Book[] input){
        this.data = input;
        this.size = input.length;
    }

    public boolean add(Book newBook){
        if (nextEmpty >= size){
            return false;
        }
        else {
            data[nextEmpty] = newBook;
            nextEmpty++;
            return true;
        }
    }

    public Bookshelf getBooksByAuthor(String author) {
        int newSize = 0;
        for (int i = 0; i <= size-1; i++) {
            if (data[i].getAuthor().equals(author)) {

                newSize++;
            }
        }
        Book[] authorArray = new Book[newSize];
        int index = 0;
        for (int i = 0; i <= size-1; i++) {
            if (data[i].getAuthor().equals(author)) {
                authorArray[index] = data[i];
                index++;
            }
        }
        return new Bookshelf(authorArray);
    }

    public String toString(){
        String totalStr = "";
        for(int i = 0; i < size; i++){
            if(data[i] != null) {
                totalStr = totalStr + data[i].toString() + "\n";
            }
            else {
                System.out.println("null");
            }
        }
        return totalStr;
    }

    public void sort(char sortBy) {
        int i, j;
        Book temp;
        boolean swapped = true;
        for (i = 0; i < data.length && swapped == true; i++) {
            swapped = false;
            for (j = 1; j < data.length - i; j++) {
                if (data[j].compareTo(data[j - 1], sortBy) < 0) {
                    swapped = true;
                    temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String args[]) {
//        Bookshelf bs = new Bookshelf(5);
//        bs.add(new Book("Eragon", "Christopher Paolini", 10.0));
//        bs.add(new Book("The Fellowship of the Ring", "J.R.R. Tolkein", 10.0));
//        bs.add(new Book("Twilight", "Stephenie Meyer", 0.0));
//        bs.add(new Book("The Diary of a Wimpy Kid", "Jeff Kinney", 0.0));
//        bs.add(new Book("Dracula", "Bram Stoker", 7.5));
//        bs.sort('t');
//        System.out.println(bs);

        Bookshelf mine = BookshelfReader.readBooksFromFile("bookinput.txt");
        mine.sort('r');
        BookshelfReader.writeShelfToFile(mine, "output.txt");
    }
}