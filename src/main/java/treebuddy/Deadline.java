package treebuddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task that must be completed before a specific date.
 */
public class Deadline extends Task {
    private LocalDate by;
    /**
     * Creates a new Deadline task.
     *
     * @param description Description of the task
     * @param by Deadline date in ISO format (yyyy-MM-dd)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}