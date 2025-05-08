package hw6.test;

import hw06.enums.DayOfWeek;
import hw06.models.Family;
import hw06.models.Human;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FamilyTest {
    @Test
    public void testAddChild() {
        Family family = new Family(
                new Human("Mom", "Doge", 1999),
                new Human("Dad", "Doge", 1999)
        );
        Human child = new Human("Kid", "Doge", 1999);

        family.addChild(child);

        assertEquals(3, family.countFamily());
    }

    @Test
    public void testDeleteChildByIndex_Valid() {
        Family family = new Family(
                new Human("Mom", "Doge", 1999),
                new Human("Dad", "Doge", 1999)
        );
        family.addChild(new Human("Kid1", "Doge", 1999));
        family.addChild(new Human("Kid2", "Doge", 1999));

        boolean result = family.deleteChild(1);
        assertTrue(result);
        assertEquals(3, family.countFamily());
    }

    @Test
    public void testDeleteChildByObject_NotFound() {
        Family family = new Family(
                new Human("Mom", "Doge", 1999),
                new Human("Dad", "Doge", 1999)
        );
        family.addChild(new Human("Kid1", "Doge", 1999));

        Human stranger = new Human("Other", "Doge", 1999);
        boolean result = family.deleteChild(stranger);

        assertFalse(result);
        assertEquals(3, family.countFamily());
    }

    @Test
    public void testToString() {
        Human human = new Human("John", "Doe", 1990, 100, new String[][] {
                {DayOfWeek.MONDAY.name(), "work"},
                {DayOfWeek.MONDAY.name(), "gym"}
        });

        String result = human.toString();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("work"));
        assertTrue(result.contains("MONDAY"));
    }
}