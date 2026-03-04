package treebuddy;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
/**
 * Main entry point of the TreeBuddy application.
 *
 * TreeBuddy is a simple command line task management application
 * that allows users to add, delete, mark, unmark, find and list tasks.
 */
public class TreeBuddy {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storage = new Storage("./data/treebuddy.txt");
    /**
     * Starts the TreeBuddy application.
     *
     * Loads tasks from storage, prints the logo and greeting message,
     * and continuously reads user input until the user exits.
     *
     * @param args Command line arguments (not used)
     */
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
                exit = handleCommand(input);
            } catch (TreeBuddyException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid command format.");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
    /**
     * Handles user input commands and executes corresponding actions.
     *
     * @param input The full user input command
     * @return true if the application should exit, false otherwise
     * @throws Exception If command format is invalid
     */
    private static boolean handleCommand(String input) throws Exception {

        if (input.equals("bye")) {
            return true;
        }

        if (input.equals("list")) {
            printList();
            return false;
        }
        if (input.startsWith("find ")) {
            findTask(input);
            return false;
        }

        if (input.startsWith("delete ")) {
            deleteTask(input);
            return false;
        }

        if (input.startsWith("mark ")) {
            markTask(input);
            return false;
        }

        if (input.startsWith("unmark ")) {
            unmarkTask(input);
            return false;
        }

        if (input.startsWith("todo ")) {
            addTodo(input);
            return false;
        }

        if (input.startsWith("deadline ")) {
            addDeadline(input);
            return false;
        }

        if (input.startsWith("event ")) {
            addEvent(input);
            return false;
        }

        throw new TreeBuddyException("OOPS!!! I don't understand that command.");
    }
    /**
     * Finds and prints tasks that contain the given keyword.
     *
     * @param input The full user input containing the find command
     * @throws TreeBuddyException If keyword is empty
     */
    private static void findTask(String input) throws TreeBuddyException {
        String keyword = input.substring(5).trim();

        if (keyword.isEmpty()) {
            throw new TreeBuddyException("OOPS!!! The keyword cannot be empty.");
        }

        System.out.println("Here are the matching tasks in your list:");

        int count = 1;
        boolean found = false;

        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(count + ". " + t);
                count++;
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching tasks found.");
        }
    }
    /**
     * Prints all tasks currently stored in the task list.
     */
    private static void printList() {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    /**
     * Deletes a task at the specified index.
     *
     * @param input The user input containing the delete command
     * @throws Exception If the index is invalid
     */
    private static void deleteTask(String input) throws Exception {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        validateIndex(index);
        Task removed = tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
    /**
     * Marks a task as done.
     *
     * @param input The user input containing the mark command
     * @throws Exception If the index is invalid
     */
    private static void markTask(String input) throws Exception {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        validateIndex(index);
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index));
        storage.save(tasks);
    }
    /**
     * Marks a task as not done.
     *
     * @param input The user input containing the unmark command
     * @throws Exception If the index is invalid
     */
    private static void unmarkTask(String input) throws Exception {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        validateIndex(index);
        tasks.get(index).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(index));
        storage.save(tasks);
    }
    /**
     * Adds a new ToDo task to the task list.
     *
     * @param input The user input containing the todo command
     * @throws Exception If the description is empty
     */
    private static void addTodo(String input) throws Exception {
        String desc = input.substring(5).trim();
        if (desc.isEmpty()) {
            throw new TreeBuddyException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task t = new ToDo(desc);
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
    /**
     * Adds a new Deadline task to the task list.
     *
     * @param input The user input containing the deadline command
     * @throws Exception If the format is invalid
     */
    private static void addDeadline(String input) throws Exception {
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) {
            throw new TreeBuddyException("OOPS!!! Please specify /by for deadline.");
        }
        String desc = parts[0].trim();
        String by = parts[1].trim();
        if (desc.isEmpty()) {
            throw new TreeBuddyException("OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            java.time.LocalDate.parse(by);
        } catch (Exception e) {
            throw new TreeBuddyException(
                    "OOPS!!! Please enter date in format yyyy-MM-dd (e.g. 2026-03-10)."
            );
        }
        Task t = new Deadline(desc, by);
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
    /**
     * Adds a new Event task to the task list.
     *
     * @param input The user input containing the event command
     * @throws Exception If the format is invalid
     */
    private static void addEvent(String input) throws Exception {
        String[] fromParts = input.substring(6).split("/from", 2);
        if (fromParts.length < 2) {
            throw new TreeBuddyException("OOPS!!! Please specify /from and /to for event.");
        }
        String desc = fromParts[0].trim();
        if (desc.isEmpty()) {
            throw new TreeBuddyException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] toParts = fromParts[1].split("/to", 2);
        if (toParts.length < 2) {
            throw new TreeBuddyException("OOPS!!! Please specify /to for event.");
        }
        Task t = new Event(desc, toParts[0].trim(), toParts[1].trim());
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
    /**
     * Validates whether the given index exists in the task list.
     *
     * @param index The index to validate
     * @throws TreeBuddyException If index is out of range
     */
    private static void validateIndex(int index) throws TreeBuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new TreeBuddyException("Invalid task number.");
        }
    }
}