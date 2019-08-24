import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static TodoList todoList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        try {
            Duke.serve(Saver.load());
        } catch (IOException | ClassNotFoundException ce) {
            Duke.serve(new TodoList());
        }
    }

    private static void serve(TodoList todoList) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String move = scanner.nextLine();
            if (move.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            todoList.action(move);
            try {
                Saver.save(todoList);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
