package hw12.model.Pets;

import hw12.enums.Species;

import java.util.Set;

public class Fish extends Pet {
    public Fish(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.FISH;
    }

    @Override
    public void respond() {
        System.out.printf("... (the fish %s just looks at you)\n", getNickname());
    }
}
