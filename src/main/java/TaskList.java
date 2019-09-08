import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Task> todos;

    public TaskList() {
        todos = new LinkedList<>();
    }

    /**
     * Carries out action on TaskList using move.
     *
     * @param move is passed by user thru UI
     * @return true if move was successful else false
     */
    public String action(String move) {
        Parser current = new Parser(move);
        try {
            if (current.getType().equals("list")) {
                return Ui.printList(todos);
            } else if (current.getType().equals("find")) {
                return Ui.findTasks(todos, current.getTaskName());
            } else if (current.getType().equals("done")) {
                return completeTask(current.getTaskNo());
            } else if (current.getType().equals("delete")) {
                return deleteTask(current.getTaskNo());
            } else {
                return addTask(current);
            }
        } catch (DukeException de) {
            return Ui.printError(de);
        } catch (DateTimeParseException dtpe) {
            return (dtpe.getMessage() + "\nUse dd/MM/yyyy HHmm formatting");
        }
    }

    private String addTask(Parser parser) throws DukeException {
        Task current;
        if (parser.getType().equals("todo")) {
            if (parser.getDescription().equals("")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            current = new ToDo(parser.getDescription());
        } else if (parser.getType().equals("deadline")) {
            if (parser.remainingArgs.equals("")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            current = new Deadline(parser.getDeadlineDescription(), parser.getDeadlineDate());
        } else if (parser.getType().equals("event")) {
            if (parser.remainingArgs.equals("")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            current = new Event(parser.getEventDescription(), parser.getEventDate());
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        todos.add(current);
        assert todos.size() != 0;
        return ("Got it. I've added this task: \n" + current
                + "\nNow you have " + todos.size() + " tasks in the list.");
    }

    private String completeTask(int taskNo) throws DukeException {
        try {
            Task current = todos.get(taskNo);
            current.markAsDone();
            return Ui.completedTask(current);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give correct task number");
        }
    }

    private String deleteTask(int taskNo) throws DukeException {
        try {
            Task current = todos.get(taskNo);
            todos.remove(current);
            return Ui.deletedTask(current, todos);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give correct task number");
        }
    }


}
