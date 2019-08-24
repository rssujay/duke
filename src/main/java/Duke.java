import java.util.Scanner;
import java.util.Vector;

public class Duke {
    private static Vector<String> buffer = new Vector<>();

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

        //levelOne();
        //levelTwo();
        levelThree();
    }

    private static void printBuffer(){
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        for (int i = 0; i < buffer.size(); i++){
            System.out.println(buffer.get(i));
        }
        System.out.println(horizontalLine);
        buffer.clear();
    }

    private static void exitConversation(){
        buffer.addElement("Bye. Hope to see you again soon!");
        printBuffer();
    }

    private static void levelOne() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exitConversation();
                break;
            }
            else {
                buffer.addElement(input);
                printBuffer();
            }
        }
    }

    private static void levelTwo() {
        Scanner scanner = new Scanner(System.in);
        Vector<String> inputs = new Vector<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exitConversation();
                break;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < inputs.size(); i++) {
                    String temp = Integer.toString(i + 1);
                    temp = temp.concat(". " + inputs.get(i));
                    buffer.addElement(temp);
                }
            }
            else {
                inputs.addElement(input);
                buffer.addElement("added: ".concat(input));
            }
            printBuffer();
        }
    }

    private static void levelThree() {
        Scanner scanner = new Scanner(System.in);
        Vector<Task> inputs = new Vector<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exitConversation();
                break;
            }
            else if (input.length() >= 6 && input.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;

                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markDone();
                    buffer.addElement("Nice! I've marked this task as done:");
                    buffer.addElement(" [" + inputs.get(index).getStatusIcon() + "] " + inputs.get(index).getTaskName());
                }
                else {
                    buffer.addElement("Invalid index. Type 'list' to see your list.");
                }
            }
            else if (input.equals("list")) {
                for (int i = 0; i < inputs.size(); i++) {
                    String temp = Integer.toString(i + 1);
                    buffer.addElement(temp.concat(".[" + inputs.get(i).getStatusIcon() + "] " + inputs.get(i).getTaskName()));
                }
            }
            else {
                inputs.addElement(new Task(input));
                buffer.addElement("added: " + input);
            }
            printBuffer();
        }
    }
}
