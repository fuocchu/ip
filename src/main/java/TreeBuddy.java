import java.util.ArrayList;
import java.util.Scanner;

public class TreeBuddy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                }
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid command format. Use: mark <task_number>");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid command format. Use: unmark <task_number>");
                }

            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5).trim();
                Task t = new ToDo(desc);
                tasks.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split("/by", 2);
                if (parts.length < 2) {
                    System.out.println("Please specify /by for deadline.");
                } else {
                    Task t = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }

            } else if (input.startsWith("event ")) {
                String[] partsFrom = input.substring(6).split("/from", 2);
                if (partsFrom.length < 2) {
                    System.out.println("Please specify /from and /to for event.");
                } else {
                    String desc = partsFrom[0].trim();
                    String[] partsTo = partsFrom[1].split("/to", 2);
                    if (partsTo.length < 2) {
                        System.out.println("Please specify /to for event.");
                    } else {
                        Task t = new Event(desc, partsTo[0].trim(), partsTo[1].trim());
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                }

            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
