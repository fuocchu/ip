package treebuddy;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class TreeBuddy {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storage = new Storage("./data/duke.txt");

    public static void main(String[] args) {
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }

        Scanner scanner = new Scanner(System.in);

        Logo.printLogo();
        System.out.println("Hello! I'm TreeBuddy");
        System.out.println("What can I do for you?");

        boolean exit = false;

        while (!exit) {
            String input = scanner.nextLine().trim();

            try {
                if (input.equals("bye")) {
                    exit = true;
                }

                else if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println("Your list is empty.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                    }
                }

                else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new TreeBuddyException("Invalid task number.");
                    }

                    Task removed = tasks.remove(index);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    storage.save(tasks);
                }

                else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new TreeBuddyException("Invalid task number.");
                    }

                    tasks.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));

                    storage.save(tasks);
                }

                else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new TreeBuddyException("Invalid task number.");
                    }

                    tasks.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));

                    storage.save(tasks);
                }

                else if (input.startsWith("todo ")) {
                    String desc = input.substring(5).trim();
                    if (desc.isEmpty()) {
                        throw new TreeBuddyException("Description cannot be empty.");
                    }

                    Task t = new ToDo(desc);
                    tasks.add(t);

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    storage.save(tasks);
                }

                else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split("/by", 2);
                    if (parts.length < 2) {
                        throw new TreeBuddyException("Please specify /by for deadline.");
                    }

                    Task t = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(t);

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    storage.save(tasks);
                }

                else if (input.startsWith("event ")) {
                    String[] fromParts = input.substring(6).split("/from", 2);
                    String[] toParts = fromParts[1].split("/to", 2);

                    Task t = new Event(fromParts[0].trim(), toParts[0].trim(), toParts[1].trim());
                    tasks.add(t);

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    storage.save(tasks);
                }
                else {
                    throw new TreeBuddyException("OOPS!!! I don't understand that command.");
                }
            } catch (TreeBuddyException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid command format.");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}