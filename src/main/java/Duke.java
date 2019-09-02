import java.util.Scanner;
import java.util.Vector;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static Vector<Task> inputs = new Vector<>();
    static Storage dukeData;

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

    static class InputException extends DukeException {
        InputException(String errorMsg) {
            super("Invalid Input\n\n" + errorMsg);
        }
    }

    private static void exitConversation() {
        PrintBuffer.addElement("Bye. Hope to see you again soon!");
        System.out.println(PrintBuffer.getPrint());
    }

    private static void updateTasks() throws DukeException {
        dukeData.setData(inputs);
        PrintBuffer.addElement("You currently have " + Integer.toString(inputs.size())
                + ((inputs.size() == 1) ? " task in the list." : " tasks in the list."));
    }

    private static void startDuke() {
        try {
            dukeData = new Storage("data/duke.txt");
            inputs = dukeData.getData();
        } catch (DukeException e) {
            PrintBuffer.addElement(e.getMessage());
            System.out.println(PrintBuffer.getPrint());
        }

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");

            if (commands[0].equals("bye")) {
                exitConversation();
                break;
            }

            try {
                switch (commands[0]) {
                case "done":
                    try {
                        int index = Integer.parseInt(commands[1]) - 1;
                        inputs.get(index).markDone();
                        PrintBuffer.addElement("Nice! I've marked this task as done:");
                        PrintBuffer.addElement(inputs.get(index).toString());
                        dukeData.setData(inputs);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InputException("Invalid index entered. Type 'list' to see your list.");
                    }
                    break;

                case "list":
                    PrintBuffer.addElement("Here are the tasks in your list:");
                    for (int i = 0; i < inputs.size(); i++) {
                        String temp = Integer.toString(i + 1) + ".";
                        PrintBuffer.addElement(temp.concat(inputs.get(i).toString()));
                    }
                    break;

                case "delete":
                    try {
                        int index = Integer.parseInt(commands[1]) - 1;
                        Task removed = inputs.remove(index);
                        PrintBuffer.addElement("Noted. I've removed this task:");
                        PrintBuffer.addElement(removed.toString());
                        updateTasks();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InputException("Invalid index entered. Type 'list' to see your list.");
                    }
                    break;

                case "find":
                    if (commands.length == 1) {
                        throw new InputException("☹ OOPS!!! You need to enter a keyword.");
                    }
                    PrintBuffer.addElement("Here are the matching tasks in your list:");
                    String keyword = input.replaceFirst("find ", "");
                    int resultCount = 0;
                    for (int i = 0; i < inputs.size(); i++) {
                        String description = inputs.get(i).getTaskName();
                        if (description.contains(keyword)) {
                            String temp = Integer.toString(resultCount++ + 1) + ".";
                            PrintBuffer.addElement(temp.concat(inputs.get(i).toString()));
                        }
                    }
                    break;

                case "todo":
                    if (commands.length == 1) {
                        throw new InputException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    inputs.addElement(new Todo(input.replaceFirst("todo ", "")));
                    PrintBuffer.addElement("Got it. I've added this todo:");
                    PrintBuffer.addElement(inputs.lastElement().toString());
                    updateTasks();
                    break;

                case "deadline":
                    try {
                        if (commands[1].equals("/by")) {
                            throw new InputException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        inputs.addElement(new Deadline(input.substring(0, input.lastIndexOf(" /by"))
                                .replaceFirst("deadline ", ""),
                                input.split("/by ")[1]));
                        PrintBuffer.addElement("Got it. I've added this deadline:");
                        PrintBuffer.addElement(inputs.lastElement().toString());
                        updateTasks();
                    } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                        throw new InputException("Please ensure that you enter the full command.\n"
                                + "Deadline: deadline <task name> /by <DD/MM/YY HHMM>\n"
                                + "Event: event <task name> /at <start as DD/MM/YY HHMM>_<end as DD/MM/YY HHMM>");
                    }
                    break;

                case "event":
                    try {
                        if (commands[1].equals("/at")) {
                            throw new InputException("☹ OOPS!!! The description of a event cannot be empty.");
                        }

                        inputs.addElement(new Event(input.substring(0, input.lastIndexOf(" /at"))
                                .replaceFirst("event ", ""),
                                input.split("/at ")[1]));
                        PrintBuffer.addElement("Got it. I've added this event:");
                        PrintBuffer.addElement(inputs.lastElement().toString());
                        updateTasks();
                    } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                        throw new InputException("Please ensure that you enter the full command.\n"
                                + "Deadline: deadline <task name> /by <DD/MM/YY HHMM>\n"
                                + "Event: event <task name> /at <start as DD/MM/YY HHMM>_<end as DD/MM/YY HHMM>");
                    }
                    break;

                default:
                    throw new InputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                PrintBuffer.addElement(e.getMessage());
            } finally {
                System.out.println(PrintBuffer.getPrint());
            }
        }
    }
}
