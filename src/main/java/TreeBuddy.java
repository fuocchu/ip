import java.util.ArrayList;
import java.util.Scanner;

public class TreeBuddy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        Logo.printLogo();
        System.out.println("Hello! I'm TreeBuddy");
        System.out.println("What can I do for you?");

        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine().trim();

            try {
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
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid command format. Use: mark <task_number>");
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid number.");
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
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid command format. Use: unmark <task_number>");
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid number.");
                    }
                } else if (input.equals("todo")) {
                    throw new TreeBuddyException("OOPS!!! The description of a todo cannot be empty.");
                } else if (input.startsWith("todo ")) {
                    String desc = input.substring(5).trim();
                    if (desc.isEmpty()) {
                        throw new TreeBuddyException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new ToDo(desc);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (input.equals("deadline")) {
                    throw new TreeBuddyException("OOPS!!! The description of a deadline cannot be empty.");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split("/by", 2);
                    if (parts.length < 2) {
                        throw new TreeBuddyException("OOPS!!! Please specify /by for deadline.");
                    }
                    String desc = parts[0].trim();
                    if (desc.isEmpty()) {
                        throw new TreeBuddyException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    Task t = new Deadline(desc, parts[1].trim());
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (input.equals("event")) {
                    throw new TreeBuddyException("OOPS!!! The description of an event cannot be empty.");
                } else if (input.startsWith("event ")) {
                    String[] partsFrom = input.substring(6).split("/from", 2);
                    if (partsFrom.length < 2) {
                        throw new TreeBuddyException("OOPS!!! Please specify /from and /to for event.");
                    }
                    String desc = partsFrom[0].trim();
                    if (desc.isEmpty()) {
                        throw new TreeBuddyException("OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] partsTo = partsFrom[1].split("/to", 2);
                    if (partsTo.length < 2) {
                        throw new TreeBuddyException("OOPS!!! Please specify /to for event.");
                    }
                    Task t = new Event(desc, partsTo[0].trim(), partsTo[1].trim());
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (input.equals("blah")) {
                    throw new TreeBuddyException("OOPS!!! I don't understand that command.");
                } else {
                    tasks.add(new Task(input));
                    System.out.println("added: " + input);
                }
            } catch (TreeBuddyException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}