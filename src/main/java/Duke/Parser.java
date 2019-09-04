package Duke;

import Duke.Commands.Command;
import Duke.Commands.AddCommand;
import Duke.Commands.FindCommand;
import Duke.Commands.DeleteCommand;
import Duke.Commands.ExitCommand;
import Duke.Commands.UnknownCommand;
import Duke.Commands.ListCommand;
import Duke.Commands.DoneCommand;
import Duke.Exceptions.DukeException;
import Duke.Exceptions.InputException;

public class Parser {
    /**
     * Parses an input string into a workable command.
     * @param input user typed in this string.
     * @return a Command that can executed.
     * @throws DukeException Storage errors or input errors.
     */
    public static Command parse(String input) throws DukeException {
        Command command;
        String[] components = input.split(" ");

        try {
            switch (components[0]) {
            case "done":
                command = new DoneCommand(Integer.parseInt(components[1]) - 1);
                break;

            case "list":
                command = new ListCommand();
                break;

            case "delete":
                command = new DeleteCommand(Integer.parseInt(components[1]) - 1);
                break;

            case "find":
                command = new FindCommand(components, input);
                break;

            case "todo":
            case "deadline":
            case "event":
                command = new AddCommand(components, input);
                break;

            case "bye":
                command = new ExitCommand();
                break;

            default:
                command = new UnknownCommand();
            }
            return command;
        } catch (NumberFormatException e) {
            throw new InputException("Invalid index entered. Type 'list' to see your list.");
        }
    }
}
