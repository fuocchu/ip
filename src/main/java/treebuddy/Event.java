package treebuddy;
/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {
    private String from;
    private String to;
    /**
     * Creates a new Event task.
     *
     * @param description Description of the event
     * @param from Start time
     * @param to End time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from + " | " + to;
    }
}