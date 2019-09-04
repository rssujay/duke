package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.InputException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

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
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            List<String> formattedOutput = new ArrayList<>();
            Task completed = taskList.markDone(index);
            storage.setData(taskList.getTasks());
            formattedOutput.add("Nice! I've marked this task as done:");
            formattedOutput.add(completed.toString());
            ui.showFormatted(formattedOutput);
        } catch (IndexOutOfBoundsException e) {
            throw new InputException("Invalid index entered. Type 'list' to see your list.");
        }
    }
}
