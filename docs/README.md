# TreeBuddy User Guide

Welcome to **TreeBuddy** 

TreeBuddy helps you manage your tasks directly from the terminal. You can:

- Add tasks (ToDo, Deadline, Event)
- List all tasks
- Find tasks
- Mark tasks as done
- Unmark tasks
- Delete tasks
- Automatically save tasks
- Exit the program

---

# ▶ Getting Started

Run the application and you will see:

```
  _______              ____            _     _
 |__   __|            |  _ \          | |   | |
    | |_ __ ___  ___  | |_) |_   _  __| | __| |_   _
    | | '__/ _ \/ _ \ |  _ <| | | |/ _` |/ _` | | | |
    | | | |  __/  __/ | |_) | |_| | (_| | (_| | |_| |
    |_|_|  \___|\___| |____/ \__,_|\__,_|\__,_|\__, |
                                                __/ |
                                               |___/

Hello! I'm TreeBuddy
What can I do for you?
```

You can now start typing commands.

---

# ▶ Features

## 1️⃣ Adding a ToDo Task

Adds a simple task without date/time.

### Format
```
todo DESCRIPTION
```

### Example
```
todo borrow book
```

### Output
```
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
```

- The description cannot be empty.

---

## 2️⃣ Adding a Deadline Task

Adds a task with a deadline.

### Format
```
deadline DESCRIPTION /by DATE
```

### Example
```
deadline return /by 2026-03-10
```

### Output
```
Got it. I've added this task:
  [D][ ] return (by: Mar 10 2026)
Now you have 2 tasks in the list.
```

- You must include `/by`.

---

## 3️⃣ Adding an Event Task

Adds a task with a start and end time.

### Format
```
event DESCRIPTION /from START /to END
```

### Example
```
event project meeting /from Mon 2pm /to 4pm
```

### Output
```
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
```

- You must include both `/from` and `/to`.

---

## 4️⃣ Listing All Tasks

Displays all tasks currently stored.

### Format
```
list
```

### Example Output
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Mar 10 2026)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
```

If the list is empty:
```
Your list is empty.
```

---

## 5️⃣ Marking a Task as Done

Marks a task as completed.

### Format
```
mark TASK_NUMBER
```

### Example
```
mark 1
```

### Output
```
Nice! I've marked this task as done:
  [T][X] borrow book
```

---

## 6️⃣ Unmarking a Task

Marks a task as not completed.

### Format
```
unmark TASK_NUMBER
```

### Example
```
unmark 1
```

### Output
```
OK, I've marked this task as not done yet:
  [T][ ] borrow book
```

---

## 7️⃣ Deleting a Task

Removes a task from the list.

### Format
```
delete TASK_NUMBER
```

### Example
```
delete 2
```

### Output
```
Noted. I've removed this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
```

---

## 8️⃣ Finding Tasks

Searches for tasks containing a keyword.

### Format
```
find KEYWORD
```

### Example
```
find book
```

### Output
```
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
```

If no match:
```
No matching tasks found.
```

- The keyword cannot be empty.

---

## 9️⃣ Exiting the Application

Closes TreeBuddy.

### Format
```
bye
```

### Output
```
Bye. Hope to see you again soon!
```

---

# Data Storage

- All tasks are automatically saved.
- Tasks are loaded when the program starts.
- You do not need to manually save anything.

# Error Handling

TreeBuddy will show helpful error messages if:

- You enter an unknown command
- You provide an invalid task number
- You miss required parameters (`/by`, `/from`, `/to`)
- You leave descriptions empty

Example:
```
OOPS!!! I don't understand that command.
```

---

# Command Summary

| Command | Description |
|----------|-------------|
| `todo DESCRIPTION` | Add a ToDo task |
| `deadline DESCRIPTION /by DATE` | Add a Deadline task |
| `event DESCRIPTION /from START /to END` | Add an Event task |
| `list` | Show all tasks |
| `mark NUMBER` | Mark task as done |
| `unmark NUMBER` | Mark task as not done |
| `delete NUMBER` | Delete a task |
| `find KEYWORD` | Search tasks |
| `bye` | Exit application |

---

# Thank You for Using TreeBuddy!

TreeBuddy is designed to be simple, fast, and easy to use from the command line.