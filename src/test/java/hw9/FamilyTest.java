package hw9;

import hw09.enums.DayOfWeek;
import hw09.models.Family;
import hw09.models.Humans.Human;
import hw09.models.Pets.Dog;
import hw09.models.Pets.DomesticCat;
import hw09.models.Pets.Pet;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

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
    public void testDescribePet_OutputCorrectDescription() {
        Pet pet = new Dog("Buddy", 4, 80, Set.of("fetch", "bark"));

        Human mom = new Human("Alice", "Doge", 1990);
        Family family = new Family(mom, new Human("Bob", "Doge", 1989));
        family.addPet(pet);
        mom.setFamily(family);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        mom.describePet();

        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("I have a DOG"));;
        assertTrue(output.contains("they are 4 years old"));
        assertTrue(output.contains("very tricky"));
    }

    @Test
    public void testAddMultiplePets() {
        Family family = new Family(
                new Human("Mom", "Catson", 1985),
                new Human("Dad", "Catson", 1983)
        );

        Pet pet1 = new Dog("Rex", 3, 75, Set.of("bark", "fetch"));
        Pet pet2 = new DomesticCat("Murka", 2, 60, Set.of("sleep", "purr"));

        family.addPet(pet1);
        family.addPet(pet2);

        assertEquals(2, family.getPets().size());
        assertTrue(family.getPets().contains(pet1));
        assertTrue(family.getPets().contains(pet2));
    }

    @Test
    public void testEqualsAndHashCode() {
        Human mom1 = new Human("Alice", "Smith", 1980);
        Human dad1 = new Human("Bob", "Smith", 1978);
        Human mom2 = new Human("Alice", "Smith", 1980);
        Human dad2 = new Human("Bob", "Smith", 1978);

        Family family1 = new Family(mom1, dad1);
        Family family2 = new Family(mom2, dad2);

        assertEquals(family1, family2);
        assertEquals(family1.hashCode(), family2.hashCode());
    }


    @Test
    public void testToString() {
        Human human = new Human("John", "Doe", 1990, 100, Map.of(
                DayOfWeek.MONDAY.name(), "work",
                DayOfWeek.TUESDAY.name(), "gym"
        ));

        String result = human.toString();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("work"));
        assertTrue(result.contains("MONDAY"));
    }
}
