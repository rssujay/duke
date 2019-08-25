import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

public class Storage {
    private File dukeData;

    /**
     * This constructor creates the file if needed.
     * @param fileLocation relative path of the text file to store data in.
     */
    public Storage(String fileLocation) {
        try {
            dukeData = new File(fileLocation);
            dukeData.createNewFile();
        } catch (IOException e) {
            PrintBuffer.addElement(e.getMessage());
            System.out.println(PrintBuffer.getPrint());
        }
    }

    /**
     * This method retrieves data from the text file, and constructs objects to insert back into the list.
     * @return tasks
     */
    public Vector<Task> getData() {
        Vector<Task> tasks = new Vector<>();
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(dukeData));
            while (true) {
                String currentLine = inputStream.readLine();
                if (currentLine == null) {
                    break;
                } else {
                    String[] arguments = currentLine.split(" \\| ");
                    switch (arguments[0]) {
                    case "T":
                        tasks.addElement(new Todo(Integer.parseInt(arguments[1]), arguments[2]));
                        break;
                    case "D":
                        tasks.addElement(new Deadline(Integer.parseInt(arguments[1]), arguments[2], arguments[3]));
                        break;
                    default:
                        tasks.addElement(new Event(Integer.parseInt(arguments[1]), arguments[2], arguments[3]));
                    }
                }
            }
            inputStream.close();
        } catch (IOException e) {
            PrintBuffer.addElement(e.getMessage());
            System.out.println(PrintBuffer.getPrint());
        }
        return tasks;
    }

    /**
     * This method takes in a vector and calls each task's storeString method to store its data in the correct format.
     * @param tasks A vector of tasks currently in the program.
     */
    public void setData(Vector<Task> tasks) {
        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter(dukeData));
            for (Task task : tasks) {
                outputStream.write(task.storeString());
                outputStream.newLine();
            }
            outputStream.close();
        } catch (IOException e) {
            PrintBuffer.addElement(e.getMessage());
            System.out.println(PrintBuffer.getPrint());
        }
    }
}
