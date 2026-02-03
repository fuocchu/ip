import java.util.ArrayList;
import java.util.Scanner;

public class TreeBuddy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        System.out.println("Hello! I'm TreeBuddy");
        System.out.println("What can I do for you?");

        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine().trim();

            if (input.equals("bye")) {
                exit = true;
            } else if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("Your list is empty.");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
