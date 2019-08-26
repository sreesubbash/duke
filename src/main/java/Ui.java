import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ui {

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    public static void serve(TaskList TaskList) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String move = scanner.nextLine();
            if (move.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            TaskList.action(move);
            try {
                Storage.save(TaskList);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void printError(DukeException de) {
        System.out.println(de.getMessage());
    }

    public static void completedTask(Task t) {
        System.out.println("Nice! I've marked this task as done: \n" + t);
    }

    public static void deletedTask(Task t, List<Task> todos) {
        System.out.println("Noted. I've removed this task: \n" + t
                + "\nNow you have " + todos.size() + " tasks in the list.");
    }

    public static void printList(List<Task> todos) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i));
        }
    }

    public static void findTasks(List<Task> todos, String name) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < todos.size(); i++) {
            Task curr = todos.get(i);
            if (curr.getDescription().contains(name)) {
                System.out.println((i + 1) + ". " + curr);
            }
        }
    }
}
