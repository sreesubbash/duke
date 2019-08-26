import java.io.*;

public class Storage {


    public static void save(TaskList toSave) throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/src/main/java/notes.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(toSave);
        oos.close();
    }

    public static TaskList load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/notes.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TaskList toLoad = (TaskList) ois.readObject();
        ois.close();
        return toLoad;

    }

}
