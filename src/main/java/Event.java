public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * This constructor is used for recreation of Deadline from storage.
     * @param done 1 if task has been marked complete, 0 otherwise.
     * @param description the name or description of the event.
     * @param at the actual date/time/duration of the event.
     */
    public Event(int done, String description, String at) {
        super(description);
        this.isDone = (done == 1);
        this.at = at;
    }

    @Override
    public String storeString() {
        return "E | " + super.storeString() + " | " + this.getAt();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    private String getAt() {
        return this.at;
    }
}
