package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public abstract class Command {
    private boolean isExit;

    public abstract String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
