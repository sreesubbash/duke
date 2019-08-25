import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static TaskList TaskList;

    public static void main(String[] args) {
        Ui.greet();
        try {
            Ui.serve(Storage.load());
        } catch (IOException | ClassNotFoundException ce) {
            Ui.serve(new TaskList());
        }
    }
}
