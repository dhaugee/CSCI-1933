import java.util.Scanner;

// 1. Welcome to Class
public class BankAccount {
  private String name;
  private String password;
  private double balance;

  public static void main(String[] args) {
  BankAccount myAccount = new BankAccount("Dyl", "1234", 2049.00);
  myAccount.deposit("1234", 100.50);
  System.out.println("My password was: " + myAccount.password);
  myAccount.setpassword("1234", "12345!");
  myAccount.setpassword("12345!", "12345?");
  System.out.println("But now it's: " + myAccount.password);
  System.out.println(myAccount.getName());
  System.out.println("Enter your password: ");
  Scanner myScanner = new Scanner(System.in);
  String input = myScanner.nextLine();
    if (myAccount.password.equals(input)) {
        System.out.println("Your account's balance is: " + myAccount.balance);
    }
      else {
        System.out.println("Incorrect password, try again in 5 minutes");
      }
  BankAccount theirAccount = new BankAccount("Doug", "sk8ter", 14561.75);
  myAccount.transfer(theirAccount, "12345?", 5000);
  }

  public BankAccount(String initName, String initPass, double initBal) {
    this.name = initName;
    this.password = initPass;
    this.balance = initBal;
  }

  public double getBalance(String enteredPassword) {
    if (password.equals(enteredPassword)) {
        return balance;
    } else {
        return -1;
      }
    }

  public String getName() {
    return name;
  }

  public boolean setpassword(String oldPassword, String newPassword) {
    if (password.equals(oldPassword)) {
        password = newPassword;
        return true;
    } else {
        return false;
    }
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public void withdraw(String enteredPassword, double amount) {
// Only people with the right password and sufficient funds can withdraw
    if (password.equals(enteredPassword) && balance >= amount) {
        balance = balance - amount;
    }
  }

  public void deposit(String enteredPassword, double amount) {
    if (password.equals(enteredPassword)) {
        balance = balance + amount;
    }
  }

  // Transfers the given amount from this account into the other account
  // if enteredPassword matches password and the password entered in this
  // function matches the other account’s password.
  // Note: You’ll have to use the Scanner class again to intake a password
  // to deposit into other.
  public void transfer(BankAccount other, String enteredPassword, double
  amount){
    this.deposit(enteredPassword, amount);
    System.out.println("Enter the other password: ");
    Scanner theScanner = new Scanner(System.in);
    String answer = theScanner.nextLine();
    other.withdraw(answer, amount);
    if (!answer.equals(other.password)) {
      this.withdraw(enteredPassword, amount);
    }
    System.out.println(this.getName() + "'s balance is: " + this.getBalance(enteredPassword));
    System.out.println(other.getName() + "'s balance is: " + other.getBalance(answer));
  }
}
