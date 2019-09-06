package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private TaskList tasks;
    private Ui userInterface;
    private Storage dukeData;
    private boolean shutdown = false;

    /**
     * Constructor for CLI interface.
     * @param filePath A String representing path to the storage file on hard disk.
     */
    public Duke(String filePath) {
        userInterface = new Ui();
        try {
            dukeData = new Storage(filePath);
            tasks = new TaskList(dukeData.loadData());
        } catch (DukeException e) {
            userInterface.showFormatted(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for GUI version of Duke.
     */
    public Duke() {
        userInterface = new Ui();
        userInterface.setPrintOutput(false);

        try {
            dukeData = new Storage("data/duke.txt");
            tasks = new TaskList(dukeData.loadData());
        } catch (DukeException e) {
            userInterface.showFormatted(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void startDuke() {
        userInterface.showWelcome();

        while (!this.isShutdown()) {
            String input = userInterface.readInput();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, dukeData, userInterface);
                this.setShutdown(command.isExit());
            } catch (DukeException e) {
                userInterface.showFormatted(e.getMessage());
            }
        }
    }

    /**
     * Entry point into this java program, for CLI version.
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke("data/duke.txt");
        dukeInstance.startDuke();
    }

    /**
     * GUI layer to interact with Duke.
     * @param input String input from GUI layer.
     * @return output String to be printed out to the GUI layer.
     */
    public String getResponse(String input) {
        if (!this.isShutdown()) {
            try {
                Command command = Parser.parse(input);
                String response = command.execute(tasks, dukeData, userInterface);
                this.setShutdown(command.isExit());
                return response;
            } catch (DukeException e) {
                return userInterface.showFormatted(e.getMessage());
            }
        } else {
            return "Chat bot Closed.";
        }
    }

    private void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    private boolean isShutdown() {
        return shutdown;
    }
}
