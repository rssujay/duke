package Duke.Exceptions;

public class InputException extends DukeException {
    public InputException(String errorMsg) {
        super("Invalid Input\n\n" + errorMsg);
    }
}
