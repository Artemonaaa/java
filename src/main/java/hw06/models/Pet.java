package hw06.models;

import hw06.enums.Species;
import java.util.Arrays;

public abstract class Pet {
    protected Species species = Species.UNKNOWN;
    private String nickname;
    private int age;
    private int trickLevel;
    private String[] habits;

    public Pet() {}

    public Pet(String nickname, int age, int trickLevel, String[] habits) {
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public void eat() {
        System.out.println("I am eating!");
    }

    public abstract void respond();

    public Species getSpecies() {
        return species;
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

    public int getTrickLevel() {
        return trickLevel;
    }

    public String[] getHabits() {
        return habits;
    }

    @Override
    public String toString() {
        return String.format("%s{nickname='%s', age=%d, trickLevel=%d, habits=%s}",
                species, nickname, age, trickLevel, Arrays.toString(habits));
    }
}
