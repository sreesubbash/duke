import java.util.LinkedList;
import java.util.List;

public class TodoList {

    private List<String> todos;

    public TodoList () {
        todos = new LinkedList<>();
    }

    public void action(String move) {
        if (move.equals("list")) {
            printList();
        } else {
            todos.add(move);
            System.out.println("added: " + move);
        }
    }

    private void printList() {
        for (int i = 0; i < todos.size(); i++) {
            System.out.println( (i+1)+ ". " + todos.get(i) );
        }
    }
}
