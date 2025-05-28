package hw12.dao;

import hw12.model.Family;

import java.util.List;

public interface FamilyDAO<F> {

    List<F> getAllFamilies();

    void setFamilies(List<Family> families);

    Family getFamilyByIndex(int index);

    boolean deleteFamily(int index);

    boolean deleteFamily(F family);

    void saveFamily(F family);
}

