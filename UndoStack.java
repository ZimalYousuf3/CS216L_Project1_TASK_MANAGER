public class UndoStack {

    private class StackNode {

        Action data;
        StackNode next;

        StackNode(Action data) {

            this.data = data;
            this.next = null;
        }
    }

    private StackNode top;

    public UndoStack() {

        this.top = null;
    }

    // Push an action onto the stack
    public void push(Action action) {

        StackNode temp = new StackNode(action);
        temp.next = top;
        top = temp;

    }
    
    // Pop an action from the stack
    public Action pop() { 

        if (top == null) {       // returns null if empty
            
            return null;
        }

        StackNode temp = top;
        top = top.next;
        return temp.data;
    } 
    
    
    // Peek at the top action without removing it
    public Action peek() { 

        if (top == null) {       // returns null if empty
            
            return null;
        }

        return top.data;
    }      

    // Check if the stack is empty
    public boolean isEmpty() { 
        
        return top == null;
    }
}
