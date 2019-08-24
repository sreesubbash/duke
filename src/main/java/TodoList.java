import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TodoList implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<Task> todos;

    public TodoList() {
        todos = new LinkedList<>();
    }

    public void action(String move) {
        List<String> args = new LinkedList<>(Arrays.asList(move.split(" ")));
        String type = args.get(0);
        args.remove(0);
        String remainingArgs = String.join(" ", args);
        try {
            if (move.equals("list")) {
                printList();
            } else if (type.equals("done")) {
                completeTask(args);
            } else if (type.equals("delete")) {
                deleteTask(args);
            } else {
                addTask(type, remainingArgs);
            }
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        } catch (DateTimeParseException dtpe) {
            System.out.println(dtpe.getMessage() + "\nUse dd/MM/yyyy HHmm formatting" );
        }
    }

    private void addTask(String type, String task) throws DukeException {
        Task current;
        if (type.equals("todo")) {
            if (task.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            current = new ToDo(task);
        } else if (type.equals("deadline")) {
            if (task.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            List<String> temp = new LinkedList<>(Arrays.asList(task.split(" /by ")));
            if (temp.size() != 2) {
                throw new DukeException("☹ OOPS!!! Set deadline after \"/by\"");
            }
            current = new Deadline(temp.get(0), temp.get(1));
        } else if (type.equals("event")) {
            if (task.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            List<String> temp = new LinkedList<>(Arrays.asList(task.split(" /at ")));
            if (temp.size() != 2) {
                throw new DukeException("☹ OOPS!!! Set timing after \"/at\"");
            }
            current = new Event(temp.get(0), temp.get(1));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        todos.add(current);
        System.out.println("Got it. I've added this task: \n" + current
                + "\nNow you have " + todos.size() + " tasks in the list.");
    }

    private void completeTask(List<String> args) throws DukeException {
        try {
            int taskNo = Integer.parseInt(args.get(0)) - 1;
            Task current = todos.get(taskNo);
            current.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + current);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give correct task number");
        }
    }

    private void deleteTask(List<String> args) throws DukeException {
        try {
            int taskNo = Integer.parseInt(args.get(0)) - 1;
            Task current = todos.get(taskNo);
            todos.remove(current);
            System.out.println("Noted. I've removed this task: \n" + current
                    + "\nNow you have " + todos.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give correct task number");
        }
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i));
        }
    }
}
