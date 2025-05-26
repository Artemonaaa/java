package hw12;

import hw12.controller.FamilyController;
import hw12.model.Family;
import hw12.model.Humans.Human;
import hw12.service.FamilyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FamilyControllerTest {

    private FamilyService familyService;
    private FamilyController familyController;

    @BeforeEach
    void setUp() {
        familyService = new FamilyService();
        familyController = new FamilyController(familyService);
    }

    @Test
    void testFillWithTestData() {
        familyController.fillWithTestData();
        List<Family> families = familyService.getAllFamilies();
        assertEquals(3, families.size());
    }

    @Test
    void testCreateNewFamily() {
        Human father = new Human("John", "Doe", 1980);
        Human mother = new Human("Jane", "Doe", 1982);

        familyService.createNewFamily(father, mother);
        List<Family> families = familyService.getAllFamilies();

        assertEquals(1, families.size());
        assertEquals(father, families.get(0).getFather());
        assertEquals(mother, families.get(0).getMother());
    }

    @Test
    void testDeleteFamilyByIndex() {
        familyController.fillWithTestData();
        familyService.deleteFamilyByIndex(0);

        List<Family> families = familyService.getAllFamilies();
        assertEquals(2, families.size());
    }

    @Test
    void testDeleteAllChildrenOlderThan() {
        familyController.fillWithTestData();
        familyService.deleteAllChildrenOlderThan(18);

        familyService.getAllFamilies().forEach(family ->
                family.getChildren().forEach(child ->
                        assertTrue(child.getAge() <= 18)
                )
        );
    }

    @Test
    void testDisplayAllFamilies() {
        familyController.fillWithTestData();
        familyController.displayAllFamilies();
        assertEquals(3, familyService.getAllFamilies().size());
    }

    @Test
    void testDisplayFamiliesBiggerThan() {
        familyController.fillWithTestData();
        Scanner scanner = new Scanner("2");
        familyController.displayFamiliesBiggerThan(scanner);
        assertTrue(familyService.getFamiliesBiggerThan(2).size() > 0);
    }

    @Test
    void testDisplayFamiliesLessThan() {
        familyController.fillWithTestData();
        Scanner scanner = new Scanner("5");
        familyController.displayFamiliesLessThan(scanner);
        assertTrue(familyService.getFamiliesLessThan(5).size() > 0);
    }

    @Test
    void testCountFamiliesWithMemberNumber() {
        familyController.fillWithTestData();
        Scanner scanner = new Scanner("2");
        familyController.countFamiliesWithMemberNumber(scanner);
        assertEquals(3, familyService.countFamiliesWithMemberNumber(2));
    }

    @Test
    void testEditFamily_AdoptChild() {
        familyController.fillWithTestData();
        Scanner scanner = new Scanner("1\n1\nJohn\nDoe\n2010\n7\n10\n80");
        familyController.editFamily(scanner);
        assertEquals(1, familyService.getFamilyByIndex(0).getChildren().size());
    }

    @Test
    void testEditFamily_BornChild() {
        familyController.fillWithTestData();
        Scanner scanner = new Scanner("1\n2\nMike\nAnna");
        familyController.editFamily(scanner);
        assertEquals(1, familyService.getFamilyByIndex(0).getChildren().size());
    }

    @Test
    void testDeleteAllChildrenOlderThanWithInput() {
        familyController.fillWithTestData();
        Scanner scanner = new Scanner("18");
        familyController.deleteAllChildrenOlderThan(scanner);
        familyService.getAllFamilies().forEach(family ->
                family.getChildren().forEach(child ->
                        assertTrue(child.getAge() <= 18)
                )
        );
    }
}
