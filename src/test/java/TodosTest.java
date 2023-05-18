import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class TodosTest {
    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
    String[] subtasks = { "Молоко", "Яйца", "Хлеб", "Приложение" };
    Epic epic = new Epic(55, subtasks);
    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );

    Todos todos = new Todos();

    @BeforeEach
    public void stats() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
    }

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQuerySimoleTask() {

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryEpic() {

        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryMeeting() {

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryMeetingStart() {

        Task[] expected = {};
        Task[] actual = todos.search("вторник");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryMultiplTasks() {

        Task[] expected = {epic, meeting};
        Task[] actual = todos.search("Приложение");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryEmptyTask() {

        Task[] expected = {};
        Task[] actual = todos.search("завтра");
        Assertions.assertArrayEquals(expected, actual);
    }
}
