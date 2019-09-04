package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui){
        this.setExit(true);
    }
}
