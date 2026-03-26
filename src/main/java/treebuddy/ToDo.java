package treebuddy;

/**
 * Represents a simple task without date or time constraints.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task.
     *
     * @param description Description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
