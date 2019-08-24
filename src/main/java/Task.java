public class Task {
    protected String taskName;
    protected Boolean isDone;

    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markDone(){
        this.isDone = true;
    }
}
