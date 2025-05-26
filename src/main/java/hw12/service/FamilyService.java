package hw12.service;

import hw12.dao.FamilyDao;
import hw12.model.Family;
import hw12.model.Humans.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FamilyService {
    private final List<Family> families;
    private final List<Predicate<Family>> familyFilters;
    private FamilyDao familyDao;

    public FamilyService() {
        this.families = new ArrayList<>();
        this.familyFilters = new ArrayList<>();
        this.familyDao = new FamilyDao();
    }

    public void saveData() {
        familyDao.saveData(families);
        System.out.println("Дані збережено.");
    }

    public void loadData() {
        List<Family> loadedFamilies = familyDao.loadData();
        families.clear();
        families.addAll(loadedFamilies);
        System.out.println("Дані завантажено.");
    }

    public FamilyService(List<Family> families) {
        this.families = families;
        this.familyFilters = new ArrayList<>();
    }

    public void addFamilyFilter(Predicate<Family> filter) {
        familyFilters.add(filter);
    }

    public List<Family> getFamiliesByFilters() {
        return families.stream()
                .filter(family -> familyFilters.stream().allMatch(filter -> filter.test(family)))
                .collect(Collectors.toList());
    }

    public List<Family> getFamiliesBiggerThan(int count) {
        return families.stream()
                .filter(family -> family.countFamily() >= count)
                .collect(Collectors.toList());
    }

    public List<Family> getFamiliesLessThan(int count) {
        return families.stream()
                .filter(family -> family.countFamily() < count)
                .collect(Collectors.toList());
    }

    public long countFamiliesWithMemberNumber(int number) {
        return families.stream()
                .filter(family -> family.countFamily() == number)
                .count();
    }

    public void deleteAllChildrenOlderThan(int ageLimit) {
        families.forEach(family -> family.getChildren()
                .removeIf(child -> child.getAge() > ageLimit));
    }

    public List<Family> getAllFamilies() {
        return families;
    }

    public boolean deleteFamilyByIndex(int index) {
        if (index >= 0 && index < families.size()) {
            families.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public void createNewFamily(Human mother, Human father) {
        Family newFamily = new Family(father, mother); // Ensure correct order: father first, mother second
        families.add(newFamily);
    }

    public Family getFamilyByIndex(int index) {
        if (index >= 0 && index < families.size()) {
            return families.get(index);
        }
        return null;
    }

    public void adoptChild(int familyIndex, Human child) {
        Family family = getFamilyByIndex(familyIndex);
        if (family != null) {
            family.getChildren().add(child);
        }
    }

    public void bornChild(int familyIndex, String boyName, String girlName) {
        Family family = getFamilyByIndex(familyIndex);
        if (family != null) {
            boolean isBoy = Math.random() < 0.5;
            String name = isBoy ? boyName : girlName;
            String surname = family.getFather().getSurname();
            Human child = new Human(name, surname, 0);
            family.getChildren().add(child);
        }
    }

    public void displayAllFamilies() {
        families.forEach(System.out::println);
    }
}
