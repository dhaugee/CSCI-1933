
public class Owl {
    private String owlName;
    private int owlAge;
    private  double owlWeight;

    public static void main(String args[]) {
        Owl owl1 = new Owl("owl1", 5, 12.0);
        Owl owl2 = new Owl("owl2", 5, 12.0);
        Owl owl3 = new Owl("owl1", 5, 12.0);
        System.out.println(owl1.equals(owl2));
        System.out.println(owl1.equals(owl3));
    }

    public Owl(String name, int age, double weight) {
        this.owlName = name;
        this.owlAge = age;
        this.owlWeight = weight;
    }

    public String getName() {
        return owlName;
    }

    public void setName(String name) {
        this.owlName = name;
    }

    public int getAge() {
        return owlAge;
    }

    public void setAge(int age) {
        this.owlAge = age;
     }

    public double getWeight() {
        return owlWeight;
    }

    public void setWeight(double weight) {
        this.owlWeight = weight;
    }

    public boolean equals(Owl other) {
        if (this.owlName.equals(other.getName())) {
            if (this.owlAge == other.getAge()) {
                if (this.owlWeight == other.getWeight()) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

}