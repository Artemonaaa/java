package hw10.models.Pets;

import hw10.enums.Species;

import java.util.Set;

public class DomesticCat extends Pet implements Foulable {
    public DomesticCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.DOMESTICCAT;
    }

    @Override
    public void respond() {
        System.out.printf("Meow! I'm %s, your lovely domestic cat!\n", getNickname());
    }

    @Override
    public void foul() {
        System.out.println("I do not like cats...");
    }
}
