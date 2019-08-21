import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TodoList {

    private List<Task> todos;

    public TodoList () {
        todos = new LinkedList<>();
    }

    public void action(String move) {
        List<String> moveSplit = new LinkedList<>(Arrays.asList(move.split(" ")));
        String type = moveSplit.get(0);
        moveSplit.remove(0);
        String task = String.join(" ", moveSplit);
        if (move.equals("list")) {
            printList();
        } else if (type.equals("done")) {
            Task current = todos.get(Integer.parseInt(moveSplit.get(0)) - 1);
            current.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + current);
        } else {
            addTask(type, task);
        }
    }

    private void addTask(String type, String task) {
        Task current;
        if (type.equals("todo")) {
            current = new ToDo(task);
        } else if (type.equals("deadline")) {
            List<String> temp  =  new LinkedList<>(Arrays.asList(task.split(" /by ")));
            current = new Deadline(temp.get(0), temp.get(1));
        } else if (type.equals("event")) {
            List<String> temp  =  new LinkedList<>(Arrays.asList(task.split(" /at ")));
            current = new Event(temp.get(0), temp.get(1));
        } else {
            return;
        }
        todos.add(current);
        System.out.println("Got it. I've added this task: \n" + current
            + "\nNow you have " + todos.size() + " tasks in the list.");
    }

    private void printList() {
        for (int i = 0; i < todos.size(); i++) {
            System.out.println( (i+1)+ ". " + todos.get(i) );
        }
    }
}
