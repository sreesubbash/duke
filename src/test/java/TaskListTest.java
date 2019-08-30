import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    @Test
    public void deleteWrongTask() {
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.action("delete 1"));
    }
}