package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.InputException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the relevant task in the task list.
     * @param taskList TaskList instance.
     * @param storage Storage instance.
     * @param ui Ui instance.
     * @throws DukeException Invalid index or storage error.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            List<String> formattedOutput = new ArrayList<>();
            List<Task> tasks = taskList.getTasks();

            Task removed = taskList.removeTask(index);
            storage.setData(tasks);
            formattedOutput.add("Noted. I've removed this task:\n" + removed.toString());
            formattedOutput.add("You currently have " + tasks.size()
                    + ((tasks.size() == 1) ? " task in the list." : " tasks in the list."));
            ui.showFormatted(formattedOutput);

        } catch (IndexOutOfBoundsException e) {
            throw new InputException("Invalid index entered. Type 'list' to see your list.");
        }
    }
}
