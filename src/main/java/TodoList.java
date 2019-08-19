import java.util.LinkedList;
import java.util.List;

public class TodoList {

    private List<Task> todos;

    public TodoList () {
        todos = new LinkedList<>();
    }

    public void action(String move) {
        String[] moveSplit = move.split(" ");
        if (move.equals("list")) {
            printList();
        } else if (moveSplit[0].equals("done")) {
            Task current = todos.get(Integer.parseInt(moveSplit[1])-1);
            current.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + current);


        } else {
            todos.add(new Task(move));
            System.out.println("added: " + move);
        }
    }

    private void printList() {
        for (int i = 0; i < todos.size(); i++) {
            System.out.println( (i+1)+ ". " + todos.get(i) );
        }
    }
}
