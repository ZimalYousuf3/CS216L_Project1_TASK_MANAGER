public class TaskList {
    private Node head;

    public TaskList() {
        this.head = null;
    }

    // Add a new task to the end of the list
    public void addTask(Task task) {  
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
        }

        else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }
    }          

    // Delete a task by title
    public Task deleteTask(String title) { 
        
        if (head == null) {
            return null;
        }

        else if (head.data.getTitle().equalsIgnoreCase(title)) {
            Task removedTask = head.data;
            head = head.next;
            return removedTask;
        }

        else {
            Node temp = head;

            while (temp.next != null && !temp.next.data.getTitle().equalsIgnoreCase(title)) {
                temp = temp.next;
            }

            if (temp.next == null) {
                return null;
            }
            
            else {
                Task removedTask = temp.next.data;
                temp.next = temp.next.next;
                return removedTask;
            }
        }
    }   

    // View all tasks in the list
    public void viewAll() {

        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        else {
            Node temp = head;

            while (temp != null) {
                System.out.println(temp.data.toString());
                temp = temp.next;
            }
        }

    }

    // Search for a task by title
    public Task searchByTitle(String title) { // linear search, returns Task or null
        Node temp = head;

        while (temp != null) {

            if (temp.data.getTitle().equalsIgnoreCase(title)) {
                return temp.data;
            }

            temp = temp.next;
        }

        return null;
    } 

    // Sort tasks by priority (1: High, 2: Medium, 3: Low)
    public void sortByPriority() { // bubble sort

        if (head == null || head.next == null) {
            return; // No need to sort if the list is empty or has only one task
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;

            while (current.next != null) {
                if (current.data.getPriority() > current.next.data.getPriority()) {
                    // Swap the tasks
                    Task tempTask = current.data;
                    current.data = current.next.data;
                    current.next.data = tempTask;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }            
}
