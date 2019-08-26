import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TaskList implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<Task> todos;

    public TaskList() {
        todos = new LinkedList<>();
    }

    public boolean action(String move) {
        Parser current = new Parser(move);
        try {
            if (current.getType().equals("list")) {
                Ui.printList(todos);
            } else if (current.getType().equals("done")) {
                completeTask(current.getTaskNo());
            } else if (current.getType().equals("delete")) {
                deleteTask(current.getTaskNo());
            } else {
                addTask(current);
            }
            return true;
        } catch (DukeException de) {
            Ui.printError(de);
            return false;
        } catch (DateTimeParseException dtpe) {
            System.out.println(dtpe.getMessage() + "\nUse dd/MM/yyyy HHmm formatting" );
            return false;
        }
    }

    private void addTask(Parser parser) throws DukeException {
        Task current;
        if (parser.getType().equals("todo")) {
            if (parser.getDescription().equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            current = new ToDo(parser.getDescription());
        } else if (parser.getType().equals("deadline")) {
            if (parser.remainingArgs.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            current = new Deadline(parser.getDeadlineDescription(), parser.getDeadlineDate());
        } else if (parser.getType().equals("event")) {
            if (parser.remainingArgs.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            current = new Event(parser.getEventDescription(), parser.getEventDate());
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        todos.add(current);
        System.out.println("Got it. I've added this task: \n" + current
                + "\nNow you have " + todos.size() + " tasks in the list.");
    }

    private void completeTask(int taskNo) throws DukeException {
        try {
            Task current = todos.get(taskNo);
            current.markAsDone();
            Ui.completedTask(current);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give correct task number");
        }
    }

    private void deleteTask(int taskNo) throws DukeException {
        try {
            Task current = todos.get(taskNo);
            todos.remove(current);
            Ui.deletedTask(current, todos);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give correct task number");
        }
    }


}
