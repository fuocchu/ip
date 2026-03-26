package treebuddy;

/**
 * Parses raw user input into structured data for command execution.
 *
 * All methods are static and throw TreeBuddyException when the input
 * does not conform to the expected format.
 */
public class Parser {

    /**
     * Extracts the command keyword from user input (the first word).
     *
     * @param input The full user input string
     * @return The command keyword (e.g., "todo", "list", "bye")
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses a one-indexed task number from a command like "mark 2" or "delete 3".
     *
     * @param input The full user input string
     * @return Zero-based index of the task
     * @throws TreeBuddyException If the task number is missing or not a valid integer
     */
    public static int parseIndex(String input) throws TreeBuddyException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TreeBuddyException("OOPS!!! Please provide a task number.");
        }
        try {
            return Integer.parseInt(parts[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new TreeBuddyException("OOPS!!! Task number must be an integer.");
        }
    }

    /**
     * Parses the description from a todo command.
     *
     * @param input The full user input string starting with "todo"
     * @return The trimmed description of the todo task
     * @throws TreeBuddyException If the description is empty
     */
    public static String parseTodoDescription(String input) throws TreeBuddyException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TreeBuddyException("OOPS!!! The description of a todo cannot be empty.");
        }
        return parts[1].trim();
    }

    /**
     * Parses the description and deadline date from a deadline command.
     *
     * The expected format is: {@code deadline <description> /by <yyyy-MM-dd>}
     *
     * @param input The full user input string starting with "deadline"
     * @return A String array where index 0 is the description and index 1 is the date
     * @throws TreeBuddyException If the format is invalid or the date is not in yyyy-MM-dd format
     */
    public static String[] parseDeadline(String input) throws TreeBuddyException {
        String body = input.substring(8).trim();
        String[] parts = body.split("/by", 2);
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
        return new String[]{desc, by};
    }

    /**
     * Parses the description, start time, and end time from an event command.
     *
     * The expected format is: {@code event <description> /from <start> /to <end>}
     *
     * @param input The full user input string starting with "event"
     * @return A String array where index 0 is description, 1 is from, 2 is to
     * @throws TreeBuddyException If the format is invalid or required fields are missing
     */
    public static String[] parseEvent(String input) throws TreeBuddyException {
        String body = input.substring(5).trim();
        String[] fromParts = body.split("/from", 2);
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
        return new String[]{desc, toParts[0].trim(), toParts[1].trim()};
    }

    /**
     * Parses the search keyword from a find command.
     *
     * @param input The full user input string starting with "find"
     * @return The trimmed keyword to search for
     * @throws TreeBuddyException If the keyword is empty
     */
    public static String parseFindKeyword(String input) throws TreeBuddyException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TreeBuddyException("OOPS!!! The keyword cannot be empty.");
        }
        return parts[1].trim();
    }
}
