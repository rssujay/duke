package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InputException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Mark task in task list as done.
     * @param taskList TaskList instance.
     * @param storage Storage instance.
     * @param ui Ui instance.
     * @throws DukeException invalid index or storage error.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            List<String> formattedOutput = new ArrayList<>();
            Task completed = taskList.markDone(index);
            storage.setData(taskList.getTasks());
            formattedOutput.add("Nice! I've marked this task as done:");
            formattedOutput.add(completed.toString());
            return ui.showFormatted(formattedOutput);
        } catch (IndexOutOfBoundsException e) {
            throw new InputException("Invalid index entered. Type 'list' to see your list.");
        }
    }
}
