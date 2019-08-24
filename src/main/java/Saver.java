import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Saver {
    public Saver() {
    }

    public static void save(TodoList toSave) throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/main/java/notes.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(toSave);
        oos.close();
    }

    public static TodoList load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/notes.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TodoList toLoad = (TodoList) ois.readObject();
        ois.close();
        return toLoad;
    }
}
