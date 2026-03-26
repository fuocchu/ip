package treebuddy;

/**
 * Represents a generic task in TreeBuddy.
 *
 * A task has a description and a completion status.
 * Subclasses include ToDo, Deadline, and Event.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the task's completion state.
     *
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task has been marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of this task suitable for saving to file.
     *
     * @return The file-format string of this task
     */
    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
