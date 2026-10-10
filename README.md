# Task Manager

A console-based task management application built in Java as part of the **CS216L Data Structures Lab**. It helps organize tasks by priority and includes an **Undo** feature powered by a stack.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Data Structures and Algorithms](#data-structures-and-algorithms)
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)
- [Getting Started](#getting-started)
- [Sample Run](#sample-run)
- [Time Complexity](#time-complexity)
- [Input Validation](#input-validation)

## Overview
Task Manager lets a user keep a list of tasks, each with a title, a priority (High, Medium or Low) and a status (Completed or Not Completed). The program is menu driven and runs fully in the console.

Two main data structures work together:
- A **linked list** holds the actual tasks.
- A **stack** holds the history of user actions so that mistakes can be undone.

## Features
| No. | Feature | Description |
|---|---|---|
| 1 | Add Task | Adds a new task with a title and priority (duplicate titles are not allowed) |
| 2 | Delete Task | Removes a task by title |
| 3 | View All Tasks | Displays every task in the list |
| 4 | Mark Task as Completed | Changes a task's status to Completed |
| 5 | Undo Last Action | Reverses the last add, delete or mark completed action |
| 6 | Show Last Action | Shows the most recent action without removing it (stack peek) |
| 7 | Search Task | Finds a task by title |
| 8 | Sort Tasks by Priority | Orders tasks from High to Low priority |
| 9 | Exit | Closes the program |

## Data Structures and Algorithms

**Singly Linked List (`TaskList`, `Node`)**
Stores all tasks. Each node holds one `Task` and a reference to the next node. Deleting a task unlinks its node from the list.

**Stack (`UndoStack`, `Action`)**
A custom linked stack with `push`, `pop`, `peek` and `isEmpty`. Every add, delete and mark completed operation pushes an `Action` record. Undo pops the top record and reverses it. A stack is used because the most recent action must be undone first (Last In, First Out).

**Linear Search**
Used to find a task by title. A linear search fits a linked list because nodes can only be visited one after another.

**Bubble Sort**
Used to sort tasks by priority. It compares neighbouring nodes and swaps their data when they are in the wrong order, and repeats until no swaps are needed.

## Project Structure
```
CS216L_Project1_[GroupName]
|-- Main.java        # Menu, input validation and program flow
|-- Task.java        # Task data (title, priority, completed)
|-- Node.java        # Linked list node
|-- TaskList.java    # Linked list with add, delete, view, search, sort
|-- Action.java      # One undo record (type and task)
|-- UndoStack.java   # Stack with push, pop, peek, isEmpty
|-- README.md
```

## How It Works
- **Add:** the task is added to the linked list, then an `Add` action is pushed on the stack.
- **Delete:** the task is removed from the list, then a `Delete` action is pushed on the stack.
- **Mark Completed:** the task status is changed, then a `Mark Completed` action is pushed.
- **Undo:** the top action is popped from the stack.
  - `Add` is undone by deleting that task from the list.
  - `Delete` is undone by adding that task back to the list.
  - `Mark Completed` is undone by setting the task back to Not Completed.

## Getting Started

**Requirements:** Java JDK 8 or higher.

**Run from the terminal:**
```
javac *.java
java Main
```

**Run from an IDE (Eclipse or Visual Studio):** create a new Java project, add all `.java` files, and run `Main.java`.

## Sample Run
```
========== TASK MANAGER MENU ==========

1. Add Task
2. Delete Task
3. View All Tasks
...
Enter your choice: 1
Enter task title: Math Quiz
Enter task priority (1: High, 2: Medium, 3: Low): 2
Task added successfully.
```

## Time Complexity
| Operation | Time Complexity | Reason |
|---|---|---|
| Insert (at end) | O(n) | The list is traversed to reach the last node |
| Delete | O(n) | The list is traversed to find the task |
| Search (Linear) | O(n) | Each node may need to be checked |
| Sort (Bubble) | O(n^2) | Nested comparison passes over the list |
| Push | O(1) | Only the top reference changes |
| Pop | O(1) | Only the top reference changes |
| Peek | O(1) | Reads the top without traversal |

## Input Validation
- Menu choice and priority must be valid numbers (letters are rejected).
- Priority must be 1, 2 or 3.
- Task title cannot be empty.
- Duplicate task titles are not allowed.
- Deleting, searching or marking a task that does not exist shows a clear message.
- Undo on an empty stack shows a message instead of crashing.
