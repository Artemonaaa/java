package hw09.models;

import hw09.models.Humans.Human;
import hw09.models.Pets.Pet;

import java.util.*;

public class Family {
    private final Human mother;
    private final Human father;
    private final List<Human> children;
    private final Set<Pet> pets;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.pets = new HashSet<>();
        this.children = new ArrayList<>();
        mother.setFamily(this);
        father.setFamily(this);
    }

    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
    }

    public List<Human> getChildren() {
        return children;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public void removePet(Pet pet) {
        this.pets.remove(pet);
    }

    public void addChild(Human child) {
        children.add(child);
        child.setFamily(this);
    }

    public boolean deleteChild(int index) {
        return Optional.ofNullable(index)
                .filter(i -> i >= 0 && i < children.size())
                .map(i -> {
                    Human removed = children.remove((int) i);
                    removed.setFamily(null);
                    return true;
                })
                .orElse(false);
    }


    public boolean deleteChild(Human child) {
        return Optional.ofNullable(child)
                .filter(children::contains)
                .map(c -> {
                    children.remove(c);
                    c.setFamily(null);
                    return true;
                })
                .orElse(false);
    }

    public int countFamily() {
        return 2 + children.size();
    }

    @Override
    public String toString() {
        return String.format("Family{mother=%s, father=%s, children=%s, pets=%s}",
                mother, father, children, pets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Family family)) return false;
        return Objects.equals(mother, family.mother) &&
                Objects.equals(father, family.father) &&
                Objects.equals(children, family.children) &&
                Objects.equals(pets, family.pets);    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, children, pets);
    }
}
