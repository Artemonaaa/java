package hw06.models;

import hw06.enums.Species;

public class RoboCat extends Pet {
    public RoboCat(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.ROBOCAT;
    }

    @Override
    public void respond() {
        System.out.printf("0x1001000f... I'm RoboCat %s\n", getNickname());
    }
}
