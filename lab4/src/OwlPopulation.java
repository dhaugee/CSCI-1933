import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OwlPopulation {
    private String fileName;
    private Owl[] data;


    public int populateData() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);

        int numLines = 0;
        while(scanner.hasNextLine()){
            numLines++;
//            String s = scanner.nextLine();
        }
        scanner.close();
        data = new Owl[numLines];   //data is allocated the exact amount of space it needs
        scanner = new Scanner(f);
        String splitter = ",";
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String [] array = s.split(splitter);
            data[numLines-1] = new Owl(array[0], Integer.parseInt(array[1]), Double.parseDouble(array[2]));
            numLines--;
        }
        return data.length;
    }

        //TODO: Populate the data with owls constructed from the lines of the file given


    public OwlPopulation(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        populateData();
        //TODO: Populate the class variables in OwlPopulation
    }

    public double averageAge(){
        int i = (data.length -1);
        double totalAge = 0;
        while (i >= 0) {
            totalAge = totalAge + data[i].getAge();
            i --;
        }
        return totalAge/data.length;
    }

    public Owl getYoungest(){
        if (data.length == 0) {
            return null;
        }
        int i = (data.length -1);
        Owl youngestOwl = data[i];
        int youngestAge = data[i].getAge();
        while (i >= 0) {
            if (data[i].getAge() < youngestAge) {
                youngestAge = data[i].getAge();
                youngestOwl = data[i];
            }
            i--;
        }
        return youngestOwl;
    }

    public Owl getHeaviest(){
        if (data.length == 0) {
            return null;
        }
        int i = (data.length -1);
        Owl heaviestOwl = data[i];
        double heaviestWeight = data[i].getWeight();
        while (i >= 0) {
            if (data[i].getWeight() > heaviestWeight) {
                heaviestWeight = data[i].getWeight();
                heaviestOwl = data[i];
            }
            i--;
        }
        return heaviestOwl;
    }

    public String toString(){
        String introStr = "FOR THIS OWL POPULATION:\n";
        String youngStr = "The youngest owl is " + getYoungest().getName() + ", who is " + getYoungest().getAge() + " years old.\n";
        String avgStr = "The average owl age is " + averageAge() + " years old.\n";
        String heavyStr = "The heaviest owl is " + getHeaviest().getName() + ", who weighs " + getHeaviest().getWeight() + " pounds.";
        return introStr + youngStr + avgStr + heavyStr;
    }

    public boolean containsOwl(Owl other){
        int i = (data.length - 1);
        while (i >= 0) {
            if (data[i].equals(other)) {
                return true;
            }
            i--;
        }
        return false;
    }

    public void merge(OwlPopulation other){
        int j = (other.data.length -1);
        int numDist = 0;
        while (j >= 0) {
            if (!(containsOwl(other.data[j]))) {
                numDist++;
            }
            j--;
        }
        Owl[] merger = new Owl[this.data.length + numDist];
        int z = 0;
        while (z <= merger.length -1) {
            int i = (this.data.length -1);
            while (i >= 0) {
                merger[z] = this.data[i];
                z++;
                i--;
            }
            int y = (other.data.length -1);
            while (y >= 0) {
                if (!(containsOwl(other.data[y]))) {
                    merger[z] = other.data[y];
                    z++;
                }
                y--;
            }
        }
        this.data = merger;

    }

    public int popSize(){
        return data.length;
    }

    public static void main(String[] args) {
        try {
          //  The following should run when you are complete. Feel free to comment out as you see fit while you work.
            OwlPopulation pop1 = new OwlPopulation("owlPopulation1.csv");
            System.out.println(pop1);
            System.out.println(pop1.popSize());

            OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
            System.out.println(pop2);
            System.out.println(pop2.popSize());

            pop1.merge(pop2);
            System.out.println(pop1);
            System.out.println(pop1.popSize());
        }
        catch (FileNotFoundException f){
            System.out.println("File not found.");
        }
    }


}
