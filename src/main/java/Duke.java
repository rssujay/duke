import java.util.Scanner;
import java.util.Vector;

public class Duke {
    private static String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        //levelOneEcho();
        //levelTwoEcho();
        levelThreeEcho();
    }

    private static void levelOneEcho() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(horizontalLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else {
                System.out.println(horizontalLine);
                System.out.println(input);
                System.out.println(horizontalLine);
            }
        }
    }

    private static void levelTwoEcho() {
        Scanner scanner = new Scanner(System.in);
        Vector<String> inputs = new Vector<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(horizontalLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.print(i + 1);
                    System.out.println(". " + inputs.get(i));
                }
                System.out.println(horizontalLine);
            } else {
                inputs.addElement(input);
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
                System.out.println(horizontalLine);
            }
        }
    }

    private static void levelThreeEcho() {
        Scanner scanner = new Scanner(System.in);
        Vector<Task> inputs = new Vector<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(horizontalLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else if (input.length() >= 6 && input.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;

                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" [" + inputs.get(index).getStatusIcon() + "] " + inputs.get(index).getTaskName());
                }
                else {
                    System.out.println("Invalid index. Type 'list' to see your list.");
                }

            } else if (input.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.print(i + 1);
                    System.out.println(".[" + inputs.get(i).getStatusIcon() + "] " + inputs.get(i).getTaskName());
                }
                System.out.println(horizontalLine);
            } else {
                inputs.addElement(new Task(input));
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
                System.out.println(horizontalLine);
            }
        }
    }
}
