import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    private String raw;
    List<String> args;
    String type;
    String remainingArgs;
    List<String> argsWithDate;

    public Parser(String move) {
        raw = move;
        args = new LinkedList<>(Arrays.asList(move.split(" ")));
        type = args.get(0);
        args.remove(0);
        remainingArgs = String.join(" ", args);
    }

    public String getType() {
        if (args.size() == 0) {
            return raw;
        } else {
            return type;
        }
    }

    public int getTaskNo() throws DukeException {
        try {
            int taskNo = Integer.parseInt(args.get(0)) - 1;
            return taskNo;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a task number");
        }
    }

    public String getTaskName() throws DukeException {
        try {
            String name = args.get(0);
            return name;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give a task name");
        }
    }

    public String getDescription() {
        return remainingArgs;
    }

    private void parseArgsWithDate(String delimitter) throws DukeException {
        argsWithDate = new LinkedList<>(Arrays.asList(remainingArgs.split(delimitter)));
        if (argsWithDate.size() != 2) {
            throw new DukeException("☹ OOPS!!! Set deadline after" + delimitter);
        }
    }

    public String getDeadlineDescription() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /by ");
        }
        return argsWithDate.get(0);
    }

    public String getDeadlineDate() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /by ");
        }
        return argsWithDate.get(1);
    }

    public String getEventDescription() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /at ");
        }
        return argsWithDate.get(0);
    }

    public String getEventDate() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /at ");
        }
        return argsWithDate.get(1);
    }

    public static LocalDateTime parseInput(String at) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime temp = LocalDateTime.parse(at, formatter);
        //System.out.println(temp);
        return temp;
    }


}
