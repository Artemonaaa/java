package hw06.models;

import hw06.enums.Species;

public class Fish extends Pet {
    public Fish(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.FISH;
    }

    @Override
    public void respond() {
        System.out.printf("... (the fish %s just looks at you)\n", getNickname());
    }
}
