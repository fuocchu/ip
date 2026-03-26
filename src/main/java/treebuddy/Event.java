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
     * @param from Start time of the event
     * @param to End time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of this event.
     *
     * @return The start time string
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of this event.
     *
     * @return The end time string
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from + " | " + to;
    }
}
