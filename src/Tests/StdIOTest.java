package Tests;
import java.util.Scanner;

/**
 * Created by WentaoD on 5/18/15.
 */
public class StdIOTest {
  public static void main(String args[]) {
    // create a scanner so we can read the command-line input
    Scanner scanner = new Scanner(System.in);

    //  prompt for the user's name
    System.out.print("Enter your name: ");

    // get their input as a String
    String username = scanner.next();

    // prompt for their DOB
    System.out.print("Enter your date of birth: ");

    // get DOB in format YYYY MM DD
    int year = scanner.nextInt();
    int month = scanner.nextInt();
    int day = scanner.nextInt();

    System.out.println("Please input your count of fingers: ");
    //Read input from a new line.
    scanner.nextLine();
    int fingers = scanner.nextInt();

    System.out.println(String.format("%s, your DOB is %d/%d/%d, with %d fingers remaining.", username, month, day, year, fingers));
  }

  private static void readLines() {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      System.out.println(line);
    }
  }
}
