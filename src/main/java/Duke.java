import java.util.Scanner;
import java.util.Vector;

public class Duke {
    private static Vector<String> buffer = new Vector<>();

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
        buffer.addElement("Hello! I'm Duke");
        buffer.addElement("What can I do for you?");
        printBuffer();
        startDuke();
    }

    /**
     * Prints out each item currently in the buffer
     * vector on a new line. Subsequently, clears the buffer.
     */
    private static void printBuffer() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        for (int i = 0; i < buffer.size(); i++) {
            System.out.println(buffer.get(i));
        }
        System.out.println(horizontalLine);
        buffer.clear();
    }

    private static void exitConversation() {
        buffer.addElement("Bye. Hope to see you again soon!");
        printBuffer();
    }

    private static void startDuke() {
        Scanner scanner = new Scanner(System.in);
        Vector<Task> inputs = new Vector<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");

            if (input.equals("bye")) {
                exitConversation();
                break;
            } else if (commands[0].equals("done")) {
                int index = Integer.parseInt(commands[1]) - 1;

                //To be replaced with exception handling
                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markDone();
                    buffer.addElement("Nice! I've marked this task as done:");
                    buffer.addElement(inputs.get(index).toString());
                } else {
                    buffer.addElement("Invalid index. Type 'list' to see your list.");
                }
            } else if (input.equals("list")) {
                buffer.addElement("Here are the tasks in your list:");
                for (int i = 0; i < inputs.size(); i++) {
                    String temp = Integer.toString(i + 1)  + ".";
                    buffer.addElement(temp.concat(inputs.get(i).toString()));
                }
            } else {
                switch (commands[0]) {
                    case "todo":
                        inputs.addElement(new Todo(input.replaceFirst("todo ","")));
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
                        inputs.addElement(new Task(input));
                }
                buffer.addElement("Got it. I've added this task:\n" + inputs.lastElement().toString()
                        + "\nNow you have " + Integer.toString(inputs.size())
                        + ((inputs.size() == 1) ? " task in the list." : " tasks in the list."));
            }
            printBuffer();
        }
    }
}
