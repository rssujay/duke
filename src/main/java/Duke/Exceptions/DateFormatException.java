package Duke.Exceptions;

public class DateFormatException extends DukeException {
    public DateFormatException(String errorMsg) {
        super("Invalid Date Format\n\n" + errorMsg);
    }
}
