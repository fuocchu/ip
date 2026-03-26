package treebuddy;

/**
 * Represents application-specific exceptions thrown by TreeBuddy.
 *
 * Used to signal invalid commands, missing arguments, or out-of-range
 * task indices that should be shown to the user as error messages.
 */
public class TreeBuddyException extends Exception {

    /**
     * Creates a new TreeBuddyException with the given error message.
     *
     * @param message The error message to display to the user
     */
    public TreeBuddyException(String message) {
        super(message);
    }
}
