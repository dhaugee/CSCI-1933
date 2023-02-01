import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class BookshelfReader {

    public static Bookshelf readBooksFromFile(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            int numLines = 0;
            while (scanner.hasNextLine()) {
                numLines++;
                scanner.nextLine();
            }
            Bookshelf bookbook = new Bookshelf(numLines);

            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String[] array = scanner.nextLine().split(",");
                Book book = new Book(array[0], array[1], Double.parseDouble(array[2]));
                bookbook.add(book);
            }
            return bookbook;
        } catch (FileNotFoundException e) {
            System.out.println("Exception found");
            return null;
        }

    }

    public static void writeShelfToFile(Bookshelf b, String fileName) {
        PrintWriter p;
        try {
                p = new PrintWriter(fileName);
                p.println(b.toString());
            p.close();
        } catch (Exception e) {
            System.out.println("Exception found");
        }
    }




//    public static void writeShelfToFile(Bookshelf mine, String s) {
//        PrintWriter p;
//        try {
//            p = new PrintWriter(fileName);
//            p.println(b.toString());
//            p.close();
//        } catch (Exception e) {
//            System.out.println("Exception found");
//        }
//    }
    }
