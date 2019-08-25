public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This constructor is used for recreation of Deadline from storage.
     * @param done 1 if task has been marked complete, 0 otherwise.
     * @param description the name or description of the deadline.
     * @param by the due date/time of the deadline.
     */
    public Deadline(int done, String description, String by) {
        super(description);
        this.isDone = (done == 1);
        this.by = by;
    }

    @Override
    public String storeString() {
        return "D | " + super.storeString() + " | " + this.getBy();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    private String getBy() {
        return this.by;
    }
}
