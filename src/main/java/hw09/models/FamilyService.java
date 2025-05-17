package hw09.models;

import java.util.List;
import java.util.stream.Collectors;

public class FamilyService {
    private final List<Family> families;

    public FamilyService(List<Family> families) {
        this.families = families;
    }

    public void displayAllFamilies() {
        families.forEach(System.out::println);
    }

    public List<Family> getFamiliesBiggerThan(int count) {
        return families.stream()
                .filter(f -> f.countFamily() > count)
                .collect(Collectors.toList());
    }

    public List<Family> getFamiliesLessThan(int count) {
        return families.stream()
                .filter(f -> f.countFamily() < count)
                .collect(Collectors.toList());
    }

    public long countFamiliesWithMemberNumber(int number) {
        return families.stream()
                .filter(f -> f.countFamily() == number)
                .count();
    }

    public void deleteAllChildrenOlderThan(int ageLimit) {
        families.forEach(family -> family.getChildren()
                .removeIf(child -> child.getAge() > ageLimit));
    }

    public List<Family> getAllFamilies() {
        return families;
    }
}
