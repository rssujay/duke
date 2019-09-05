package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.InputException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class UnknownCommand extends Command {

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        throw new InputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
