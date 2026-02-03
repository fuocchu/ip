import java.util.Scanner;

public class TreeBuddy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Level 0
        System.out.println("Hello! I'm TreeBuddy");
        System.out.println("What can I do for you?");

        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                exit = true;
            } else {
                System.out.println(input); // Level 1
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
