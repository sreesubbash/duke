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

    /**
     * Constructor for Parser
     *
     * @param move string that user types in
     */
    public Parser(String move) {
        raw = move;
        args = new LinkedList<>(Arrays.asList(move.split(" ")));
        type = args.get(0);
        args.remove(0);
        remainingArgs = String.join(" ", args);
    }

    /**
     * Getter for type of action
     *
     * @return type of action
     */
    public String getType() {
        if (args.size() == 0) {
            return raw;
        } else {
            return type;
        }
    }

    /**
     * Getter for Task Number from action
     *
     * @return Task Number
     * @throws DukeException if task number incorrect
     */
    public int getTaskNo() throws DukeException {
        try {
            int taskNo = Integer.parseInt(args.get(0)) - 1;
            return taskNo;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a task number");
        }
    }

    /**
     * Getter for description without type
     *
     * @return description
     */
    public String getDescription() {
        return remainingArgs;
    }

    private void parseArgsWithDate(String delimitter) throws DukeException {
        argsWithDate = new LinkedList<>(Arrays.asList(remainingArgs.split(delimitter)));
        if (argsWithDate.size() != 2) {
            throw new DukeException("☹ OOPS!!! Set date after" + delimitter);
        }
    }

    /**
     * Getter Deadline Description
     *
     * @return Deadline description
     * @throws DukeException if no date provided
     */
    public String getDeadlineDescription() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /by ");
        }
        return argsWithDate.get(0);
    }

    /**
     * Getter Deadline Date
     *
     * @return Deadline date
     * @throws DukeException if no date provided
     */
    public String getDeadlineDate() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /by ");
        }
        return argsWithDate.get(1);
    }

    /**
     * Getter Event Description
     *
     * @return Event description
     * @throws DukeException if no date provided
     */
    public String getEventDescription() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /at ");
        }
        return argsWithDate.get(0);
    }

    /**
     * Getter Event Date
     *
     * @return Event date
     * @throws DukeException if no date provided
     */
    public String getEventDate() throws DukeException {
        if (argsWithDate == null) {
            this.parseArgsWithDate(" /at ");
        }
        return argsWithDate.get(1);
    }

    /**
     * Parses string date time to LocalDateTime object
     *
     * @return LocalDateTime object
     */
    public static LocalDateTime parseInput(String at) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime temp = LocalDateTime.parse(at, formatter);
        //System.out.println(temp);
        return temp;
    }


}
