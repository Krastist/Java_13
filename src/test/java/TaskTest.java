import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.ClasspathResourceSelector;

public class TaskTest {

    @Test
    public void testTrueOrFalseSimple() {
        SimpleTask simple = new SimpleTask(5, "Позвонить родителям");

        Assertions.assertTrue(simple.matches("Позвонить")); //Проверка поиска
        Assertions.assertFalse(simple.matches("жены")); //Поиск отсутствующего слова
        Assertions.assertFalse(simple.matches("Родителям")); //Проверка регистра
        Assertions.assertFalse(simple.matches(".")); //Проверка знаков
    }

    @Test
    public void testTrueOrFalseEpic() {
        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Epic emptyEpic = new Epic(55, new String[]{});

        Assertions.assertTrue(epic.matches("Молоко")); //Проверка поиска
        Assertions.assertFalse(epic.matches("молоко")); //Проверка регистра
        Assertions.assertFalse(epic.matches("Икра")); //Поиск отсутствующего слова
        Assertions.assertFalse(epic.matches("10")); //Поиск числа
        Assertions.assertFalse(epic.matches(",")); //Проверка знаков
        Assertions.assertFalse(emptyEpic.matches("Молоко")); //Проверка пустого массива
    }

    @Test
    public void testTrueOrFalseMeeting() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(meeting.matches("Выкатка")); //Проверка поиска topic
        Assertions.assertFalse(meeting.matches("выкатка")); //Проверка регистра topic
        Assertions.assertFalse(meeting.matches("Новая")); //Првоерка отсутствующего слова topic
        Assertions.assertFalse(meeting.matches(",")); //Проверка знаков topic
        Assertions.assertTrue(meeting.matches("Приложение")); //Проверка поиска project
        Assertions.assertFalse(meeting.matches("приложение")); //Проверка регистра project
        Assertions.assertFalse(meeting.matches("Наше")); //Првоерка отсутствующего слова project
        Assertions.assertFalse(meeting.matches(",")); //Проверка знаков project
        Assertions.assertFalse(meeting.matches("вторник")); //Проверка поиска start
        Assertions.assertFalse(meeting.matches("Вторник")); //Проверка регистра start
        Assertions.assertFalse(meeting.matches("завтра")); //Првоерка отсутствующего слова start
        Assertions.assertFalse(meeting.matches(",")); //Проверка знаков start
    }
}
