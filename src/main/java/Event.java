import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = Parser.parseInput(at);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMM yyyy h:mm a");
        return "[D]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }


}