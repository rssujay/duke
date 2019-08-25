import java.util.Scanner;
import java.util.Vector;

public class Duke {
    /**
     Entry point into this java program.
     */
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        PrintBuffer.addElement("Hello! I'm Duke");
        PrintBuffer.addElement("What can I do for you?");
        System.out.println(PrintBuffer.getPrint());
        startDuke();
    }

    private static void exitConversation() {
        PrintBuffer.addElement("Bye. Hope to see you again soon!");
        System.out.println(PrintBuffer.getPrint());
    }

    private static void startDuke() {
        Scanner scanner = new Scanner(System.in);
        Vector<Task> inputs = new Vector<>();
        Storage dukeData = new Storage("data/duke.txt");
        inputs = dukeData.getData();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");

            if (input.equals("bye")) {
                exitConversation();
                break;
            } else if (commands[0].equals("done")) {
                try {
                    int index = Integer.parseInt(commands[1]) - 1;
                    inputs.get(index).markDone();
                    PrintBuffer.addElement("Nice! I've marked this task as done:");
                    PrintBuffer.addElement(inputs.get(index).toString());
                    dukeData.setData(inputs);
                } catch (ArrayIndexOutOfBoundsException e) {
                    PrintBuffer.clear();
                    PrintBuffer.addElement("Invalid index. Type 'list' to see your list.");
                }
            } else if (input.equals("list")) {
                PrintBuffer.addElement("Here are the tasks in your list:");
                for (int i = 0; i < inputs.size(); i++) {
                    String temp = Integer.toString(i + 1)  + ".";
                    PrintBuffer.addElement(temp.concat(inputs.get(i).toString()));
                }
            } else {
                try {
                    switch (commands[0]) {
                    case "todo":
                        if (commands.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        inputs.addElement(new Todo(input.replaceFirst("todo ", "")));
                        break;
                    case "deadline":
                        inputs.addElement(new Deadline(input.substring(0, input.lastIndexOf(" /by"))
                                .replaceFirst("deadline ", ""),
                                input.split("/by ")[1]));
                        break;
                    case "event":
                        inputs.addElement(new Event(input.substring(0, input.lastIndexOf(" /at"))
                                .replaceFirst("event ", ""),
                                input.split("/at ")[1]));
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    dukeData.setData(inputs);
                    PrintBuffer.addElement("Got it. I've added this task:\n" + inputs.lastElement().toString()
                            + "\nNow you have " + Integer.toString(inputs.size())
                            + ((inputs.size() == 1) ? " task in the list." : " tasks in the list."));
                } catch (DukeException e) {
                    PrintBuffer.addElement(e.getMessage());
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    PrintBuffer.addElement("Please ensure that you enter the full command.\n"
                            + "Deadline: deadline <task name> /by <end>\n"
                            + "Event: event <task name> /at <duration>");
                }
            }
            System.out.println(PrintBuffer.getPrint());
        }
    }
}
