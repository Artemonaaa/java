package hw12.service;

import hw12.dao.CollectionFamilyDao;
import hw12.model.Family;
import hw12.model.Humans.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FamilyService {
    private final CollectionFamilyDao collectionFamilyDao;
    private final List<Predicate<Family>> familyFilters;

    public FamilyService() {
        this.collectionFamilyDao = new CollectionFamilyDao();
        this.familyFilters = new ArrayList<>();
    }

    public void saveData() {
        collectionFamilyDao.saveData(collectionFamilyDao.getAllFamilies());
        System.out.println("Дані збережено.");
    }

    public void loadData() {
        List<Family> loadedFamilies = collectionFamilyDao.loadData();
        collectionFamilyDao.setFamilies(loadedFamilies);
        System.out.println("Дані завантажено.");
    }

    public void addFamilyFilter(Predicate<Family> filter) {
        familyFilters.add(filter);
    }

    public List<Family> getFamiliesByFilters() {
        return collectionFamilyDao.getAllFamilies().stream()
                .filter(family -> familyFilters.stream().allMatch(f -> f.test(family)))
                .collect(Collectors.toList());
    }

    public List<Family> getFamiliesBiggerThan(int count) {
        return collectionFamilyDao.getAllFamilies().stream()
                .filter(f -> f.countFamily() >= count)
                .collect(Collectors.toList());
    }

    public List<Family> getFamiliesLessThan(int count) {
        return collectionFamilyDao.getAllFamilies().stream()
                .filter(f -> f.countFamily() < count)
                .collect(Collectors.toList());
    }

    public long countFamiliesWithMemberNumber(int number) {
        return collectionFamilyDao.getAllFamilies().stream()
                .filter(f -> f.countFamily() == number)
                .count();
    }

    public void deleteAllChildrenOlderThan(int ageLimit) {
        collectionFamilyDao.getAllFamilies().forEach(family ->
                family.getChildren().removeIf(child -> child.getAge() > ageLimit)
        );
    }

    public List<Family> getAllFamilies() {
        return collectionFamilyDao.getAllFamilies();
    }

    public boolean deleteFamilyByIndex(int index) {
        return collectionFamilyDao.deleteFamily(index);
    }

    public void createNewFamily(Human mother, Human father) {
        Family newFamily = new Family(father, mother);
        collectionFamilyDao.saveFamily(newFamily);
    }

    public Family getFamilyByIndex(int index) {
        List<Family> families = collectionFamilyDao.getAllFamilies();
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
        collectionFamilyDao.getAllFamilies().forEach(System.out::println);
    }
}
