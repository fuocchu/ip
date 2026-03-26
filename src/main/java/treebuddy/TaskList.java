package treebuddy;

import java.util.ArrayList;

/**
 * Manages the list of tasks in TreeBuddy.
 *
 * Provides methods to add, remove, retrieve, and search tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with an existing list of tasks.
     *
     * @param tasks The existing list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index Zero-based index of the task to remove
     * @return The removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Zero-based index of the task
     * @return The task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return true if there are no tasks, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the full list of tasks.
     *
     * @return ArrayList of all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Searches for tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for (case-insensitive)
     * @return ArrayList of matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Returns whether the given index is valid for this task list.
     *
     * @param index The index to validate
     * @return true if the index is within bounds, false otherwise
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
