package hw12;

import hw09.models.Family;
import hw09.models.FamilyService;
import hw09.models.Humans.Human;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FamilyServiceTest {
    private FamilyService familyService;
    private Family family1;
    private Family family2;

    @BeforeEach
    void setUp() {
        Human father1 = new Human("NameFather1", "SurnameFather1", "01/01/1980", 100, null);
        Human mother1 = new Human("NameMother1", "SurnameMother1", "01/01/1982", 100, null);
        Human child1 = new Human("NameChild1", "SurnameChild1", "01/01/2010", 90, null);
        Human child2 = new Human("NameChild2", "SurnameChild2", "01/01/2018", 90, null);

        family1 = new Family(father1, mother1);
        family1.addChild(child1);
        family1.addChild(child2);

        Human father2 = new Human("NameFather2", "SurnameFather1", "01/01/1975", 95, null);
        Human mother2 = new Human("NameMother2", "SurnameMother1", "01/01/1977", 95, null);
        family2 = new Family(father2, mother2);

        List<Family> families = new ArrayList<>();
        families.add(family1);
        families.add(family2);

        familyService = new FamilyService(families);
    }

    @Test
    void testGetFamiliesBiggerThan() {
        List<Family> result = familyService.getFamiliesBiggerThan(2);
        assertEquals(1, result.size());
        assertTrue(result.contains(family1));
    }

    @Test
    void testGetFamiliesLessThan() {
        List<Family> result = familyService.getFamiliesLessThan(3);
        assertEquals(1, result.size());
        assertTrue(result.contains(family2));
    }

    @Test
    void testCountFamiliesWithMemberNumber() {
        long count = familyService.countFamiliesWithMemberNumber(4);
        assertEquals(1, count);
    }

    @Test
    void testDeleteAllChildrenOlderThan() {
        familyService.deleteAllChildrenOlderThan(10);

        List<Human> childrenAfter = family1.getChildren();
        assertEquals(1, childrenAfter.size());
        assertEquals("NameChild2", childrenAfter.getFirst().getName());
    }
}
