package hw8;

import hw08.enums.DayOfWeek;
import hw08.models.Family;
import hw08.models.Humans.Human;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FamilyTest {

    @Test
    public void shouldAddChildToFamily() {
        Human mom = new Human("Mom", "Doge", "01/01/1990", 90);
        Human dad = new Human("Dad", "Doge", "01/01/1989", 95);
        Family family = new Family(mom, dad);

        Human child = new Human("Kid", "Doge", "01/01/2015", 100);
        family.addChild(child);

        assertEquals(3, family.countFamily());
    }

    @Test
    public void shouldDeleteChildByValidIndex() {
        Family family = new Family(
                new Human("Mom", "Doge", "01/01/1990", 90),
                new Human("Dad", "Doge", "01/01/1989", 95)
        );

        family.addChild(new Human("Kid1", "Doge", "01/01/2010", 90));
        family.addChild(new Human("Kid2", "Doge", "01/01/2012", 85));

        boolean deleted = family.deleteChild(1);

        assertTrue(deleted);
        assertEquals(3, family.countFamily());
    }

    @Test
    public void shouldNotDeleteChildThatDoesNotExist() {
        Family family = new Family(
                new Human("Mom", "Doge", "01/01/1990", 90),
                new Human("Dad", "Doge", "01/01/1989", 95)
        );
        family.addChild(new Human("Kid1", "Doge", "01/01/2010", 90));

        Human stranger = new Human("Other", "Stranger", "01/01/2011", 100);

        boolean deleted = family.deleteChild(stranger);

        assertFalse(deleted);
        assertEquals(3, family.countFamily());
    }

    @Test
    public void toStringShouldContainFormattedDate() {
        Human human = new Human("John", "Doe", "20/03/2016", 100, Map.of(
                DayOfWeek.MONDAY.name(), "work",
                DayOfWeek.TUESDAY.name(), "gym"
        ));

        String result = human.toString();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("20/03/2016"));
        assertTrue(result.contains("MONDAY"));
    }

    @Test
    public void describeAgeShouldReturnHumanReadableAge() {
        LocalDate birthDate = LocalDate.now().minusYears(10).minusMonths(2).minusDays(5);
        String formatted = birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Human human = new Human("Young", "Person", formatted, 90);

        String description = human.describeAge();

        assertTrue(description.contains("10 years"));
        assertTrue(description.contains("2 months"));
        assertTrue(description.contains("5 days"));
    }
}
