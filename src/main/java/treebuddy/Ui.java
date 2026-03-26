package treebuddy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user-facing input and output for TreeBuddy.
 *
 * This class is responsible for reading user commands and printing
 * all messages to the console.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Creates a new Ui object and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the next line of user input, trimmed of whitespace.
     *
     * @return The trimmed user input string
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints the welcome message and logo when the application starts.
     */
    public void printWelcome() {
        Logo.printLogo();
        System.out.println("Hello! I'm TreeBuddy");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the goodbye message when the user exits.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a confirmation message after a task is added.
     *
     * @param task The task that was added
     * @param size The current total number of tasks
     */
    public void printTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a confirmation message after a task is deleted.
     *
     * @param task The task that was removed
     * @param size The current total number of tasks after deletion
     */
    public void printTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a confirmation message after a task is marked as done.
     *
     * @param task The task that was marked
     */
    public void printTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints a confirmation message after a task is unmarked.
     *
     * @param task The task that was unmarked
     */
    public void printTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Prints all tasks currently in the task list.
     *
     * @param taskList The task list to display
     */
    public void printList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    /**
     * Prints the list of tasks matching a search keyword.
     *
     * @param found The list of matching tasks
     */
    public void printFoundTasks(ArrayList<Task> found) {
        System.out.println("Here are the matching tasks in your list:");
        if (found.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            for (int i = 0; i < found.size(); i++) {
                System.out.println((i + 1) + ". " + found.get(i));
            }
        }
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to display
     */
    public void printError(String message) {
        System.out.println(message);
    }

    /**
     * Closes the input scanner.
     */
    public void close() {
        scanner.close();
    }
}
