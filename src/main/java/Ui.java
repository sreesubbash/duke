import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ui {

    /**
     * Greets user
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    /**
     * Serves user while waiting for input
     *
     * @param taskList on which user can have his actions on
     */
    public static void serve(TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String move = scanner.nextLine();
            if (move.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            taskList.action(move);
            try {
                Storage.save(taskList);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Prints errors raised due to wrong input
     *
     * @param de DukeException to be printed
     */
    public static void printError(DukeException de) {
        System.out.println(de.getMessage());
    }

    /**
     * Prints task which was completed
     *
     * @param t task which was completed
     */
    public static void completedTask(Task t) {
        System.out.println("Nice! I've marked this task as done: \n" + t);
    }

    /**
     * Prints task which was deleted
     *
     * @param t task which was deleted
     * @param todos remaining list used to calculate tasks left
     */
    public static void deletedTask(Task t, List<Task> todos) {
        System.out.println("Noted. I've removed this task: \n" + t
                + "\nNow you have " + todos.size() + " tasks in the list.");
    }

    /**
     * Prints entire list of tasks
     *
     * @param todos list with all the tasks
     */
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
