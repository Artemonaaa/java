package hw05;

import hw05.enums.DayOfWeek;
import hw05.models.Human;

public class Main {
    String[][] schedule = {
            {DayOfWeek.MONDAY.name(), "go to work"},
            {DayOfWeek.TUESDAY.name(), "watch a movie"},
            {DayOfWeek.WEDNESDAY.name(), "read a book"},
            {DayOfWeek.THURSDAY.name(), "go shopping"},
            {DayOfWeek.FRIDAY.name(), "gym"},
            {DayOfWeek.SATURDAY.name(), "rest"},
            {DayOfWeek.SUNDAY.name(), "visit parents"}
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10_000_000; i++) {
            Human h = new Human("John" + i, "Doe", 1990);
            System.out.println(h.toString());
        }
        System.gc();
    }
}
