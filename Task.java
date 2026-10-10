public class Task {

    private String title;
    private int priority;
    private boolean completed;

    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Setters
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // toString method for printing
    public String toString() {

        if (priority == 1) {
            return "\nTitle: " + title + " | Priority: High | Status: " + (completed ? "Completed" : "Not Completed");
        } 

        else if (priority == 2) {
            return "\nTitle: " + title + " | Priority: Medium | Status: " + (completed ? "Completed" : "Not Completed");
        } 

        else if (priority == 3) {
            return "\nTitle: " + title + " | Priority: Low | Status: " + (completed ? "Completed" : "Not Completed");
        }

        return "\nTitle: " + title + " | Priority: " + priority + " | Status: " + (completed ? "Completed" : "Not Completed");
    }
}
