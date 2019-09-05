package Duke;

import Duke.Commands.Command;
import Duke.Exceptions.DukeException;

public class Duke {
    private TaskList tasks;
    private Ui userInterface;
    private Storage dukeData;
    private boolean shutdown = false;

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
            System.out.println(this.isShutdown());
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
     Entry point into this java program.
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke("data/duke.txt");
        dukeInstance.startDuke();
    }

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
