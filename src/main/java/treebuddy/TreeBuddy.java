package treebuddy;

import java.io.IOException;

/**
 * Main entry point of the TreeBuddy application.
 *
 * TreeBuddy is a simple command line task management application
 * that allows users to add, delete, mark, unmark, find and list tasks.
 */
public class TreeBuddy {

    private static TaskList taskList;
    private static Storage storage = new Storage("./data/treebuddy.txt");
    private static Ui ui = new Ui();

    /**
     * Starts the TreeBuddy application.
     *
     * Loads tasks from storage, prints the welcome message,
     * and continuously reads user input until the user exits.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError("Error loading file.");
            taskList = new TaskList();
        }

        ui.printWelcome();

        boolean exit = false;

        while (!exit) {
            String input = ui.readCommand();
            try {
                exit = handleCommand(input);
            } catch (TreeBuddyException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("Invalid command format.");
            }
        }

        ui.printGoodbye();
        ui.close();
    }

    /**
     * Handles user input commands and executes corresponding actions.
     *
     * @param input The full user input command
     * @return true if the application should exit, false otherwise
     * @throws Exception If the command format is invalid
     */
    private static boolean handleCommand(String input) throws Exception {
        String command = Parser.parseCommand(input);

        switch (command) {
        case "bye":
            return true;
        case "list":
            ui.printList(taskList);
            break;
        case "find":
            findTask(input);
            break;
        case "delete":
            deleteTask(input);
            break;
        case "mark":
            markTask(input);
            break;
        case "unmark":
            unmarkTask(input);
            break;
        case "todo":
            addTodo(input);
            break;
        case "deadline":
            addDeadline(input);
            break;
        case "event":
            addEvent(input);
            break;
        default:
            throw new TreeBuddyException("OOPS!!! I don't understand that command.");
        }
        return false;
    }

    /**
     * Finds and prints tasks that contain the given keyword.
     *
     * @param input The full user input containing the find command
     * @throws TreeBuddyException If the keyword is empty
     */
    private static void findTask(String input) throws TreeBuddyException {
        String keyword = Parser.parseFindKeyword(input);
        ui.printFoundTasks(taskList.find(keyword));
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param input The user input containing the delete command
     * @throws TreeBuddyException If the index is invalid
     */
    private static void deleteTask(String input) throws TreeBuddyException, IOException {
        int index = Parser.parseIndex(input);
        if (!taskList.isValidIndex(index)) {
            throw new TreeBuddyException("Invalid task number.");
        }
        Task removed = taskList.remove(index);
        ui.printTaskDeleted(removed, taskList.size());
        storage.save(taskList.getAll());
    }

    /**
     * Marks a task as done.
     *
     * @param input The user input containing the mark command
     * @throws TreeBuddyException If the index is invalid
     */
    private static void markTask(String input) throws TreeBuddyException, IOException {
        int index = Parser.parseIndex(input);
        if (!taskList.isValidIndex(index)) {
            throw new TreeBuddyException("Invalid task number.");
        }
        taskList.get(index).markAsDone();
        ui.printTaskMarked(taskList.get(index));
        storage.save(taskList.getAll());
    }

    /**
     * Marks a task as not done.
     *
     * @param input The user input containing the unmark command
     * @throws TreeBuddyException If the index is invalid
     */
    private static void unmarkTask(String input) throws TreeBuddyException, IOException {
        int index = Parser.parseIndex(input);
        if (!taskList.isValidIndex(index)) {
            throw new TreeBuddyException("Invalid task number.");
        }
        taskList.get(index).unmark();
        ui.printTaskUnmarked(taskList.get(index));
        storage.save(taskList.getAll());
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param input The user input containing the todo command
     * @throws TreeBuddyException If the description is empty
     */
    private static void addTodo(String input) throws TreeBuddyException, IOException {
        String desc = Parser.parseTodoDescription(input);
        Task t = new ToDo(desc);
        taskList.add(t);
        ui.printTaskAdded(t, taskList.size());
        storage.save(taskList.getAll());
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param input The user input containing the deadline command
     * @throws TreeBuddyException If the format is invalid
     */
    private static void addDeadline(String input) throws TreeBuddyException, IOException {
        String[] parts = Parser.parseDeadline(input);
        Task t = new Deadline(parts[0], parts[1]);
        taskList.add(t);
        ui.printTaskAdded(t, taskList.size());
        storage.save(taskList.getAll());
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param input The user input containing the event command
     * @throws TreeBuddyException If the format is invalid
     */
    private static void addEvent(String input) throws TreeBuddyException, IOException {
        String[] parts = Parser.parseEvent(input);
        Task t = new Event(parts[0], parts[1], parts[2]);
        taskList.add(t);
        ui.printTaskAdded(t, taskList.size());
        storage.save(taskList.getAll());
    }
}
