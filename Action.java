public class Action {

    private String type;
    private Task task;

    public Action(String type, Task task) {
        this.type = type;
        this.task = task;
    }

    // Getters
    public String getType() {
        return type;
    }

    public Task getTask() {
        return task;
    }
}
