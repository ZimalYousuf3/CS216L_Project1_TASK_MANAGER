import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        UndoStack undoStack = new UndoStack();

        do {
            System.out.println("\n========== TASK MANAGER MENU ==========\n");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Undo Last Action");
            System.out.println("6. Show Last Action");
            System.out.println("7. Search Task");
            System.out.println("8. Sort Tasks by Priority");
            System.out.println("9. Exit");
            System.out.println("\n========================================\n");

            int choice = readInt(scanner, "Enter your choice: ");
            System.out.println(); 

            switch (choice) {
                case 1:
                    // Added Task 
                    addTask(scanner, taskList, undoStack);
                    break;

                case 2:
                    // Deleted Task 
                    deleteTask(scanner, taskList, undoStack);
                    break;

                case 3:
                    // View All Tasks 
                    taskList.viewAll();
                    break;

                case 4:
                    // Mark Task as Completed 
                    markCompleted(scanner, taskList, undoStack);
                    break;

                case 5:
                    // Undo Last Action 
                    undo(taskList, undoStack);
                    break;
                    
                case 6:
                    // Show Last Action 
                    showLastAction(undoStack);
                    break;

                case 7:
                    // Search Task 
                    searchTask(scanner, taskList);
                    break;

                case 8:
                    // Sort Tasks by Priority 
                    taskList.sortByPriority();
                    System.out.println("Tasks sorted by priority successfully.");
                    break;

                case 9:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;

            }

        } while (true);
    }

    static int readInt(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } 
            else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // Clear the invalid input
            }
        }
    }

    static String readTitle(Scanner sc) {

        while (true) {
            System.out.print("Enter task title: ");
            String title = sc.nextLine().trim();

            if (!title.isEmpty()) {
                return title;
            } 

            else {
                System.out.println("Title cannot be empty. Please try again.");
            }
        }
    }

    static void addTask(Scanner sc, TaskList list, UndoStack undoStack) {

        String title = readTitle(sc);

        if (list.searchByTitle(title) != null){
            System.out.println("Task with this title already exists. Please choose a different title.");
            return;
        }

        int priority = readInt(sc, "Enter task priority (1: High, 2: Medium, 3: Low): ");
        
        while (priority < 1 || priority > 3) {

            System.out.println("Invalid priority. Please enter 1, 2, or 3.");
            priority = readInt(sc, "Enter task priority (1: High, 2: Medium, 3: Low): ");

        }

        Task newTask = new Task(title, priority);
        list.addTask(newTask);
        undoStack.push(new Action("Add", newTask));
        System.out.println("Task added successfully.");

    }

    static void deleteTask(Scanner sc, TaskList list, UndoStack undoStack) {

        String title = readTitle(sc);

        if (list.searchByTitle(title) == null) {
            System.out.println("Task not found. Please check the title and try again.");
            return;
        }

        Task removedTask = list.deleteTask(title);
        undoStack.push(new Action("Delete", removedTask));
        System.out.println("Task deleted successfully.");

    }

    static void markCompleted(Scanner sc, TaskList list, UndoStack undoStack) {

        String title = readTitle(sc);

        Task found = list.searchByTitle(title);

        if (found == null) {
            System.out.println("Task not found. Please check the title and try again.");
            return;
        }

        else {
            found.setCompleted(true);
            undoStack.push(new Action("Mark Completed", found));
            System.out.println("Task marked as completed.");
        }

    }

    static void searchTask(Scanner sc, TaskList list) {

        String title = readTitle(sc);

        Task found = list.searchByTitle(title);

        if (found == null) {
            System.out.println("Task not found. Please check the title and try again.");
            return;
        }

        else {
            System.out.println("Task found: \n" + found.toString());
        }

    }

    static void undo(TaskList list, UndoStack undoStack) {

        if (undoStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }

        else {
            Action lastAction = undoStack.pop();

            if (lastAction.getType().equals("Add")) {
                list.deleteTask(lastAction.getTask().getTitle());
            }

            else if (lastAction.getType().equals("Delete")) {
                list.addTask(lastAction.getTask());
            }

            else if (lastAction.getType().equals("Mark Completed")) {
                lastAction.getTask().setCompleted(false);
            }

            else {
                System.out.println("Unknown action type. Cannot undo.");
                return;
            }

            System.out.println("Undo successful");
        }

    }

    static void showLastAction(UndoStack undoStack) {

        Action action = undoStack.peek();

        if (action == null) {
            System.out.println("No actions to show.");
            return;
        }

        else {
            System.out.println("Last Action: " + action.getType() + " | Task: " + action.getTask().getTitle());
        }

    }

}
