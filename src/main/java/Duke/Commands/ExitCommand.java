package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        this.setExit(true);
        return ui.showGoodbye();
    }
}
