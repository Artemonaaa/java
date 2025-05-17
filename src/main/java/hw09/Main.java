package hw09;

import hw09.models.Family;
import hw09.models.FamilyService;
import hw09.models.Humans.Human;

import java.util.ArrayList;
import java.util.List;

public final class Main {
    public static void main(String[] args) {
        Human mother = new Human("NameMother", "SurName", "01/01/1980", 100);
        Human father = new Human("NameFather", "SurName", "01/01/1978", 100);

        Human child1 = new Human("NameChild1", "SurName", "01/01/2000", 90);
        Human child2 = new Human("NameChild2", "SurName", "01/01/2018", 90);

        Family family = new Family(mother, father);
        family.addChild(child1);
        family.addChild(child2);

        List<Family> families = new ArrayList<>();
        families.add(family);

        FamilyService familyService = new FamilyService(families);

        System.out.println("Before delete:");
        familyService.displayAllFamilies();

        familyService.deleteAllChildrenOlderThan(10);

        System.out.println("\nAfter delete:");
        familyService.displayAllFamilies();
    }
}
