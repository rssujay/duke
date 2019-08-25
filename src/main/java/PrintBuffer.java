import java.util.Vector;

public class PrintBuffer {
    private static Vector<String> buffer = new Vector<>();

    /**
     * Adds a new string to the back of the buffer.
     * @param content - Any string to be wrapped.
     */
    public static void addElement(String content) {
        buffer.addElement(content);
    }

    /**
     * Prints out each item currently in the buffer
     * vector on a new line. Subsequently, clears the buffer.
     */
    public static String getPrint() {
        String horizontalLine = "____________________________________________________________";
        String constructed = "";
        constructed = constructed.concat(horizontalLine + "\n");
        for (int i = 0; i < buffer.size(); i++) {
            constructed = constructed.concat(buffer.get(i) + "\n");
        }
        constructed = constructed.concat(horizontalLine);
        buffer.clear();
        return constructed;
    }

    /**
     * Clears the current items in the print buffer.
     */
    public static void clear() {
        buffer.clear();
    }
}
