package hw05.models;

import hw05.enums.Species;

import java.util.Arrays;
import java.util.Objects;

public class Pet {
    private Species species;
    private String nickname;
    private int age;
    private int trickLevel;
    private String[] habits;

    public Pet() {}

    public Pet(Species species, String nickname) {
        this.species = species;
        this.nickname = nickname;
    }

    public Pet(Species species, String nickname, int age, int trickLevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public void eat() {
        System.out.println("I am eating!");
    }

    public void respond() {
        System.out.printf("Hello, master. I am %s. I missed you!\n", nickname);
    }

    public void foul() {
        System.out.println("You need to cover your tracks well...");
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }

    @Override
    public String toString() {
        return String.format("%s{nickname='%s', age=%d, trickLevel=%d, habits=%s}",
                species, nickname, age, trickLevel, Arrays.toString(habits));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return age == pet.age &&
                trickLevel == pet.trickLevel &&
                Objects.equals(species, pet.species) &&
                Objects.equals(nickname, pet.nickname);
    }

    @Override
    @Deprecated
    protected void finalize() throws Throwable {
        System.out.println("Pet object is being garbage collected: " + this);
        super.finalize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, nickname, age, trickLevel);
    }
}