package Duke;

import Duke.Commands.Command;
import Duke.Exceptions.DukeException;

public class Duke {
    private TaskList tasks;
    private Ui userInterface;
    private Storage dukeData;

    Duke(String filePath) {
        userInterface = new Ui();
        try {
            dukeData = new Storage(filePath);
             tasks = new TaskList(dukeData.loadData());
        } catch (DukeException e) {
            userInterface.showFormatted(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void startDuke() {
        userInterface.showWelcome();
        boolean exitCheck = false;

        while (!exitCheck) {
            String input = userInterface.readInput();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, dukeData, userInterface);
                exitCheck = command.isExit();
            } catch (DukeException e) {
                userInterface.showFormatted(e.getMessage());
            }
        }
        userInterface.showGoodbye();
    }

    /**
     Entry point into this java program.
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke("data/duke.txt");
        dukeInstance.startDuke();
    }
}
