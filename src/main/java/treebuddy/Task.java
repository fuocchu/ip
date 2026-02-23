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

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }
    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}